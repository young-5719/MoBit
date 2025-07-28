package com.tj703.mobit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "password_change_logs")
public class PasswordChangeLog {
    @Id
    @Column(name = "password_log_no", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_no", nullable = false)
    private User userNo;

    @Size(max = 255)
    @Column(name = "changed_password")
    private String changedPassword;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "change_date", nullable = false)
    private Instant changeDate;

}