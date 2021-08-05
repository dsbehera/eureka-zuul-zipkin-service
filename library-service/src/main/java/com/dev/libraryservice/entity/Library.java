package com.dev.libraryservice.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Library extends BaseEntity {

    private String name;
    private String address;
    private Short establishmentYear;

    private String founderName;

}
