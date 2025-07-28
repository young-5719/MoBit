package com.tj703.mobit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "coin_transactions")
public class CoinTransaction {
    @Id
    @Column(name = "trans_no", nullable = false)
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

    @Size(max = 20)
    @NotNull
    @Column(name = "transaction_type", nullable = false, length = 20)
    private String transactionType;

    @NotNull
    @Column(name = "price", nullable = false, precision = 20, scale = 2)
    private BigDecimal price;

    @NotNull
    @Column(name = "transaction_cnt", nullable = false, precision = 20, scale = 8)
    private BigDecimal transactionCnt;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "transaction_date", nullable = false)
    private Instant transactionDate;

    @Size(max = 20)
    @Column(name = "transaction_state", length = 20)
    private String transactionState;

}