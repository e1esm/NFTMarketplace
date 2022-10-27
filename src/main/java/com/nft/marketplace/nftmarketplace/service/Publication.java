package com.nft.marketplace.nftmarketplace.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nft.marketplace.nftmarketplace.models.Block;
import com.nft.marketplace.nftmarketplace.models.ImageDTO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import javax.xml.bind.DatatypeConverter;

public class Publication {


    BufferedImage source;
    ArrayList<Block> blocks;


    Publication() {
      blocks = new ArrayList<>();
      source = null;
    }

    public Publication(String src) {
        try {
            src = src.split(",")[1];
            source = ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(src)));
        } catch (IOException e) {
            System.out.println("image buffer is empty");
        }
        blocks = new ArrayList<Block>();
    }


    private Integer maxDel(int n) {
        int del = (int)Math.ceil(Math.sqrt(n));
        while (del > 1) {
            if (n % del == 0) break;
            del--;
        }
        return del;
    }

    public void split(Integer blocksNum) {
        if (blocks.size() == blocksNum) return;
        int n = maxDel(blocksNum);
        int m = blocksNum / n;

        int dw = source.getWidth() / n;
        int dh = source.getHeight() / m;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x = dw * j;
                int y = dh * i;
                int w = (j == n - 1) ? source.getWidth() - x : dw;
                int h = (i == m - 1) ? source.getHeight() - y : dh;

                blocks.add(new Block(i, j, source.getSubimage(x, y, w, h)));
            }
        }
    }

    public String getJsonRepresentation(){
        String json = "";
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            json = ow.writeValueAsString(blocks);
        }catch (JacksonException e){
            System.out.println(e.getMessage());
        }
        return "{\"blocks\":" + json + "}";
    }

    //'{"blocks":["a"]}'

    @Override
    public String toString() {
        return String.format("<image w=%d h=%d nblocks=%d>",
                source.getWidth(),
                source.getHeight(),
                blocks.size());
    }








}
