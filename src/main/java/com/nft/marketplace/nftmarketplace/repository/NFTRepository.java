
package com.nft.marketplace.nftmarketplace.repository;
import com.nft.marketplace.nftmarketplace.Entity.NFTCollectionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface NFTRepository extends CrudRepository<NFTCollectionEntity, Integer> {


    @Transactional
    @Query(value = "SELECT COUNT(*) FROM nftcollection", nativeQuery = true)
    long count();
}


