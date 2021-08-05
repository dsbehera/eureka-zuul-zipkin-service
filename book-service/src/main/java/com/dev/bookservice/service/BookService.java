package com.dev.bookservice.service;

import com.dev.bookservice.bean.BookDTO;
import com.dev.bookservice.util.CustomResponse;

import java.util.List;

public interface BookService {

    CustomResponse<List<BookDTO>> getAllBooks();

    CustomResponse<BookDTO> getBook(Long bookId);

    CustomResponse<BookDTO> saveBook(BookDTO bookDTO);

    CustomResponse<BookDTO> deleteBook(Long bookId);

    CustomResponse<BookDTO> updateBook(Long bookId, BookDTO bookDTO);
}
