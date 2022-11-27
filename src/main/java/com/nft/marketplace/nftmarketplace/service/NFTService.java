package com.nft.marketplace.nftmarketplace.service;
import com.nft.marketplace.nftmarketplace.Entity.BlockEntity;
import com.nft.marketplace.nftmarketplace.Entity.NFTCollectionEntity;
import com.nft.marketplace.nftmarketplace.models.Publication;
import com.nft.marketplace.nftmarketplace.repository.BlockRepository;
import com.nft.marketplace.nftmarketplace.repository.NFTRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@Service
public class NFTService {

    @Autowired
    NFTRepository nftRepository;

    @Autowired
    BlockRepository blockRepository;

    public List<NFTCollectionEntity> getAllCollections(){

        List<NFTCollectionEntity> nftlist = new ArrayList<>();
        nftRepository.findAll().forEach(nft-> nftlist.add(nft));

        return nftlist;
    }
    public void saveOrUpdate(NFTCollectionEntity nftCollection, Publication publication){

        NFTCollectionEntity nftCollectionEntity = nftRepository.save(nftCollection);

        ArrayList<BlockEntity> blockEntities = new ArrayList<>();
        for(int i = 0; i < publication.getBlocks().size(); i++){
            blockEntities.add(publication.getBlocks().get(i).nftBlockEntityBuilder());
            blockEntities.get(i).setCollection((nftCollectionEntity));
        }

        blockRepository.saveAll(blockEntities);

    }

}
