package com.tj703.mobit.service;

import com.tj703.mobit.dto.request.LimitBuyRequestDto;
import com.tj703.mobit.dto.request.LimitSellRequestDto;
import com.tj703.mobit.dto.request.MarketBuyRequestDto;
import com.tj703.mobit.dto.request.MarketSellRequestDto;
import com.tj703.mobit.dto.response.CoinTransactionResponseDto;
import com.tj703.mobit.entity.CoinTransaction;
import com.tj703.mobit.entity.User;
import com.tj703.mobit.entity.UserAsset;
import com.tj703.mobit.repository.CoinTransactionRepository;
import com.tj703.mobit.repository.UserAssetRepository;
import com.tj703.mobit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TradeService {

    private final CoinPriceService coinPriceService;
    private final CoinTransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final UserAssetRepository userAssetRepository;

    // 시장가 매수 //
    @Transactional
    public CoinTransactionResponseDto executeMarketBuy(MarketBuyRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserNo())
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        BigDecimal currentPrice = coinPriceService.getCurrentPrice(requestDto.getMarket());
        BigDecimal totalCost = currentPrice.multiply(requestDto.getQuantity());

        UserAsset krwAsset = userAssetRepository.findByUserAndMarket(user, "KRW")
                .orElseThrow(() -> new RuntimeException("KRW 자산이 부족하거나 존재하지 않습니다."));

        if (krwAsset.getQuantity().compareTo(totalCost) < 0) {
            throw new RuntimeException("보유 KRW가 부족합니다.");
        }

        krwAsset.setQuantity(krwAsset.getQuantity().subtract(totalCost));
        userAssetRepository.save(krwAsset);

        UserAsset coinAsset = userAssetRepository.findByUserAndMarket(user, requestDto.getMarket())
                .orElse(new UserAsset(user, requestDto.getMarket(), BigDecimal.ZERO));

        coinAsset.setQuantity(coinAsset.getQuantity().add(requestDto.getQuantity()));
        coinAsset.setAvgPrice(currentPrice);
        userAssetRepository.save(coinAsset);

        CoinTransaction transaction = CoinTransaction.builder()
                .user(user)
                .market(requestDto.getMarket())
                .transactionType("BUY")
                .transactionState("COMPLETED")
                .price(currentPrice)
                .transactionCnt(requestDto.getQuantity())
                .transactionDate(LocalDateTime.now())
                .build();

        CoinTransaction saved = transactionRepository.save(transaction);

        return CoinTransactionResponseDto.builder()
                .id(saved.getId())
                .market(saved.getMarket())
                .transactionType(saved.getTransactionType())
                .price(saved.getPrice())
                .transactionCnt(saved.getTransactionCnt())
                .transactionState(saved.getTransactionState())
                .transactionDate(saved.getTransactionDate())
                .build();
    }

    // 시장가 매도 //
    @Transactional
    public CoinTransactionResponseDto executeMarketSell(MarketSellRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserNo())
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        BigDecimal currentPrice = coinPriceService.getCurrentPrice(requestDto.getMarket());
        BigDecimal totalRevenue = currentPrice.multiply(requestDto.getQuantity());

        UserAsset coinAsset = userAssetRepository.findByUserAndMarket(user, requestDto.getMarket())
                .orElseThrow(() -> new RuntimeException("보유한 코인이 없습니다."));

        if (coinAsset.getQuantity().compareTo(requestDto.getQuantity()) < 0) {
            throw new RuntimeException("보유 수량이 부족합니다.");
        }

        // 보유 코인 차감
        coinAsset.setQuantity(coinAsset.getQuantity().subtract(requestDto.getQuantity()));
        userAssetRepository.save(coinAsset);

        // KRW 자산 증가
        UserAsset krwAsset = userAssetRepository.findByUserAndMarket(user, "KRW")
                .orElse(new UserAsset(user, "KRW", BigDecimal.ZERO));

        krwAsset.setQuantity(krwAsset.getQuantity().add(totalRevenue));
        userAssetRepository.save(krwAsset);

        // 거래 기록 저장
        CoinTransaction transaction = CoinTransaction.builder()
                .user(user)
                .market(requestDto.getMarket())
                .transactionType("SELL")
                .transactionState("COMPLETED")
                .price(currentPrice)
                .transactionCnt(requestDto.getQuantity())
                .transactionDate(LocalDateTime.now())
                .build();

        CoinTransaction saved = transactionRepository.save(transaction);

        return CoinTransactionResponseDto.builder()
                .id(saved.getId())
                .market(saved.getMarket())
                .transactionType(saved.getTransactionType())
                .price(saved.getPrice())
                .transactionCnt(saved.getTransactionCnt())
                .transactionState(saved.getTransactionState())
                .transactionDate(saved.getTransactionDate())
                .build();
    }

    // 지정가 매수 //
    @Transactional
    public CoinTransactionResponseDto registerLimitBuyOrder(LimitBuyRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserNo())
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        // 총 금액 계산 = 지정가 * 수량
        BigDecimal totalCost = requestDto.getLimitPrice().multiply(requestDto.getQuantity());

        // KRW 자산 확인 및 선 차감
        UserAsset krwAsset = userAssetRepository.findByUserAndMarket(user, "KRW")
                .orElseThrow(() -> new RuntimeException("KRW 자산이 부족하거나 존재하지 않습니다."));

        if (krwAsset.getQuantity().compareTo(totalCost) < 0) {
            throw new RuntimeException("보유 KRW가 부족합니다.");
        }

        krwAsset.setQuantity(krwAsset.getQuantity().subtract(totalCost));
        userAssetRepository.save(krwAsset);

        // 거래 저장 (체결되지 않음 → PENDING 상태)
        CoinTransaction transaction = CoinTransaction.builder()
                .user(user)
                .market(requestDto.getMarket())
                .transactionType("BUY")
                .transactionState("PENDING")
                .price(requestDto.getLimitPrice()) // 지정가
                .transactionCnt(requestDto.getQuantity())
                .transactionDate(LocalDateTime.now())
                .build();

        CoinTransaction saved = transactionRepository.save(transaction);

        return CoinTransactionResponseDto.builder()
                .id(saved.getId())
                .market(saved.getMarket())
                .transactionType(saved.getTransactionType())
                .price(saved.getPrice())
                .transactionCnt(saved.getTransactionCnt())
                .transactionState(saved.getTransactionState())
                .transactionDate(saved.getTransactionDate())
                .build();
    }

    // 지정가 매도 //
    @Transactional
    public CoinTransactionResponseDto registerLimitSellOrder(LimitSellRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserNo())
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        UserAsset coinAsset = userAssetRepository.findByUserAndMarket(user, requestDto.getMarket())
                .orElseThrow(() -> new RuntimeException("보유한 코인이 없습니다."));

        if (coinAsset.getQuantity().compareTo(requestDto.getQuantity()) < 0) {
            throw new RuntimeException("보유 수량이 부족합니다.");
        }

        // 보유 코인 수량 차감 (선차감 방식)
        coinAsset.setQuantity(coinAsset.getQuantity().subtract(requestDto.getQuantity()));
        userAssetRepository.save(coinAsset);

        CoinTransaction transaction = CoinTransaction.builder()
                .user(user)
                .market(requestDto.getMarket())
                .transactionType("SELL")
                .transactionState("PENDING")
                .price(requestDto.getLimitPrice())
                .transactionCnt(requestDto.getQuantity())
                .transactionDate(LocalDateTime.now())
                .build();

        CoinTransaction saved = transactionRepository.save(transaction);

        return CoinTransactionResponseDto.builder()
                .id(saved.getId())
                .market(saved.getMarket())
                .transactionType(saved.getTransactionType())
                .price(saved.getPrice())
                .transactionCnt(saved.getTransactionCnt())
                .transactionState(saved.getTransactionState())
                .transactionDate(saved.getTransactionDate())
                .build();
    }

    // 미체결 목록 조회  //
    @Transactional(readOnly = true)
    public Page<CoinTransactionResponseDto> getOpenOrders(Integer userNo, int page, int size) {
        int adjustedPage = Math.max(0, page - 1);
        Pageable pageable = PageRequest.of(adjustedPage, size, Sort.by(Sort.Direction.DESC, "transactionDate"));

        Page<CoinTransaction> result = transactionRepository.findByUser_UserNoAndTransactionState(
                userNo, "PENDING", pageable);

        return result.map(tx -> CoinTransactionResponseDto.builder()
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