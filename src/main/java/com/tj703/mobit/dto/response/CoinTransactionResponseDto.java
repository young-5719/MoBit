package com.tj703.mobit.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "거래 내역 응답 DTO")
public class CoinTransactionResponseDto {
    @Schema(description = "거래 ID", example = "1")
    private Integer id;

    @Schema(description = "코인 마켓명", example = "KRW-BTC")
    private String market;

    @Schema(description = "거래 타입", example = "BUY")
    private String transactionType;

    @Schema(description = "가격", example = "42000000.00")
    private BigDecimal price;

    @Schema(description = "거래 수량", example = "0.00500000")
    private BigDecimal transactionCnt;

    @Schema(description = "거래 상태", example = "COMPLETED")
    private String transactionState;

    @Schema(description = "거래 일시 (서버 시간 기준)", example = "2025-08-01T10:15:30")
    private LocalDateTime transactionDate;
}
