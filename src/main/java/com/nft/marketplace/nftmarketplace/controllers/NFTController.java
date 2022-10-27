package com.nft.marketplace.nftmarketplace.controllers;

import com.nft.marketplace.nftmarketplace.models.ImageDTO;

import com.nft.marketplace.nftmarketplace.service.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
public class NFTController {
    /*
    @Autowired
    NFTService nftService;

    @GetMapping("/nftcollections")
    public List<NFTCollection> getAllCollections() {
        return nftService.getAllCollections();
    }
     */
    @CrossOrigin
    @PostMapping("/")
    public ResponseEntity<?> saveCollection(@RequestBody ImageDTO img){
        Publication pub = new Publication(img.getSrc());
        pub.split(img.getNblocks());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        responseHeaders.set("Access-Control-Allow-Origin", "localhost:3000");
        responseHeaders.set("Access-Control-Allow-Headers",  "Origin, X-Requested-With, Content-Type, Accept");
        return ResponseEntity.ok().body(pub.getJsonRepresentation());
    }
}