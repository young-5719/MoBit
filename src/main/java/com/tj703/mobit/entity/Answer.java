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
@Table(name = "answers")
public class Answer {
    @Id
    @Column(name = "answer_no", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "question_no", nullable = false)
    private Question questionNo;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "answer_created_at", nullable = false)
    private Instant answerCreatedAt;

    @Lob
    @Column(name = "answer_cont")
    private String answerCont;

}