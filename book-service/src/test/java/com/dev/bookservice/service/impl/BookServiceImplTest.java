package com.dev.bookservice.service.impl;

import com.dev.bookservice.bean.BookDTO;
import com.dev.bookservice.entity.Book;
import com.dev.bookservice.exception.BookNotFoundException;
import com.dev.bookservice.repository.BookRepository;
import com.dev.bookservice.util.CustomResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

    private static final long bookId = 1L;
    private static final String bookTitle = "Test Book";

    @InjectMocks
    BookServiceImpl bookService;

    @Mock
    BookRepository repository;

    @Test
    public void testGetAllBooks() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Book(), new Book()));
        CustomResponse<List<BookDTO>> response = bookService.getAllBooks();
        Assert.assertEquals(response.getPayLoad().size(), 2);
    }

    @Test
    public void testGetBook() {
        Book book = new Book();
        book.setId(bookId);
        when(repository.findById(bookId)).thenReturn(Optional.of(book));
        CustomResponse<BookDTO> response = bookService.getBook(bookId);
        Assert.assertEquals(response.getPayLoad().getId().longValue(), bookId);
    }

    @Test(expected = BookNotFoundException.class)
    public void testGetBookWithWrongId() {
        when(repository.findById(bookId)).thenReturn(Optional.ofNullable(null));
        CustomResponse<BookDTO> response = bookService.getBook(bookId);
    }

    @Test
    public void testSaveBook() {
        Book book = new Book();
        book.setId(bookId);

        when(repository.save(any(Book.class))).thenReturn(book);
        CustomResponse<BookDTO> response = bookService.saveBook(new BookDTO());
        Assert.assertEquals(response.getPayLoad().getId().longValue(), bookId);
    }

    @Test
    public void testDeleteBook() {
        when(repository.findById(bookId)).thenReturn(Optional.of(new Book()));
        doNothing().when(repository).delete(any(Book.class));
        bookService.deleteBook(bookId);
        verify(repository, times(1)).delete(any(Book.class));
    }

    @Test(expected = BookNotFoundException.class)
    public void testDeleteBookWithWrongId() {
        when(repository.findById(bookId)).thenReturn(Optional.ofNullable(null));
        bookService.deleteBook(bookId);
    }

    @Test
    public void testUpdateBook() {
        Book book = new Book();
        book.setId(bookId);
        book.setTitle(bookTitle);
        when(repository.existsById(bookId)).thenReturn(true);
        when(repository.save(any(Book.class))).thenReturn(book);

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(bookTitle);

        CustomResponse<BookDTO> response = bookService.updateBook(bookId, bookDTO);
        Assert.assertEquals(response.getPayLoad().getId().longValue(), bookId);
        Assert.assertEquals(response.getPayLoad().getTitle(), bookTitle);
    }

    @Test(expected = BookNotFoundException.class)
    public void testUpdateBookWithWrongId() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(bookTitle);

        bookService.updateBook(bookId, bookDTO);
    }
}
