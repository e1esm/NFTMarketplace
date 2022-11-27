package com.nft.marketplace.nftmarketplace.repository;

import com.nft.marketplace.nftmarketplace.Entity.BlockEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BlockRepository extends CrudRepository<BlockEntity, Integer> {
}
