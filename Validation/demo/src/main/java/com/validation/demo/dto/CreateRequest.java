package com.validation.demo.dto;

import com.validation.demo.validator.StatusCheck;
import com.validation.demo.validator.PublishRule;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@PublishRule
public record CreateRequest(
        @NotBlank @Size(min = 5, max = 50) String title,
        @NotBlank @Size(min = 20, max = 2000) String content,
        @StatusCheck @NotBlank String status,
        LocalDateTime publishAt
) {
}
