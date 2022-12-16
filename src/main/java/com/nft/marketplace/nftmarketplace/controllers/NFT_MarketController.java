package com.nft.marketplace.nftmarketplace.controllers;


import com.nft.marketplace.nftmarketplace.models.reuqests.MarketRequest;
import com.nft.marketplace.nftmarketplace.service.NFTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController()
@RequestMapping("/market")
public class NFT_MarketController {

    @Autowired
    NFTService nftService;
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getAllBlocks")
    public ResponseEntity<?> retrieveCollections(@RequestBody MarketRequest marketRequest){
        Map<String, Integer> retrievedCollections = nftService.getBlocks(marketRequest.getLimit(), marketRequest.getPage());


        return ResponseEntity.ok().body(retrievedCollections.keySet().stream().findFirst().get());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getPreciseBlock")
    public ResponseEntity<?> retrieveBlock(@RequestParam String id){

        String jsonReprOfBlock = nftService.getPreciseBlock(Integer.parseInt(id));
        if(jsonReprOfBlock.isEmpty()) {
            return ResponseEntity.ok().body("No entitties in db");
        }
        return ResponseEntity.ok().body(jsonReprOfBlock);
    }



}
