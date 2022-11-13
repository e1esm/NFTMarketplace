package com.nft.marketplace.nftmarketplace.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.lang.Object;

import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

@Getter @Setter
@Entity
@Table(name = "User")
public class User {

    @Id @Column(name = "User_id")
    private int User_id;


    @Column(name = "username")
    private String Username;

    @Column(name = "avatar")
    private BufferedImage Avatar;


    @Column(name = "email")
    private String Email;


    @Column(name = "role")
    private String Role;

    public User() {
        this.User_id = 0;
        this.Username = null;
        this.Avatar = null;
        this.Email = null;
        this.Role = null;
    }
    public User(int User_id, String Username, BufferedImage Avatar,
                String Email, String Role) {
        this.User_id = User_id;
        this.Username = Username;
        this.Avatar = Avatar;
        this.Email = Email;
        this.Role = Role;
    }

    private Integer maxDel(int n) {
        int del = (int)Math.ceil(Math.sqrt(n));
        while (del > 1) {
            if (n % del == 0) break;
            del--;
        }
        return del;
    }

    public String getJsonRepresentation() throws JsonProcessingException {
        String json_str = "";
        JsonObject json = new JsonObject();
        json.addProperty("User_id", User_id);
        json.addProperty("Username", Username);
        json.addProperty("Avatar", Avatar); // не хранит bufferedimage
        json.addProperty("Email", Email);
        json.addProperty("Role", Role);
    }

}
