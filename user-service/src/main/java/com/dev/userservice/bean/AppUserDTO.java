package com.dev.userservice.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AppUserDTO implements Serializable {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;

    private String emailId;
    private String mobileNumber;
}
