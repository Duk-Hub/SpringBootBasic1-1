package com.RESTful.demo.book.mapper;

import com.RESTful.demo.book.domain.Book;
import com.RESTful.demo.book.dto.BookCreateRequest;
import com.RESTful.demo.book.dto.BookResponse;
import com.RESTful.demo.book.dto.BookSummaryResponse;
import com.RESTful.demo.book.dto.BookUpdateRequest;
import com.RESTful.demo.global.config.MapStructConfig;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface BookMapper2 {

    Book toEntity(BookCreateRequest request);

    BookResponse toResponse(Book book);

    List<BookSummaryResponse> toResponseList(List<Book> books);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBook(BookUpdateRequest request, @MappingTarget Book book);

}
