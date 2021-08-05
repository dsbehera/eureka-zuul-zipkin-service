package com.dev.bookservice.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book extends BaseEntity {

    private String title;
    private String description;
    private Short bookEditionYear;

    private String authorName;
    private Integer numberOfPages;
    private String publisher;

}
