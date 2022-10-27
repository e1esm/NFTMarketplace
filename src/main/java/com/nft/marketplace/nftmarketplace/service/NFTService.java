/*
package com.nft.marketplace.nftmarketplace.service;


import com.nft.marketplace.nftmarketplace.models.NFTCollection;
import com.nft.marketplace.nftmarketplace.repository.NFTRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@Service
public class NFTService {

    @Autowired
    NFTRepository nftRepository;

    public List<NFTCollection> getAllCollections(){

        List<NFTCollection> nftlist = new ArrayList<>();
        nftRepository.findAll().forEach(nft-> nftlist.add(nft));

        return nftlist;
    }
    public void saveOrUpdate(NFTCollection nftCollection){
        nftRepository.save(nftCollection);
    }

}

 */
