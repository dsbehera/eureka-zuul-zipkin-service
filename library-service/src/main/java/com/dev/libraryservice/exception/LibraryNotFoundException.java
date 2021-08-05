package com.dev.libraryservice.exception;

public class LibraryNotFoundException extends RuntimeException {

    public LibraryNotFoundException(String message) {
        super(message);
    }
}
