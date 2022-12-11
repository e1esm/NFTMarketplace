package com.nft.marketplace.nftmarketplace.service;


import com.nft.marketplace.nftmarketplace.Entity.Role;
import com.nft.marketplace.nftmarketplace.Entity.User;
import com.nft.marketplace.nftmarketplace.dto.ERole;
import com.nft.marketplace.nftmarketplace.repository.RoleRepository;
import com.nft.marketplace.nftmarketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class RolesService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    public void update(String username, String requestedRole){
        User user = userRepository.findByUsername(username).isPresent() ? userRepository.findByUsername(username).get() : null;
        Optional<Role> role;
        switch (requestedRole){
            case "ROLE_AUTHOR":
                role = roleRepository.findByName(ERole.ROLE_AUTHOR);
                break;
            case "ROLE_ADMIN":
                role = roleRepository.findByName(ERole.ROLE_ADMIN);
                break;
            default:
                role = Optional.empty();
        }
        user.setRoles(Set.of(role.get()));
        roleRepository.save(user.getId().intValue(), role.get().getId());

    }

}
