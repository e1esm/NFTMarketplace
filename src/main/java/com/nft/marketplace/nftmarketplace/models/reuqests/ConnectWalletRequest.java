package com.nft.marketplace.nftmarketplace.models.reuqests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ConnectWalletRequest {
    @NotBlank
    public String email;
    @NotBlank
    public String Wallet;
}
