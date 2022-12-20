package com.nft.marketplace.nftmarketplace.models.reuqests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConnectWallet {
    @NotBlank
    public String email;

    @NotBlank
    public String wallet;
}
