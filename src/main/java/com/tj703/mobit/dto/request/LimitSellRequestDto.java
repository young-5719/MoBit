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
@Schema(description = "지정가 매도 요청 DTO")
public class LimitSellRequestDto {

    @NotNull
    @Schema(description = "유저 번호", example = "1")
    private Integer userNo;

    @NotBlank
    @Schema(description = "코인 마켓명", example = "KRW-BTC")
    private String market;

    @NotNull
    @DecimalMin("0.00000001")
    @Schema(description = "매도 수량", example = "0.003")
    private BigDecimal quantity;

    @NotNull
    @DecimalMin("0.01")
    @Schema(description = "지정가 (원)", example = "45000000.00")
    private BigDecimal limitPrice;
}