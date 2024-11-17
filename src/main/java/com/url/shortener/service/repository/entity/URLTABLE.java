package com.url.shortener.service.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.lang.NonNull;

@Entity
@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class URLTABLE {
    @NonNull @Id
    private String id;

    @Pattern(regexp = "((http|https)://)(www.)?[a-zA-Z0-9@:%._+~#?&/=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._+~#?&/=]*)")
    @NotBlank
    @Column(name="ORIGINALURL")
    private String originalUrl;

    @NotBlank
    @Column(name="SHORTURL")
    private String shortUrl;
}
