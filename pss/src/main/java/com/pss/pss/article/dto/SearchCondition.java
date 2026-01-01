package com.pss.pss.article.dto;

import com.pss.pss.article.domain.Category;

import java.time.Instant;

public record SearchCondition(
        String q,
        String author,
        Category category,
        Instant from,
        Instant to
) {
}
