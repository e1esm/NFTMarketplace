package com.nft.marketplace.nftmarketplace.repository;

import com.nft.marketplace.nftmarketplace.Entity.Role;
import com.nft.marketplace.nftmarketplace.dto.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

    @Modifying
    @Transactional
    @Query(value = "update user_roles SET role_id = ? WHERE user_id = ?", nativeQuery = true)
    void save(int roleId, int userId);
}
