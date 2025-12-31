package com.RESTful.demo.book.service;

import com.RESTful.demo.book.domain.Book;
import com.RESTful.demo.book.dto.*;
import com.RESTful.demo.book.mapper.BookMapper;
import com.RESTful.demo.book.mapper.BookMapper2;
import com.RESTful.demo.book.repository.BookRepository;
import com.RESTful.demo.global.exception.BookNotFoundException;
import com.RESTful.demo.global.exception.DuplicateIsbnException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
//    private final BookMapper2  bookMapper2;

    @Override
    @Transactional
    public BookResponse createBook(BookCreateRequest request){
        if (bookRepository.existsByIsbn(request.isbn())){
            throw new DuplicateIsbnException();
        }
        Book book = BookMapper.createBook(request);
        bookRepository.save(book);
        return BookMapper.createBookResponse(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookSummaryResponse> getBooks(Pageable pageable){
        return bookRepository.findAll(pageable).map(BookMapper::createBookSummaryResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponse getBookById(Long id){
        Book book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        return BookMapper.createBookResponse(book);
    }

    @Override
    @Transactional
    public BookResponse updateBook(Long id, BookUpdateRequest request){
        Book book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        book.updateBook(request);
        return BookMapper.createBookResponse(book);
    }

    @Override
    @Transactional
    public void deleteBook(Long id){
        Book book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        bookRepository.delete(book);
    }
}
