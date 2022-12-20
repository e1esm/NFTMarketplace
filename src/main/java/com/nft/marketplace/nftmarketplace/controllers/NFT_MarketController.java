package com.nft.marketplace.nftmarketplace.controllers;


import com.google.gson.Gson;
import com.nft.marketplace.nftmarketplace.Entity.Wallet;
import com.nft.marketplace.nftmarketplace.models.reuqests.MarketRequest;
import com.nft.marketplace.nftmarketplace.service.NFTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController()
@RequestMapping("/market")
public class NFT_MarketController {

    @Autowired
    NFTService nftService;
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/")
    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_AUTHOR"})
    public ResponseEntity<?> retrieveCollections(@RequestParam MarketRequest marketRequest){
        Map<String, Integer> retrievedCollections = nftService.getBlocks(marketRequest.getLimit(), marketRequest.getPage());


        return ResponseEntity.ok().body(retrievedCollections.keySet().stream().findFirst().get());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getPreciseBlock/")
    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_AUTHOR"})
    public ResponseEntity<?> retrieveBlock(@RequestParam String id, @RequestParam String username){

        Set<Wallet> wallet = nftService.getWalletOfSeller(Integer.parseInt(id));

        String jsonReprOfBlock = nftService.getPreciseBlock(Integer.parseInt(id));
        if(jsonReprOfBlock.isEmpty()) {
            return ResponseEntity.ok().body("No entitties in db");
        }
        ArrayList<String> response = new ArrayList<>();
        response.add(jsonReprOfBlock);
        response.add(wallet.stream().findFirst().get().getWallet());
        return ResponseEntity.ok().body(new Gson().toJson(response));
    }

    @CrossOrigin(origins = "https://localhost:3000")
    @GetMapping(value = "getCollectionById/")
    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_AUTHOR"})
    public ResponseEntity<?> getPreciseCollection(@RequestParam String id, @RequestParam String username){
        Long amountOfCollections = nftService.getNftRepository().count();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-TOTAL-COUNT", String.valueOf(amountOfCollections));

        String collectionJson = nftService.getPreciseCollection(Integer.parseInt(id));

        return ResponseEntity.ok().headers(httpHeaders).body(collectionJson);
    }

}
