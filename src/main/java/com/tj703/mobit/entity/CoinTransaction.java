package com.tj703.mobit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.sql.ConnectionBuilder;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "coin_transactions")
public class CoinTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trans_no")
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_no", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @NotNull
    @Size(max = 50)
    @Column(name = "market", nullable = false, length = 50)
    private String market;

    @NotNull
    @Size(max = 20)
    @Column(name = "transaction_type", nullable = false, length = 20)
    private String transactionType; // 예: BUY, SELL

    @NotNull
    @Column(name = "price", precision = 20, scale = 2, nullable = false)
    private BigDecimal price;

    @NotNull
    @Column(name = "transaction_cnt", precision = 20, scale = 8, nullable = false)
    private BigDecimal transactionCnt;

    @CreationTimestamp
    @Column(name = "transaction_date", nullable = false, updatable = false)
    private LocalDateTime transactionDate;

    @Size(max = 20)
    @Column(name = "transaction_state", length = 20)
    private String transactionState; // 예: PENDING, COMPLETED
}