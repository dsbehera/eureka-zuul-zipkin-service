package com.dev.libraryservice.controller.clieantapi.circuitbraker;

import com.dev.libraryservice.util.CustomResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "${client.server.config.users}", fallback = UserApiFallBack.class)
public class UserApiFallBack {

    @GetMapping(path = "/users")
    public Object getAllUsers() {
        return getDefaultFallBackResponse();
    }

    @GetMapping(path = "/users/{userId}")
    public Object getUser(Long userId) {
        return getDefaultFallBackResponse();
    }

    private CustomResponse getDefaultFallBackResponse() {
        return new CustomResponse(false, "User service is down / we are not able to communicate !!!");
    }
}
