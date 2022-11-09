
package com.nft.marketplace.nftmarketplace.models;


import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;


@Getter
@Setter
@Entity
@Table(name = "NFTCollection")
public class NFTCollection extends Publication{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String title;


   @Column
   @Type(type = "text")
   private String src;


   @Column
   String author;

    public NFTCollection() {

    }

    public NFTCollection(String title, String src, String author){
        this.src = src;
        this.title = title;
        this.author = author;
    }
}
