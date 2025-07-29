package com.tj703.mobit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Integer userNo;

    @NotNull
    @Size(max = 30)
    @Column(name = "user_id", nullable = false, unique = true, length = 30)
    private String userId;

    @JsonIgnore
    @Size(max = 255)
    @Column(name = "password")
    private String password;

    @NotNull
    @Size(max = 36)
    @Column(name = "nickname", nullable = false, length = 36)
    private String nickname;

    @NotNull
    @Email
    @Size(max = 100)
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Size(max = 15)
    @Column(name = "phone", length = 15)
    private String phone;

    @NotNull
    @Column(name = "privacy_agreements", nullable = false)
    private Boolean privacyAgreements = false;

    @NotNull
    @Column(name = "marketing_agreements", nullable = false)
    private Boolean marketingAgreements = false;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Size(max = 255)
    @Column(name = "profile_img_url")
    private String profileImgUrl;

    @NotNull
    @Size(max = 20)
    @Column(name = "nationality", nullable = false, length = 20)
    private String nationality = "KOR";

    @NotNull
    @Size(max = 10)
    @Column(name = "gender", nullable = false, length = 10)
    private String gender;

    @NotNull
    @Size(max = 10)
    @Column(name = "theme", nullable = false, length = 10)
    private String theme = "DARK";

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotNull
    @Size(max = 30)
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Size(max = 150)
    @Column(name = "self_introduction", length = 150)
    private String selfIntroduction;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 10)
    private UserRole role = UserRole.USER;

    @Enumerated(EnumType.STRING)
    @Column(name = "oauth", length = 20)
    private OAuthProvider oauth;

    public enum UserRole {
        USER, ADMIN
    }

    public enum OAuthProvider {
        GOOGLE, KAKAO, NAVER, GITHUB
    }
}