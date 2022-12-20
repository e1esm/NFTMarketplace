package com.nft.marketplace.nftmarketplace.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "wallets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Type(type = "text")
    @NotBlank
    @Column(name = "wallet")
    @Pattern(regexp = "^0x[0-9a-fA-F]{40}$", message = "wrong format")
    private String wallet;


}
