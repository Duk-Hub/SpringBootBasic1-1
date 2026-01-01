package com.pss.pss.article.dto;

import com.pss.pss.article.domain.Category;
import com.pss.pss.article.domain.Status;

import java.time.Instant;

public record ArticleSummary(
        Long id,
        String title,
        String author,
        Status status,
        Category category,
        Long viewCount,
        Instant createdAt,
        Instant updatedAt
) {
}
