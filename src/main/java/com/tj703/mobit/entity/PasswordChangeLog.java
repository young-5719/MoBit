package com.tj703.mobit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "password_change_logs")
public class PasswordChangeLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "password_log_no")
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_no", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Size(max = 255)
    @Column(name = "changed_password", length = 255)
    private String changedPassword;

    @CreationTimestamp
    @Column(name = "change_date", nullable = false, updatable = false)
    private LocalDateTime changeDate;
}