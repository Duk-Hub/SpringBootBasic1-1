package com.pss.pss.article.dto;

import java.time.Instant;

public record ArticleSearchRequest(
        String q,
        String author,
        String category,
        Instant from,
        Instant to
) {
}
