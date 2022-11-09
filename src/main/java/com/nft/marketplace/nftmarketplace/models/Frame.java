package com.nft.marketplace.nftmarketplace.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Frame {

        public int x, y, h, w;

        public String base64;

        @JsonIgnore
        public BufferedImage source;

        public Frame() {
            x = 0;
            y = 0;
            source = null;
        }
        public Frame(int x, int y, BufferedImage source, String format) {
            this.x = x;
            this.y = y;
            this.source = source;
            ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
            try {
                ImageIO.write(source, format, byteArrayOS);
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
            h = geth();
            w = getw();
            base64 = "data:image/" + format + ";base64,"+ Base64.getEncoder().encodeToString(byteArrayOS.toByteArray());
        }

        public int getw() { return source.getWidth(); }
        public int geth() { return source.getHeight(); }

}
