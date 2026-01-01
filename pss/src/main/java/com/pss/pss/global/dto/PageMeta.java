package com.pss.pss.global.dto;

public record PageMeta(
        int pageNumber,
        int size,
        long totalElements,
        int totalPages,
        boolean hasNext,
        boolean hasPrevious
) {
}
