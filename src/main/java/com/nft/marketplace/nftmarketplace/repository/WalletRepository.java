package com.nft.marketplace.nftmarketplace.repository;

import com.nft.marketplace.nftmarketplace.Entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

        Wallet findByWallet(String wallet);


        Boolean existsByWallet(String wallet);
}
