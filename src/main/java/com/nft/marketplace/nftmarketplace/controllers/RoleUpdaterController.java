package com.nft.marketplace.nftmarketplace.controllers;


import com.nft.marketplace.nftmarketplace.repository.RoleRepository;
import com.nft.marketplace.nftmarketplace.repository.UserRepository;
import com.nft.marketplace.nftmarketplace.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/roles")
public class RoleUpdaterController {

    @Autowired
    RolesService rolesService;

    /*
    @PutMapping("/upgradeRole")
    public ResponseEntity<?> upgradeRole(@RequestBody  String username, @RequestBody String requestedRole) {

        rolesService.

        return ResponseEntity.ok().body("Username: " + username + "Received new role: " + requestedRole);
    }

     */

}
