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
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_no")
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_no", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "question_cont", columnDefinition = "TEXT")
    private String questionCont;

    @CreationTimestamp
    @Column(name = "question_created_at", nullable = false, updatable = false)
    private LocalDateTime questionCreatedAt;

    @Size(max = 255)
    @Column(name = "question_title", length = 255)
    private String questionTitle;

    @Size(max = 20)
    @Column(name = "question_status", length = 20)
    private String questionStatus; // 예: WAITING, ANSWERED

    @Size(max = 20)
    @Column(name = "question_option", length = 20)
    private String questionOption; // 예: GENERAL, TECHNICAL, BUG
}