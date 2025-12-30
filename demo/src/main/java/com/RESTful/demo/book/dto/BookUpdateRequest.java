package com.RESTful.demo.book.dto;


import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Size;

public record BookUpdateRequest(
        @Size(min = 1, max = 50) String title,
        @Size(min = 1, max = 30) String author
) {
    @AssertTrue(message = "하나 이상의 수정값이 필요합니다")
    public boolean fieldsHaveAtLeastOneField() {
        return title != null || author != null;
    }

    @AssertTrue(message = "제목은 공백이어서는 안됩니다.")
    public boolean isNotBlankIfTitleExists(){
        return title != null || !title.isBlank();
    }

    @AssertTrue(message = "저자는 공백이어서는 안됩니다.")
    public boolean isNotBlankIfAuthorExists(){
        return author != null || !author.isBlank();
    }

}
