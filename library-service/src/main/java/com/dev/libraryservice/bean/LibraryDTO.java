package com.dev.libraryservice.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LibraryDTO implements Serializable {

    private Long id;
    private String name;
    private String address;
    private Short establishmentYear;

    private String founderName;
}
