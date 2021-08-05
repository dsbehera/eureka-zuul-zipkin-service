package com.dev.bookservice.controller.impl;

import com.dev.bookservice.bean.BookDTO;
import com.dev.bookservice.controller.BookController;
import com.dev.bookservice.exception.BookNotFoundException;
import com.dev.bookservice.service.BookService;
import com.dev.bookservice.util.CustomResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTests {

    private static final long bookId = 1l;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @Test
    public void testGetAllBooks() throws Exception {
        CustomResponse<List<BookDTO>> response = new CustomResponse<>(true, Arrays.asList(new BookDTO(), new BookDTO()));
        when(bookService.getAllBooks()).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.payLoad", hasSize(2)));
    }

    @Test
    public void testGetBook() throws Exception {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(bookId);

        CustomResponse<BookDTO> response = new CustomResponse<>(true, bookDTO);
        when(bookService.getBook(bookId)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/books/"+bookId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.payLoad.id", is((int) bookId)));
    }

    @Test
    public void testGetBookWithWrongId() throws Exception {

        when(bookService.getBook(bookId)).thenThrow(BookNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/books/"+bookId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.status", is(false)));
    }

    @Test
    public void testSaveBook() throws Exception {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(bookId);

        CustomResponse<BookDTO> response = new CustomResponse<>(true, bookDTO);
        when(bookService.saveBook(ArgumentMatchers.any(BookDTO.class))).thenReturn(response);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"title\": \"Test Title\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.payLoad.id", is((int) bookId)));
    }

    @Test
    public void testDeleteBook() throws Exception {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(bookId);

        CustomResponse<BookDTO> response = new CustomResponse<>(true, bookDTO);
        when(bookService.deleteBook(bookId)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.delete("/books/"+bookId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.payLoad.id", is((int) bookId)));
    }

    @Test
    public void testUpdateBook() throws Exception {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(bookId);

        CustomResponse<BookDTO> response = new CustomResponse<>(true, bookDTO);
        when(bookService.updateBook(ArgumentMatchers.anyLong(), ArgumentMatchers.any(BookDTO.class))).thenReturn(response);

        mockMvc.perform(
                    MockMvcRequestBuilders.put("/books/"+bookId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{ \"title\": \"Test Title\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.payLoad.id", is((int) bookId)));
    }
}
