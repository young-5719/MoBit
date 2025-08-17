package com.tj703.mobit.controller;

import com.tj703.mobit.dto.request.LimitBuyRequestDto;
import com.tj703.mobit.dto.request.LimitSellRequestDto;
import com.tj703.mobit.dto.request.MarketBuyRequestDto;
import com.tj703.mobit.dto.request.MarketSellRequestDto;
import com.tj703.mobit.dto.response.CoinTransactionResponseDto;
import com.tj703.mobit.service.TradeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trades")
@RequiredArgsConstructor
@Tag(name = "거래 API", description = "시장가/지정가 매수/매도 관련 API")
public class TradeController {

    private final TradeService tradeService;

    @Operation(summary = "시장가 매수 주문", description = "현재가로 즉시 체결되는 시장가 매수")
    @PostMapping("/buy/market")
    public ResponseEntity<CoinTransactionResponseDto> marketBuy(
            @Valid @RequestBody MarketBuyRequestDto requestDto) {
        CoinTransactionResponseDto result = tradeService.executeMarketBuy(requestDto);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "시장가 매도 주문", description = "현재가로 즉시 체결되는 시장가 매도 주문")
    @PostMapping("/sell/market")
    public ResponseEntity<CoinTransactionResponseDto> marketSell(
            @Valid @RequestBody MarketSellRequestDto requestDto) {
        CoinTransactionResponseDto result = tradeService.executeMarketSell(requestDto);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "지정가 매수 주문", description = "지정한 가격으로 매수 주문 등록 (체결 전까지 대기)")
    @PostMapping("/buy/limit")
    public ResponseEntity<CoinTransactionResponseDto> limitBuy(
            @Valid @RequestBody LimitBuyRequestDto requestDto) {
        CoinTransactionResponseDto result = tradeService.registerLimitBuyOrder(requestDto);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "지정가 매도 주문", description = "지정한 가격으로 매도 주문 등록 (체결 전까지 대기)")
    @PostMapping("/sell/limit")
    public ResponseEntity<CoinTransactionResponseDto> limitSell(
            @Valid @RequestBody LimitSellRequestDto requestDto) {
        CoinTransactionResponseDto result = tradeService.registerLimitSellOrder(requestDto);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "미체결 주문 목록 조회", description = "userNo를 기준으로 PENDING 상태의 미체결 주문 목록을 조회합니다.")
    @GetMapping("/open-orders")
    public ResponseEntity<Page<CoinTransactionResponseDto>> getOpenOrders(
            @RequestParam Integer userNo,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<CoinTransactionResponseDto> openOrders = tradeService.getOpenOrders(userNo, page, size);
        return ResponseEntity.ok(openOrders);
    }

    @Operation(summary = "미체결 주문 취소", description = "PENDING 상태의 거래만 취소 가능하며 자산은 복구됩니다.")
    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> cancelPendingOrder(@PathVariable Integer transactionId) {
        tradeService.cancelPendingOrder(transactionId);
        return ResponseEntity.noContent().build();
    }
}
