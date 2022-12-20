package com.nft.marketplace.nftmarketplace.service;

import com.nft.marketplace.nftmarketplace.repository.UserRepository;
import com.nft.marketplace.nftmarketplace.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class WalletService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WalletRepository walletRepository;

    
}
