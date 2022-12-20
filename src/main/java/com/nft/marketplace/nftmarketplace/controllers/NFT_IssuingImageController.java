package com.nft.marketplace.nftmarketplace.controllers;
import com.nft.marketplace.nftmarketplace.Entity.NFTCollectionEntity;
import com.nft.marketplace.nftmarketplace.Entity.User;
import com.nft.marketplace.nftmarketplace.dto.ImageDto;
import com.nft.marketplace.nftmarketplace.models.Publication;
import com.nft.marketplace.nftmarketplace.service.NFTService;
import com.nft.marketplace.nftmarketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class NFT_IssuingImageController {

    @Autowired
    NFTService nftService;

    @Autowired
    UserService userService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/buyNft")
    @Secured({"ROLE_USER", "ROLE_AUTHOR", "ROLE_ADMIN"})
    public ResponseEntity<?> buyNft(@RequestParam String username, @RequestParam String idOfBlock){
        nftService.buyNft(username, Integer.valueOf(idOfBlock));
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/makeCollection/")
    @Secured("ROLE_AUTHOR")
    public ResponseEntity<?> saveCollection(@RequestBody ImageDto img){

        String username = img.getUsername();
        Long authorId = userService.getUserId(username);
        User user = userService.getUserInstance(String.valueOf(authorId));


        Publication pub = new Publication(img.getSrc(), img.getTitle());
        pub.split(img.getNblocks());

        nftService.saveOrUpdate(pub.nftCollectionBuilder(img.getTitle(),user ,img.getSrc() ,img.getNblocks(), img.getPrice()), pub);

        return ResponseEntity.ok().body(pub.getJsonRepresentation());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/unmakeCollection/")
    @Secured("ROLE_AUTHOR")
    public ResponseEntity<?> unmakeCollection(@RequestParam int id){
        nftService.deleteCollection(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}