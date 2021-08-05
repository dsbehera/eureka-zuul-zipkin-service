package com.dev.bookservice.service.impl;

import com.dev.bookservice.exception.BookNotFoundException;
import com.dev.bookservice.repository.BookRepository;
import com.dev.bookservice.bean.BookDTO;
import com.dev.bookservice.entity.Book;
import com.dev.bookservice.service.BookService;

import com.dev.bookservice.util.CustomResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public CustomResponse<List<BookDTO>> getAllBooks() {
        return new CustomResponse<>(
                true,
                bookRepository.findAll().stream().map(b -> this.toDto(b)).collect(Collectors.toList())
        );
    }

    @Override
    public CustomResponse<BookDTO> getBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("No record found"));

        return new CustomResponse<>(true, this.toDto(book));
    }

    @Override
    public CustomResponse<BookDTO> saveBook(BookDTO bookDTO) {
        Book book = this.fromDto(bookDTO);
        book = bookRepository.save(book);
        return new CustomResponse<>(true, this.toDto(book));
    }

    @Override
    public CustomResponse<BookDTO> deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("No record found"));

        bookRepository.delete(book);
        return new CustomResponse<>(true, this.toDto(book));
    }

    @Override
    public CustomResponse<BookDTO> updateBook(Long bookId, BookDTO bookDTO) {
        if (!bookRepository.existsById(bookId)) {
            throw new BookNotFoundException("No record found");
        }

        Book book = this.fromDto(bookDTO);
        book.setId(bookId);
        bookRepository.save(book);

        bookDTO.setId(bookId);
        return new CustomResponse<BookDTO>(true, bookDTO);
    }

    private BookDTO toDto(Book book) {
        BookDTO bookDTO = new BookDTO();
        BeanUtils.copyProperties(book, bookDTO);
        return bookDTO;
    }

    private Book fromDto(BookDTO bookDTO) {
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO, book);
        return book;
    }

}
