package com.nft.marketplace.nftmarketplace.controllers;
import com.nft.marketplace.nftmarketplace.Entity.NFTCollectionEntity;
import com.nft.marketplace.nftmarketplace.dto.ImageDto;
import com.nft.marketplace.nftmarketplace.models.Publication;
import com.nft.marketplace.nftmarketplace.service.NFTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/marketplace")
public class NFT_IssuingImageController {

    @Autowired
    NFTService nftService;
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/nftCollections")
    public List<NFTCollectionEntity> getAllCollections() {

        return nftService.getAllCollections();

    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/makeNft")
    @Secured("ROLE_AUTHOR")
    public ResponseEntity<?> saveCollection(@RequestBody ImageDto img){

        Publication pub = new Publication(img.getSrc(), img.getTitle());
        pub.split(img.getN_blocks());

        nftService.saveOrUpdate(pub.nftCollectionBuilder(img.getTitle(), img.getWalletID(), img.getSrc(),(img.getN_blocks())), pub);

        return ResponseEntity.ok().body(pub.getJsonRepresentation());
    }
}