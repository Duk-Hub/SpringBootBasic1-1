package com.pss.pss.global.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public record PageResponse<T> (
        List<T> items,
        PageMeta pageMeta
){
    public static <T> PageResponse<T> of(Page<T> page){
        return new PageResponse<>
                (page.getContent() ,new PageMeta(
                        page.getNumber()+1,
                        page.getSize(),
                        page.getTotalElements(),
                        page.getTotalPages(),
                        page.hasNext(),
                        page.hasPrevious()));
    }
}
