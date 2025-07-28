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
@Table(name = "questions")
public class Question {
    @Id
    @Column(name = "question_no", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_no", nullable = false)
    private User userNo;

    @Lob
    @Column(name = "question_cont")
    private String questionCont;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "question_created_at", nullable = false)
    private Instant questionCreatedAt;

    @Size(max = 255)
    @Column(name = "question_title")
    private String questionTitle;

    @Size(max = 20)
    @Column(name = "question_status", length = 20)
    private String questionStatus;

    @Size(max = 20)
    @Column(name = "question_option", length = 20)
    private String questionOption;

}