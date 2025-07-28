package com.tj703.mobit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "target_price_alerts")
public class TargetPriceAlert {
    @Id
    @Column(name = "target_price_alerts_no", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_no", nullable = false)
    private User userNo;

    @Size(max = 50)
    @NotNull
    @Column(name = "market", nullable = false, length = 50)
    private String market;

    @NotNull
    @Column(name = "target_price", nullable = false, precision = 20, scale = 2)
    private BigDecimal targetPrice;

}