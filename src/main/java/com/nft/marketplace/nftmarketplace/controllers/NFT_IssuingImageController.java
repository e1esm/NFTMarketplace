package com.nft.marketplace.nftmarketplace.controllers;

import com.nft.marketplace.nftmarketplace.Entity.BlockEntity;
import com.nft.marketplace.nftmarketplace.Entity.NFTCollectionEntity;
import com.nft.marketplace.nftmarketplace.dto.ImageDto;

import com.nft.marketplace.nftmarketplace.models.Publication;
import com.nft.marketplace.nftmarketplace.service.NFTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller("/marketplace")
public class NFT_IssuingImageController {

    @Autowired
    NFTService nftService;

    @GetMapping("/nftcollections")
    public List<NFTCollectionEntity> getAllCollections() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        responseHeaders.set("Access-Control-Allow-Origin", "localhost:3000");
        responseHeaders.set("Access-Control-Allow-Headers",  "Origin, X-Requested-With, Content-Type, Accept");
        return nftService.getAllCollections();

    }


    @CrossOrigin
    @PostMapping("/makeNft")
    @Secured("ROLE_AUTHOR")
    public ResponseEntity<?> saveCollection(@RequestBody ImageDto img){

        Publication pub = new Publication(img.getSrc(), img.getTitle());
        pub.split(img.getN_blocks());

        nftService.saveOrUpdate(pub.nftCollectionBuilder(img.getTitle(), img.getWalletID(), img.getSrc(),(img.getN_blocks())), pub);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        responseHeaders.set("Access-Control-Allow-Origin", "localhost:3000");
        responseHeaders.set("Access-Control-Allow-Headers",  "Origin, X-Requested-With, Content-Type, Accept");
        return ResponseEntity.ok().body(pub.getJsonRepresentation());
    }
}