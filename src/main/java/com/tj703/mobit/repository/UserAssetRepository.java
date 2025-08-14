package com.tj703.mobit.repository;

import com.tj703.mobit.entity.User;
import com.tj703.mobit.entity.UserAsset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAssetRepository extends JpaRepository<UserAsset, Integer> {
    List<UserAsset> findByUserUserNo(Integer userNo);
    Optional<UserAsset> findByUserAndMarket(User user, String market);
}