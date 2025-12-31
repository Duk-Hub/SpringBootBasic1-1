package com.validation.demo.dto;

import com.validation.demo.validator.StatusCheck;
import com.validation.demo.validator.PublishRule;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@PublishRule
public record UpdateRequest(
        @Size(min = 5, max = 50) String title,
        @Size(min = 20, max = 2000) String content,
        @StatusCheck String status,
        LocalDateTime publishAt
) {
    @AssertTrue
    public boolean mustHaveAtLeastOneField() {
        return title != null || content != null || status != null || publishAt != null;
    }
}
