package com.RESTful.demo.controller;

import com.RESTful.demo.book.controller.BookController;
import com.RESTful.demo.book.dto.BookResponse;
import com.RESTful.demo.book.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Test
    void getBookTest() throws Exception {
        //given
        long id = 1L;
        BookResponse response = new BookResponse(id,"제목1","저자1","9788995432105", LocalDate.now());

        given(bookService.getBookById(id)).willReturn(response);

        //when
        mockMvc.perform(get("/api/books/{id}", id))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.title").value("제목1"));
    }

}
