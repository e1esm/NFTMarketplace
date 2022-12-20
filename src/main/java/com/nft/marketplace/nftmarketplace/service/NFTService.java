package com.nft.marketplace.nftmarketplace.service;
import com.google.gson.Gson;
import com.nft.marketplace.nftmarketplace.Entity.BlockEntity;
import com.nft.marketplace.nftmarketplace.Entity.NFTCollectionEntity;
import com.nft.marketplace.nftmarketplace.Entity.User;
import com.nft.marketplace.nftmarketplace.Entity.Wallet;
import com.nft.marketplace.nftmarketplace.models.Publication;
import com.nft.marketplace.nftmarketplace.repository.BlockRepository;
import com.nft.marketplace.nftmarketplace.repository.NFTRepository;
import com.nft.marketplace.nftmarketplace.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Block;
import org.springframework.data.jpa.repository.Query;
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

    public void deleteCollection(int id){
        NFTCollectionEntity nftEntity = nftRepository.findById(Integer.valueOf(id)).get();
        nftRepository.deleteById(id);
        for(int i = id; i <= id + nftEntity.getAmount_of_blocks(); ++i){
            blockRepository.deleteById(i);
        }
    }

    public void buyNft(String username, Integer idOfBlock){
        User user = userRepository.findByUsername(username).get();
        BlockEntity blockEntity = blockRepository.findById(idOfBlock).get();
        blockEntity.setIsPurchased(true);
        blockRepository.update(true, blockEntity.getId());
        userRepository.save(user.getId(), blockEntity.getId());
    }

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

        Optional<BlockEntity> block = blockRepository.findById(idOfBlock);
        if(block.isPresent()){
            isSet = true;
            int amount = block.get().getLikes();
            block.get().setLikes(amount + 1);
        }

        return isSet;
    }

    public Map<User, Set<BlockEntity>> getLikedBlocks(String username){

        HashMap<User, Set<BlockEntity>> usersLikedBlocks = new HashMap<>();
        User user = userRepository.findByUsername(username).isPresent() ? userRepository.findByUsername(username).get() : null;
        usersLikedBlocks.put(user, user.getLikedBlocks());

        return usersLikedBlocks;
    }

    public Set<Wallet> getWalletOfSeller(int id){
        BlockEntity blockEntity = blockRepository.findById(id).get();
        User user = blockEntity.getAuthor();
        return user.getWallets();
    }


    public Map<String, Integer> getBlocks(int limit, int pageNum){
         Iterable<NFTCollectionEntity> nftCollectionEntities = nftRepository.findAll();
         Iterator<NFTCollectionEntity> iterator = nftCollectionEntities.iterator();

         ArrayList<NFTCollectionEntity> nftCollectionEntityArrayList = new ArrayList<>();
        iterator.forEachRemaining(nftCollectionEntityArrayList::add);

        ArrayList<String> nftCollectionsJson = new ArrayList<>();

         Gson gson = new Gson();
         if(limit > nftCollectionEntityArrayList.size()){
             for(int i = 0; i < nftCollectionEntityArrayList.size(); ++i){
                 nftCollectionsJson.add(gson.toJson(nftCollectionEntityArrayList.get(i)));
             }
        }else{
             for(int i = pageNum * limit; i < nftCollectionEntityArrayList.size(); ++i){
                 if(nftCollectionEntityArrayList.get(i) == null){
                     break;
                 }else{
                     nftCollectionsJson.add(gson.toJson(nftCollectionEntityArrayList.get(i)));
                 }
             }
         }
        Map<String, Integer> collectionsAndLength = new HashMap<>();
         collectionsAndLength.put(gson.toJson(nftCollectionsJson), nftCollectionsJson.size());
         return collectionsAndLength;
    }

    public String getPreciseBlock(int id){
        Optional<BlockEntity> preciseBlock = blockRepository.findById(id);
        Gson gson = new Gson();
        String jsonRepr = "";
        if(preciseBlock.isPresent()){
            jsonRepr = gson.toJson(preciseBlock.get());
        }
        return jsonRepr;
    }

    public String getPreciseCollection(Integer id){
        NFTCollectionEntity nftCollectionEntity = nftRepository.findById(id).get();
        return new Gson().toJson(nftCollectionEntity);
    }
}
