package com.tj703.mobit.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "notification_settings")
public class NotificationSetting {

    @Id
    @Column(name = "user_no")
    private Integer userNo;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_no", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "volatility_yn", nullable = false)
    private Boolean volatilityYn = false;

    @Column(name = "portfolio_yn", nullable = false)
    private Boolean portfolioYn = false;

    @Column(name = "target_price_yn", nullable = false)
    private Boolean targetPriceYn = false;

    @Column(name = "trade_yn", nullable = false)
    private Boolean tradeYn = false;
}