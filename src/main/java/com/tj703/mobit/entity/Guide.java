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
@Table(name = "guides")
public class Guide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guide_no")
    private Integer id;

    @Column(name = "guide_cont", columnDefinition = "TEXT")
    private String guideCont;

    @CreationTimestamp
    @Column(name = "guide_created_at", nullable = false, updatable = false)
    private LocalDateTime guideCreatedAt;

    @Size(max = 255)
    @Column(name = "guide_title", length = 255)
    private String guideTitle;
}