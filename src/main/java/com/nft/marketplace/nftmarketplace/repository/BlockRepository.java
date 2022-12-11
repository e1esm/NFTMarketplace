package com.nft.marketplace.nftmarketplace.repository;

import com.nft.marketplace.nftmarketplace.Entity.BlockEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.beans.Transient;


@Repository
public interface BlockRepository extends CrudRepository<BlockEntity, Integer> {


}
