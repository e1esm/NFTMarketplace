package com.nft.marketplace.nftmarketplace.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Block {

        public int x, y;

        public String base64;

        @JsonIgnore
        public BufferedImage source;

        public Block() {
            x = 0;
            y = 0;
            source = null;
        }

        public Block(int x, int y, BufferedImage source) {
            this.x = x;
            this.y = y;
            this.source = source;
            ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
            try {
                ImageIO.write(source, "jpg", byteArrayOS);
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
            base64 = "data:image/jpeg;base64,"+ Base64.getEncoder().encodeToString(byteArrayOS.toByteArray());
        }

        public int getw() { return source.getWidth(); }
        public int geth() { return source.getHeight(); }

}
