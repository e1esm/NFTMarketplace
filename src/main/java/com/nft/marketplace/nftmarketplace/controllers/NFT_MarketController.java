package com.nft.marketplace.nftmarketplace.controllers;


import com.nft.marketplace.nftmarketplace.models.reuqests.MarketRequest;
import com.nft.marketplace.nftmarketplace.service.NFTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("/market")
public class NFT_MarketController {

    @Autowired
    NFTService nftService;

    @GetMapping
    public ResponseEntity<?> retrieveCollections(@RequestBody MarketRequest marketRequest){
        Map<String, Integer> retrievedCollections = nftService.getBlocks(marketRequest.getLimit(), marketRequest.getPageNumber());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-TOTAL-COUNT", String.valueOf(retrievedCollections.values().stream().findFirst().get()));
        return ResponseEntity.ok().headers(httpHeaders).body(retrievedCollections.keySet().stream().findFirst().get());
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> retrieveBlock(@PathVariable Integer id){
        String jsonReprOfBlock = nftService.getPreciseBlock(id);
        return ResponseEntity.ok().body(jsonReprOfBlock);
    }


}
