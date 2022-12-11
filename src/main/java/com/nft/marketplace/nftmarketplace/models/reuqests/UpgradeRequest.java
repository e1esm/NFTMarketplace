package com.nft.marketplace.nftmarketplace.models.reuqests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpgradeRequest {
    String username;
    String requiredRole;
}
