package com.nft.marketplace.nftmarketplace.controllers;


import com.nft.marketplace.nftmarketplace.Entity.Role;
import com.nft.marketplace.nftmarketplace.Entity.User;
import com.nft.marketplace.nftmarketplace.Entity.Wallet;
import com.nft.marketplace.nftmarketplace.dto.ERole;
import com.nft.marketplace.nftmarketplace.models.responses.MessageResponse;
import com.nft.marketplace.nftmarketplace.models.reuqests.ConnectWalletRequest;
import com.nft.marketplace.nftmarketplace.repository.UserRepository;
import com.nft.marketplace.nftmarketplace.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/api/test")
public class ConnectWalletController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WalletRepository walletRepository;

    @PutMapping("/verifyWallet")
    public ResponseEntity<?> connectWallet(@Valid @RequestBody ConnectWalletRequest connectWalletRequest) {
        // если нет юзера с такой почтой - ошибка
        if (!userRepository.existsByEmail(connectWalletRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: There is no user with such email"));
        }
        // если работаем с существующим кошельком
        if (walletRepository.existsByWallet(connectWalletRequest.getWallet())) {
            Wallet check_wallet = walletRepository.findByWallet(connectWalletRequest.getWallet());
            // если введен уже существующий кошелек и уже привязан к юзеру
            if (check_wallet.getEmail().equals(connectWalletRequest.getEmail())) {
                return ResponseEntity.ok().body("Wallet checked successfully");
            }

            // иначе кошелек неверный
            return ResponseEntity.badRequest().body(new MessageResponse("This wallet is already connected to another user"));
        }
        // если кошелька нет в дб
        Wallet wallet = new Wallet(connectWalletRequest.getEmail(), connectWalletRequest.getWallet());
        walletRepository.save(wallet);
        return ResponseEntity.ok().body("Wallet connected successfully");

        // если юзер с такой почтой есть и кошелек уже привязан
        // нужна проверка совпадения кошельков

    }

}
