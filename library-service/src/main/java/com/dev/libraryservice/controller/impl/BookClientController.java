package com.dev.libraryservice.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RefreshScope
@RequestMapping("/api/books")
public class BookClientController {

    @Autowired
    private BookClientApi bookClientApi;

    @GetMapping
    @HystrixCommand
    public Object getAllBooks() {
        return bookClientApi.getAllBooks();
    }

    @GetMapping(path = "/{bookId}")
    @HystrixCommand
    public Object getBook(@PathVariable Long bookId) {
        return bookClientApi.getBook(bookId);
    }
}
