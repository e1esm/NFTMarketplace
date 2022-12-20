package com.nft.marketplace.nftmarketplace.dto;

import lombok.Getter;
import lombok.Setter;



// Sent from front end and later processed into publication
@Getter
@Setter
public class ImageDto {
    private float price;
    private int nblocks;
    private String src;
    private String title;
    private String walletID;
    private String username;
    private String sellerWallet;
}
