package com.nft.marketplace.nftmarketplace.Entity;


import com.nft.marketplace.nftmarketplace.dto.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "wallets")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Type(type = "text")
    @NotBlank
    @Column(name = "email")
    private String email;

    @Type(type = "text")
    @NotBlank
    @Column(name = "wallet")
    @Pattern(regexp = "^0x[0-9a-fA-F]{40}$", message = "wrong format")
    private String wallet;

    public Wallet(String email, String wallet) {
        this.email = email;
        this.wallet = wallet;
    }
}
