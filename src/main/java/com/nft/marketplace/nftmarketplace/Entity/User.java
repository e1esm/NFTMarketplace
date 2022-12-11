package com.nft.marketplace.nftmarketplace.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;



@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Type(type = "text")
    @NotBlank
    @Size(max = 20)
    private String username;

    @Column(name = "email")
    @Type(type = "text")
    @NotBlank
    @Size(max = 50)
    private String email;

    @Type(type = "text")
    @NotBlank
    private String password;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "liked_blocks", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "block_id"))
    private Set<BlockEntity> likedBlocks = new HashSet<>();


    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
