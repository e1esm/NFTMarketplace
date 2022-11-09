package com.nft.marketplace.nftmarketplace.dto;

import lombok.Getter;
import lombok.Setter;



// Sent from front end and later processed into publication
@Getter
@Setter
public class ImageDto {
    private int n_blocks;
    private String src;
    private String title;
    private String walletID;
}
