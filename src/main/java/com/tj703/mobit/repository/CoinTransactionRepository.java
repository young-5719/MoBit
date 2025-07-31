package com.tj703.mobit.repository;

import com.tj703.mobit.entity.CoinTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoinTransactionRepository extends JpaRepository<CoinTransaction, Integer> {

    Page<CoinTransaction> findByUser_UserNo(Integer userNo, Pageable pageable);
}

