package com.dev.bookservice.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BookDTO implements Serializable {

    private Long id;
    private String title;
    private String description;
    private Short bookEditionYear;

    private String authorName;
    private Integer numberOfPages;
    private String publisher;
}
