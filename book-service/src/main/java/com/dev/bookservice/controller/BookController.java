package com.dev.bookservice.controller;

import com.dev.bookservice.bean.BookDTO;
import com.dev.bookservice.service.BookService;
import com.dev.bookservice.util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public CustomResponse<List<BookDTO>> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(path = "/{bookId}")
    public CustomResponse<BookDTO> getBook(Long bookId) {
        return bookService.getBook(bookId);
    }

    @PostMapping
    public CustomResponse<BookDTO> saveBook(BookDTO bookDTO) {
        return bookService.saveBook(bookDTO);
    }

    @DeleteMapping(path = "/{bookId}")
    public CustomResponse<BookDTO> deleteBook(Long bookId) {
        return bookService.deleteBook(bookId);
    }

    @PutMapping(path = "/{bookId}")
    public CustomResponse<BookDTO> updateBook(Long bookId, BookDTO bookDTO) {
        return bookService.updateBook(bookId, bookDTO);
    }

}
