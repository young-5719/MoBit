package com.tj703.mobit.controller;

import com.tj703.mobit.dto.response.CoinTransactionResponseDto;
import com.tj703.mobit.service.CoinTransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/trades")
@RequiredArgsConstructor
@Tag(name = "거래 내역 API", description = "사용자 거래 내역 관련 API")
public class CoinTransactionController {

    private final CoinTransactionService transactionService;

    @Operation(summary = "사용자 거래 내역 페이징 조회", description = "userNo 기준으로 페이지 단위 거래내역을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "거래 내역 조회 성공",
            content = @Content(schema = @Schema(implementation = CoinTransactionResponseDto.class)))
    @GetMapping("/history")
    public ResponseEntity<Page<CoinTransactionResponseDto>> getUserTransactions(
            @Parameter(description = "유저 번호", example = "1")
            @RequestParam Integer userNo,

            @Parameter(description = "페이지 번호 (1부터 시작)", example = "1")
            @RequestParam(defaultValue = "1") int page,

            @Parameter(description = "페이지당 항목 수", example = "20")
            @RequestParam(defaultValue = "20") int size
    ) {
        Page<CoinTransactionResponseDto> transactions = transactionService.getUserTransactions(userNo, page, size);
        return ResponseEntity.ok(transactions);
    }
}