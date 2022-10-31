package com.nft.marketplace.nftmarketplace.controllers;

import com.nft.marketplace.nftmarketplace.dto.ImageDto;

import com.nft.marketplace.nftmarketplace.service.Publication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NFTUploadingImageController {
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
    public ResponseEntity<?> saveCollection(@RequestBody ImageDto img){
        Publication pub = new Publication(img.getSrc());
        pub.split(img.getNblocks());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        responseHeaders.set("Access-Control-Allow-Origin", "localhost:3000");
        responseHeaders.set("Access-Control-Allow-Headers",  "Origin, X-Requested-With, Content-Type, Accept");
        return ResponseEntity.ok().body(pub.getJsonRepresentation());
    }
}