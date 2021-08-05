package com.dev.libraryservice.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RefreshScope
@RequestMapping("/api/users")
public class UserClientController {

    @Autowired
    private UserClientApi userClientApi;

    @GetMapping
    @HystrixCommand
    public Object getAllUsers() {
        return userClientApi.getAllUsers();
    }

    @GetMapping(path = "/{userId}")
    @HystrixCommand
    public Object getUser(@PathVariable Long userId) {
        return userClientApi.getUser(userId);
    }
}
