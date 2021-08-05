package com.dev.userservice.service;

import com.dev.userservice.bean.AppUserDTO;
import com.dev.userservice.util.CustomResponse;

import java.util.List;

public interface UserService {

    CustomResponse<List<AppUserDTO>> getAllUsers();

    CustomResponse<AppUserDTO> getUser(Long userId);

    CustomResponse<AppUserDTO> saveUser(AppUserDTO UserDTO);

    CustomResponse<AppUserDTO> deleteUser(Long userId);

    CustomResponse<AppUserDTO> updateUser(Long userId, AppUserDTO UserDTO);
}
