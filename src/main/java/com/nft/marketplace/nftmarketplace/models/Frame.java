package com.nft.marketplace.nftmarketplace.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nft.marketplace.nftmarketplace.Entity.BlockEntity;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64Encoder;

import java.util.Arrays;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class Frame {

        public int x, y, h, w;

        public String base64;

        public String collectionTitle;

        @JsonIgnore
        public BufferedImage source;

        public Frame() {
            x = 0;
            y = 0;
            source = null;
        }

        public Frame(int x, int y, BufferedImage source, String format, String title) {
            Logger logger = Logger.getLogger(String.valueOf(Frame.class));
            this.x = x;
            this.y = y;
            this.source = source;
            this.collectionTitle = title;
            ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
            try {
                boolean isWritten = ImageIO.write(source, format, byteArrayOS);
                logger.info(String.valueOf(isWritten));
                logger.info(String.valueOf(format));
                byteArrayOS.flush();
                base64=Base64.getEncoder().encodeToString(byteArrayOS.toByteArray());
                byteArrayOS.close();

            }catch (IOException e){
                System.out.println(e.getMessage());
            }
            h = geth();
            w = getw();

            base64 = "data:image/" + format + ";base64," + base64;
        }

        public int getw() { return source.getWidth(); }
        public int geth() { return source.getHeight(); }


        public BlockEntity nftBlockEntityBuilder(){
            Logger logger = Logger.getLogger(String.valueOf(Frame.class));
            logger.info(collectionTitle);
            return new BlockEntity(base64, h, w, x, y, collectionTitle);
        }

}
