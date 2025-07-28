package com.tj703.mobit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "faq")
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faq_no")
    private Integer id;

    @Column(name = "faq_cont", columnDefinition = "TEXT")
    private String faqCont;

    @CreationTimestamp
    @Column(name = "faq_created_at", nullable = false, updatable = false)
    private LocalDateTime faqCreatedAt;

    @Size(max = 255)
    @Column(name = "faq_title", length = 255)
    private String faqTitle;
}