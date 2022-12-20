package com.nft.marketplace.nftmarketplace.controllers;



import com.google.gson.Gson;
import com.nft.marketplace.nftmarketplace.Entity.User;
import com.nft.marketplace.nftmarketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserRepository userRepository;


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/")
    public ResponseEntity<?> getUserInfo(@RequestParam String username, @RequestParam(value = "fields") List<String> params){
        List<Object> userlist = userRepository.getUser(params.toString().substring(1, params.toString().length() - 1), username);
        return userlist.size() == 0 ? ResponseEntity.ok(HttpStatus.OK) : ResponseEntity.ok(new Gson().toJson(userlist));
    }
}

