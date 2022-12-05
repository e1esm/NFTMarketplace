package com.nft.marketplace.nftmarketplace.service;


import com.nft.marketplace.nftmarketplace.repository.RoleRepository;
import com.nft.marketplace.nftmarketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    /*
    public void update(String username, String requestedRole){
        roleRepository.
    }

     */

}
