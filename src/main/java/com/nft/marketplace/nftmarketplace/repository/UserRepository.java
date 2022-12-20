
package com.nft.marketplace.nftmarketplace.repository;

import java.util.List;
import java.util.Optional;

import com.nft.marketplace.nftmarketplace.Entity.User;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Transactional
    @Query(value = "SELECT ? FROM users where name = ?", nativeQuery = true)
    List<Object> getUser(String substring, String name);


    @Transactional
    @Query(value = "SELECT id FROM users where name = ?", nativeQuery = true)
    Long getUserId(String username);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO bought_blocks (user_id, block_id) VALUES (?,?) ", nativeQuery = true)
    void save(Long user_id, Integer block_id);
}