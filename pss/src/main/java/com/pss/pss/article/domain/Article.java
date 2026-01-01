package com.pss.pss.article.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;

@Entity
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private Category category;

    private Long viewCount;

    private Instant createdAt;

    public Article(String title, String content, String author, Status status, Category category) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.status = status;
        this.category = category;
        this.viewCount = 0L;
        this.createdAt = Instant.now();
    }
}
