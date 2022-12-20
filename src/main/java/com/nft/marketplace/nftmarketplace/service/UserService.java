package com.nft.marketplace.nftmarketplace.service;

import com.nft.marketplace.nftmarketplace.Entity.User;
import com.nft.marketplace.nftmarketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Long getUserId(String username){
        return userRepository.getUserId(username);
    }


    public User getUserInstance(String id){
        return userRepository.getReferenceById(Long.parseLong(id));
    }


}
