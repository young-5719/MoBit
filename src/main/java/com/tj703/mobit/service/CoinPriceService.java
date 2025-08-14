package com.tj703.mobit.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CoinPriceService {

    public BigDecimal getCurrentPrice(String market) {
        // 실제 구현에서는 Upbit API 호출해서 현재가를 받아옵니다.
        // 예: https://api.upbit.com/v1/ticker?markets=KRW-BTC

        // TODO: Upbit API 연동
        // 현재는 더미값
        return new BigDecimal("42000000.00");
    }
}