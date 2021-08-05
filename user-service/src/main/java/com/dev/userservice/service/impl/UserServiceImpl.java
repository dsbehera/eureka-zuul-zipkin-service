package com.dev.userservice.service.impl;

import com.dev.userservice.repository.AppUserRepository;
import com.dev.userservice.bean.AppUserDTO;
import com.dev.userservice.entity.AppUser;
import com.dev.userservice.exception.UserNotFoundException;
import com.dev.userservice.service.UserService;
import com.dev.userservice.util.CustomResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    AppUserRepository userRepository;

    @Override
    public CustomResponse<List<AppUserDTO>> getAllUsers() {
        return new CustomResponse<>(
                true,
                userRepository.findAll().stream().map(b -> this.toDto(b)).collect(Collectors.toList())
        );
    }

    @Override
    public CustomResponse<AppUserDTO> getUser(Long userId) {
        AppUser user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("No record found"));

        return new CustomResponse<>(true, this.toDto(user));
    }

    @Override
    public CustomResponse<AppUserDTO> saveUser(AppUserDTO userDTO) {
        AppUser user = this.fromDto(userDTO);
        user = userRepository.save(user);
        return new CustomResponse<>(true, this.toDto(user));
    }

    @Override
    public CustomResponse<AppUserDTO> deleteUser(Long userId) {
        AppUser user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("No record found"));

        userRepository.delete(user);
        return new CustomResponse<>(true, this.toDto(user));
    }

    @Override
    public CustomResponse<AppUserDTO> updateUser(Long userId, AppUserDTO userDTO) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("No record found");
        }

        AppUser user = this.fromDto(userDTO);
        user.setId(userId);
        userRepository.save(user);

        userDTO.setId(userId);
        return new CustomResponse<AppUserDTO>(true, userDTO);
    }

    private AppUserDTO toDto(AppUser user) {
        AppUserDTO userDTO = new AppUserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    private AppUser fromDto(AppUserDTO userDTO) {
        AppUser user = new AppUser();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }

}
