package com.dev.userservice.controller.impl;

import com.dev.userservice.bean.AppUserDTO;
import com.dev.userservice.controller.UserApi;
import com.dev.userservice.service.UserService;
import com.dev.userservice.util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController implements UserApi {

    @Autowired
    private UserService userService;

    @Override
    public CustomResponse<List<AppUserDTO>> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public CustomResponse<AppUserDTO> getUser(Long userId) {
        return userService.getUser(userId);
    }

    @Override
    public CustomResponse<AppUserDTO> saveUser(AppUserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @Override
    public CustomResponse<AppUserDTO> deleteUser(Long userId) {
        return userService.deleteUser(userId);
    }

    @Override
    public CustomResponse<AppUserDTO> updateUser(Long userId, AppUserDTO userDTO) {
        return userService.updateUser(userId, userDTO);
    }

}
