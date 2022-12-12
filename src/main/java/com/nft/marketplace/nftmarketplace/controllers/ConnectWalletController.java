package com.nft.marketplace.nftmarketplace.controllers;


import com.nft.marketplace.nftmarketplace.Entity.Role;
import com.nft.marketplace.nftmarketplace.Entity.User;
import com.nft.marketplace.nftmarketplace.Entity.Wallet;
import com.nft.marketplace.nftmarketplace.dto.ERole;
import com.nft.marketplace.nftmarketplace.models.responses.ErrorResponse;
import com.nft.marketplace.nftmarketplace.models.responses.MessageResponse;
import com.nft.marketplace.nftmarketplace.models.responses.OkResponse;
import com.nft.marketplace.nftmarketplace.models.reuqests.ConnectWalletRequest;
import com.nft.marketplace.nftmarketplace.repository.UserRepository;
import com.nft.marketplace.nftmarketplace.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/api/test")
public class ConnectWalletController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WalletRepository walletRepository;



    @PostMapping("/verifyWallet")
    public ResponseEntity<?> connectWallet(@Valid @RequestBody ConnectWalletRequest connectWalletRequest) {
        // если нет юзера с такой почтой - ошибка
        if (!userRepository.existsByEmail(connectWalletRequest.getEmail())) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setErrorCode("NON_EXISTENT_USER");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        // если работаем с существующим кошельком
        if (walletRepository.existsByWallet(connectWalletRequest.getWallet())) {
            Wallet check_wallet = walletRepository.findByWallet(connectWalletRequest.getWallet());
            // если введен уже существующий кошелек и уже привязан к юзеру
            if (check_wallet.getEmail().equals(connectWalletRequest.getEmail())) {
                OkResponse okResponse = new OkResponse();
                return new ResponseEntity<>(okResponse, HttpStatus.OK);
            }

            // иначе кошелек неверный
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setErrorCode("WALLET_BUSY");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        // если кошелька нет в дб
        Wallet wallet = new Wallet(connectWalletRequest.getEmail(), connectWalletRequest.getWallet());
        walletRepository.save(wallet);
        OkResponse okResponse = new OkResponse();
        return new ResponseEntity<>(okResponse, HttpStatus.OK);


    }

}
