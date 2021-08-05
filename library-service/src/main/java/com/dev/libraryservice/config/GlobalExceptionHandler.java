package com.dev.libraryservice.config;

import com.dev.libraryservice.exception.LibraryNotFoundException;
import com.dev.libraryservice.util.CustomResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = LibraryNotFoundException.class)
    protected CustomResponse<String> handleLibraryNotFound(RuntimeException ex, WebRequest request) {

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);

        CustomResponse<String> response = new CustomResponse(false);
        response.setMessage(ex.getMessage());
        response.setPayLoad(sw.toString());

        return response;
    }

}
