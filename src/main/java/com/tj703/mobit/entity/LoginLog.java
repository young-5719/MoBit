package com.tj703.mobit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "login_logs")
public class LoginLog {
    @Id
    @Column(name = "login_log_no", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_no", nullable = false)
    private User userNo;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "login_date", nullable = false)
    private Instant loginDate;

}