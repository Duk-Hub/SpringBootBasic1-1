package com.pss.pss.article.dto;

import com.pss.pss.article.validator.CategoryCheck;
import com.pss.pss.article.validator.StatusCheck;
import jakarta.validation.constraints.NotBlank;

public record ArticleCreateRequest(
        @NotBlank String title,
        @NotBlank String content,
        @NotBlank String author,
        @NotBlank @StatusCheck String status,
        @NotBlank @CategoryCheck String category
) {
}
