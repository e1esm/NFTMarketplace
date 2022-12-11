package com.nft.marketplace.nftmarketplace.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "blocks")
public class BlockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @ManyToOne
    private NFTCollectionEntity collection;


    @Column(name = "of_collection_title")
    private String collectionTitle;

    @Column(name = "src")
    @Type(type = "text")
    private String sourceCode;

    @Column(name = "height")
    private int h;

    @Column(name = "width")
    private int w;

    @Column(name = "x")
    private int x;

    @Column(name = "y")
    private int y;


    @Column(name = "likes")
    private int likes;


    public BlockEntity(String sourceCode, int h, int w, int x, int y, String collectionTitle){
        this.sourceCode = sourceCode;
        this.h = h;
        this.w = w;
        this.x = x;
        this.y = y;
        this.collectionTitle = collectionTitle;
        likes = 0;
    }




}
