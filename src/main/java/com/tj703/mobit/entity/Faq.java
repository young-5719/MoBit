package com.tj703.mobit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "faq")
public class Faq {
    @Id
    @Column(name = "faq_no", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "faq_cont")
    private String faqCont;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "faq_created_at", nullable = false)
    private Instant faqCreatedAt;

    @Size(max = 255)
    @Column(name = "faq_title")
    private String faqTitle;

}