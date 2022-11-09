
package com.nft.marketplace.nftmarketplace.repository;
import com.nft.marketplace.nftmarketplace.models.NFTCollection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface NFTRepository extends CrudRepository<NFTCollection, Integer> {

}


