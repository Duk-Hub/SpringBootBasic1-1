package com.validation.demo.domain;

import com.validation.demo.dto.CreateRequest;
import com.validation.demo.dto.UpdateRequest;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Notice {
    private Integer id;
    private String title;
    private String content;
    private String status;
    private LocalDateTime publishAt;

    public Notice(CreateRequest request) {
        this.id = 1;
        this.title = request.title();
        this.content = request.content();
        this.status = request.status();
        this.publishAt = request.publishAt();
    }

    public void update(UpdateRequest request) {
        if (request.title() != null) {
            this.title = request.title();
        }
        if (request.content() != null) {
            this.content = request.content();
        }
        if (request.status() != null) {
            this.status = request.status();
        }
        if (request.publishAt() != null) {
            this.publishAt = request.publishAt();
        }
    }
}
