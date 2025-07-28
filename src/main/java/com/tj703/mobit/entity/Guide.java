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
@Table(name = "guides")
public class Guide {
    @Id
    @Column(name = "guide_no", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "guide_cont")
    private String guideCont;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "guide_created_at", nullable = false)
    private Instant guideCreatedAt;

    @Size(max = 255)
    @Column(name = "guide_title")
    private String guideTitle;

}