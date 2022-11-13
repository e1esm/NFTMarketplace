apackage com.nft.marketplace.nftmarketplace.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;

@RestController
public class RegistrationController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registration(@RequestBody UserDto user) {
        registrationService.addUser(
                user.getUser_id(),
                user.getUsername(),
                user.getAvatar(),
                user.getEmail(),
                user.getRole()
        );
    }
}
