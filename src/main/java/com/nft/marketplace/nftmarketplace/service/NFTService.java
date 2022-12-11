package com.nft.marketplace.nftmarketplace.service;
import com.nft.marketplace.nftmarketplace.Entity.BlockEntity;
import com.nft.marketplace.nftmarketplace.Entity.NFTCollectionEntity;
import com.nft.marketplace.nftmarketplace.Entity.User;
import com.nft.marketplace.nftmarketplace.models.Publication;
import com.nft.marketplace.nftmarketplace.repository.BlockRepository;
import com.nft.marketplace.nftmarketplace.repository.NFTRepository;
import com.nft.marketplace.nftmarketplace.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Block;
import org.springframework.stereotype.Service;

import java.util.*;

@Setter
@Getter
@Service
public class NFTService {

    @Autowired
    NFTRepository nftRepository;

    @Autowired
    BlockRepository blockRepository;

    @Autowired
    UserRepository userRepository;

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

    public boolean setLike(String username, int idOfBlock){
        boolean isSet = false;

        BlockEntity block = blockRepository.findById(idOfBlock).get();
        int amount = block.getLikes();
        block.setLikes(amount + 1);


        return isSet;
    }

    public Map<User, Set<BlockEntity>> getLikedBlocks(String username){

        HashMap<User, Set<BlockEntity>> usersLikedBlocks = new HashMap<>();
        User user = userRepository.findByUsername(username).isPresent() ? userRepository.findByUsername(username).get() : null;
        usersLikedBlocks.put(user, user.getLikedBlocks());

        return usersLikedBlocks;
    }


}
