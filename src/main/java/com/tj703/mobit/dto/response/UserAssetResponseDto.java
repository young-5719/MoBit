package com.tj703.mobit.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class UserAssetResponseDto {
    private String market;
    private BigDecimal quantity;
    private BigDecimal avgPrice;
}
