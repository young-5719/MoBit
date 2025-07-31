package com.tj703.mobit.service;

import com.tj703.mobit.dto.response.CoinTransactionResponseDto;
import com.tj703.mobit.entity.CoinTransaction;
import com.tj703.mobit.repository.CoinTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoinTransactionService {

    private final CoinTransactionRepository transactionRepository;

    public Page<CoinTransactionResponseDto> getUserTransactions(Integer userNo, int page, int size) {
        int adjustedPage = (page < 1) ? 0 : page - 1;
        Pageable pageable = PageRequest.of(adjustedPage, size, Sort.by(Sort.Direction.DESC, "transactionDate"));

        Page<CoinTransaction> txPage = transactionRepository.findByUser_UserNo(userNo, pageable);

        return txPage.map(tx -> CoinTransactionResponseDto.builder()
                .id(tx.getId())
                .market(tx.getMarket())
                .transactionType(tx.getTransactionType())
                .price(tx.getPrice())
                .transactionCnt(tx.getTransactionCnt())
                .transactionState(tx.getTransactionState())
                .transactionDate(tx.getTransactionDate())
                .build());
    }
}