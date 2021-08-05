package com.dev.libraryservice.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponse<T> implements Serializable {
    private static final long serialVersionUID = 56722L;

    private Boolean status;
    private String message;
    private T payLoad;

    public CustomResponse(boolean status) {
        super();
        this.status = status;
    }

    public CustomResponse(boolean status, T payLoad) {
        super();
        this.status = status;
        this.payLoad = payLoad;
    }
    public CustomResponse(boolean status, String message) {
        super();
        this.status = status;
        this.message = message;
    }
}
