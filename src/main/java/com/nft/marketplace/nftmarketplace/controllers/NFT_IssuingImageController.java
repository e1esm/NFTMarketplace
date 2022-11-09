package com.nft.marketplace.nftmarketplace.controllers;

import com.nft.marketplace.nftmarketplace.dto.ImageDto;

import com.nft.marketplace.nftmarketplace.models.NFTCollection;
import com.nft.marketplace.nftmarketplace.models.Publication;
import com.nft.marketplace.nftmarketplace.service.NFTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NFT_IssuingImageController {

    @Autowired
    NFTService nftService;

    @GetMapping("/nftcollections")
    public List<NFTCollection> getAllCollections() {
        return nftService.getAllCollections();
    }

    @CrossOrigin
    @PostMapping("/savePublication")
    public ResponseEntity<?> saveCollection(@RequestBody ImageDto img){
        Publication pub = new Publication(img.getSrc());
        pub.split(img.getN_blocks());
        nftService.saveOrUpdate(pub.nftCollectionBuilder(img.getTitle(), img.getWalletID(), img.getSrc()));
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        responseHeaders.set("Access-Control-Allow-Origin", "localhost:3000");
        responseHeaders.set("Access-Control-Allow-Headers",  "Origin, X-Requested-With, Content-Type, Accept");
        return ResponseEntity.ok().body(pub.getJsonRepresentation());
    }
}