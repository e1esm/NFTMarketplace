package com.nft.marketplace.nftmarketplace.models;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.nft.marketplace.nftmarketplace.Entity.NFTCollectionEntity;
import com.nft.marketplace.nftmarketplace.Entity.User;
import lombok.Getter;
import lombok.Setter;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Logger;

@Getter
@Setter

public class Publication {

    BufferedImage source;
    ArrayList<Frame> blocks;

    String format;

    String title;

    Publication() {
      blocks = new ArrayList<>();
      source = null;
    }

    public Publication(String src, String title) {
        try {
            String[] stringArr = src.split(",");
            source = ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(stringArr[1])));
            format = stringArr[0].substring(stringArr[0].indexOf('/') + 1,stringArr[0].indexOf(';'));
        } catch (IOException e) {
            System.out.println("image buffer is empty");
        }
        this.title = title;
        blocks = new ArrayList<Frame>();
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

                blocks.add(new Frame(i, j, source.getSubimage(x, y, w, h), format,title ));
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

    public NFTCollectionEntity nftCollectionBuilder(String title, User author, String src, int amount, float price){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = "";
        try{
            json = ow.writeValueAsString(blocks);
        }catch (JacksonException e){
            System.out.println(e.getMessage());
        }
        return new NFTCollectionEntity(title, src, author, amount, price);
    }

}
