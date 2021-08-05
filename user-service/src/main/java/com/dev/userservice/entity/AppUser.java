package com.dev.userservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class AppUser extends BaseEntity {

    private String firstName;
    private String middleName;
    private String lastName;

    private String emailId;
    private String mobileNumber;

}
