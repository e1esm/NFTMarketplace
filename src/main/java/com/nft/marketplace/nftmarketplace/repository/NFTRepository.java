
package com.nft.marketplace.nftmarketplace.repository;
import com.nft.marketplace.nftmarketplace.Entity.NFTCollectionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NFTRepository extends CrudRepository<NFTCollectionEntity, Integer> {

}


