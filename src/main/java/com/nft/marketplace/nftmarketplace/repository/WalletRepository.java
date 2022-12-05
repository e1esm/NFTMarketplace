package com.nft.marketplace.nftmarketplace.repository;


import com.nft.marketplace.nftmarketplace.Entity.User;
import com.nft.marketplace.nftmarketplace.Entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findByEmail(String email);


    Wallet findByWallet(String wallet);

    Boolean existsByEmail(String email);

    Boolean existsByWallet(String wallet);
}
