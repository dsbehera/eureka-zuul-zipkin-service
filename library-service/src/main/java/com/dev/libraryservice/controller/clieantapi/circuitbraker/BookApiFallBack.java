package com.dev.libraryservice.controller.clieantapi.circuitbraker;

import com.dev.libraryservice.util.CustomResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "${client.server.config.books}", fallback = BookApiFallBack.class)
public class BookApiFallBack {

    @GetMapping(path = "/books")
    public Object getAllBooks() {
        return getDefaultFallBackResponse();
    }

    @GetMapping(path = "/books/{bookId}")
    public Object getBook(Long bookId) {
        return getDefaultFallBackResponse();
    }

    private CustomResponse getDefaultFallBackResponse() {
        return new CustomResponse(false, "Book service is down / we are not able to communicate !!!");
    }
}
