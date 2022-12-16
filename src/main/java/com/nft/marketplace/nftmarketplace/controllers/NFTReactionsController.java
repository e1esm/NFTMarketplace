package com.nft.marketplace.nftmarketplace.controllers;


import com.google.gson.Gson;
import com.nft.marketplace.nftmarketplace.Entity.BlockEntity;
import com.nft.marketplace.nftmarketplace.Entity.User;
import com.nft.marketplace.nftmarketplace.service.NFTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/reactions")
public class NFTReactionsController {

    @Autowired
    NFTService nftService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/like")
    public ResponseEntity<?> likeCurrentFrame(@RequestBody String username, @RequestBody String idOfFrame){
        boolean result = nftService.setLike(username, Integer.parseInt(idOfFrame));

        return result ? ResponseEntity.ok().body(HttpStatus.OK) : ResponseEntity.ok().body(HttpStatus.BAD_REQUEST);
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getUserCollections")
    public ResponseEntity<?> getLikedBlocks(@RequestParam String username){
        Gson gson = new Gson();
        Map<User, Set<BlockEntity>> likedBlocks = nftService.getLikedBlocks(username);
        String json = gson.toJson(likedBlocks);
        return ResponseEntity.ok().body(json);
    }

}
