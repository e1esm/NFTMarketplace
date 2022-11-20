package com.nft.marketplace.nftmarketplace.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "User")
@Getter @Setter
public class User {

    @Id @Column(name = "User_id")
    private Long User_id;

    @Column(name = "username")
    @Size(max = 20)
    @NotBlank
    private String username;

    @Column(name = "avatar")
    private String avatar;

    @NotBlank
    @Size(max = 120)
    private String password;

    @Column(name = "email")
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Column(name = "role")
    private String role;

    public User() {
    }

    public User(String username, String avatar, String password, String email, String role) {
        this.username = username;
        this.email = email;
        this.avatar = avatar;
        this.password = password;
        this.role = role;
    }
}
