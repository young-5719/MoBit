package com.tj703.mobit.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
// 시장가 매수 요청용 DTO //
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "시장가 매수 요청 DTO")
public class MarketBuyRequestDto {

    @NotNull
    @Schema(description = "유저 번호", example = "1")
    private Integer userNo;

    @NotBlank
    @Schema(description = "마켓 심볼 (예: KRW-BTC)", example = "KRW-BTC")
    private String market;

    @NotNull
    @DecimalMin(value = "0.00000001")
    @Schema(description = "매수 수량", example = "0.0025")
    private BigDecimal quantity;
}