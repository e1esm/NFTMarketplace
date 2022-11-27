package com.nft.marketplace.nftmarketplace.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "nftcollection")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NFTCollectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "collection_id", nullable = false)
    private Integer id;

    @Column(name = "author")
    private String author;


    @Column(name = "collection_title", unique = false)
    private String title;

    @Column(name = "src")
    @Type(type = "text")
    private String sourceCode;


    @Column(name = "amount_of_blocks")
    private Integer amount_of_blocks;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "collection", cascade = CascadeType.ALL)
    private List<BlockEntity> blockEntityList = new ArrayList<>();


    public NFTCollectionEntity(String title, String src, String author, int amount_of_blocks) {
        this.title = title;
        this.sourceCode = src;
        this.author = author;
        this.amount_of_blocks = amount_of_blocks;
    }


}
