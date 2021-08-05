package com.dev.userservice.controller;

import com.dev.userservice.bean.AppUserDTO;
import com.dev.userservice.util.CustomResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
public interface UserApi {

    @GetMapping
    CustomResponse<List<AppUserDTO>> getAllUsers();

    @GetMapping(path = "/{userId}")
    CustomResponse<AppUserDTO> getUser(@PathVariable Long userId);

    @PostMapping
    CustomResponse<AppUserDTO> saveUser(@RequestBody AppUserDTO userDTO);

    @DeleteMapping(path = "/{userId}")
    CustomResponse<AppUserDTO> deleteUser(@PathVariable Long userId);

    @PutMapping(path = "/{userId}")
    CustomResponse<AppUserDTO> updateUser(@PathVariable Long userId, @RequestBody AppUserDTO userDTO);
}
