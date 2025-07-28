package com.tj703.mobit.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "notification_settings")
public class NotificationSetting {
    @Id
    @Column(name = "user_no", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_no", nullable = false)
    private User users;

    @ColumnDefault("0")
    @Column(name = "volatility_yn")
    private Boolean volatilityYn;

    @ColumnDefault("0")
    @Column(name = "portfolio_yn")
    private Boolean portfolioYn;

    @ColumnDefault("0")
    @Column(name = "target_price_yn")
    private Boolean targetPriceYn;

    @ColumnDefault("0")
    @Column(name = "trade_yn")
    private Boolean tradeYn;

}