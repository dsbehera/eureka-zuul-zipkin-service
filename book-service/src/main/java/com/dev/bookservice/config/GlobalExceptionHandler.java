package com.dev.bookservice.config;

import com.dev.bookservice.exception.BookNotFoundException;
import com.dev.bookservice.util.CustomResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BookNotFoundException.class)
    protected CustomResponse<String> handleBookNotFound(RuntimeException ex, WebRequest request) {

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);

        CustomResponse<String> response = new CustomResponse(false);
        response.setMessage(ex.getMessage());
        response.setPayLoad(sw.toString());

        return response;
    }
}
