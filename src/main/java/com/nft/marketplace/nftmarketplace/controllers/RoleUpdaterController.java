package com.nft.marketplace.nftmarketplace.controllers;


import com.nft.marketplace.nftmarketplace.models.reuqests.UpgradeRequest;
import com.nft.marketplace.nftmarketplace.repository.RoleRepository;
import com.nft.marketplace.nftmarketplace.repository.UserRepository;
import com.nft.marketplace.nftmarketplace.service.RolesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/roles")
public class RoleUpdaterController {

    @Autowired
    RolesService rolesService;


    @Secured({"ROLE_USER", "ROLE_AUTHOR"})
    @PostMapping("/upgradeRole")
    public ResponseEntity<?> upgradeRole(@RequestBody UpgradeRequest upgradeRequest) {

        Logger logger = LoggerFactory.getLogger(RoleUpdaterController.class);
        logger.info(upgradeRequest.getUsername());
        logger.info(upgradeRequest.getRequiredRole());

        rolesService.update(upgradeRequest.getUsername(), upgradeRequest.getRequiredRole());

        return ResponseEntity.ok().body("Username: " + upgradeRequest.getUsername() + "Received new role: " + upgradeRequest.getRequiredRole());
    }


}
