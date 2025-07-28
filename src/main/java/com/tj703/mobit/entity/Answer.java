package com.tj703.mobit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_no")
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_no", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Question question;

    @CreationTimestamp
    @Column(name = "answer_created_at", nullable = false, updatable = false)
    private LocalDateTime answerCreatedAt;

    @Column(name = "answer_cont", columnDefinition = "TEXT")
    private String answerCont;
}