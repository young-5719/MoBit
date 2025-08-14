package com.tj703.mobit.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "시장가 매도 요청 DTO")
public class MarketSellRequestDto {

    @NotNull
    @Schema(description = "유저 번호", example = "1")
    private Integer userNo;

    @NotBlank
    @Schema(description = "코인 마켓명 (예: KRW-BTC)", example = "KRW-BTC")
    private String market;

    @NotNull
    @DecimalMin(value = "0.00000001")
    @Schema(description = "매도 수량", example = "0.002")
    private BigDecimal quantity;
}
