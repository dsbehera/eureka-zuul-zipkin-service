package com.dev.userservice.service.impl;

import com.dev.userservice.bean.AppUserDTO;
import com.dev.userservice.entity.AppUser;
import com.dev.userservice.exception.UserNotFoundException;
import com.dev.userservice.repository.AppUserRepository;
import com.dev.userservice.util.CustomResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTests {

    private static final long appUserId = 1L;
    private static final String firstName = "Deepak";

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    AppUserRepository repository;

    @Test
    public void testGetAllAppUsers() {
        when(repository.findAll()).thenReturn(Arrays.asList(new AppUser(), new AppUser()));
        CustomResponse<List<AppUserDTO>> response = userService.getAllUsers();
        Assert.assertEquals(response.getPayLoad().size(), 2);
    }

    @Test
    public void testGetAppUser() {
        AppUser appUser = new AppUser();
        appUser.setId(appUserId);
        when(repository.findById(appUserId)).thenReturn(Optional.of(appUser));
        CustomResponse<AppUserDTO> response = userService.getUser(appUserId);
        Assert.assertEquals(response.getPayLoad().getId().longValue(), appUserId);
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetAppUserWithWrongId() {
        when(repository.findById(appUserId)).thenReturn(Optional.ofNullable(null));
        CustomResponse<AppUserDTO> response = userService.getUser(appUserId);
    }

    @Test
    public void testSaveAppUser() {
        AppUser appUser = new AppUser();
        appUser.setId(appUserId);

        when(repository.save(any(AppUser.class))).thenReturn(appUser);
        CustomResponse<AppUserDTO> response = userService.saveUser(new AppUserDTO());
        Assert.assertEquals(response.getPayLoad().getId().longValue(), appUserId);
    }

    @Test
    public void testDeleteAppUser() {
        when(repository.findById(appUserId)).thenReturn(Optional.of(new AppUser()));
        doNothing().when(repository).delete(any(AppUser.class));
        userService.deleteUser(appUserId);
        verify(repository, times(1)).delete(any(AppUser.class));
    }

    @Test(expected = UserNotFoundException.class)
    public void testDeleteAppUserWithWrongId() {
        when(repository.findById(appUserId)).thenReturn(Optional.ofNullable(null));
        userService.deleteUser(appUserId);
    }

    @Test
    public void testUpdateAppUser() {
        AppUser appUser = new AppUser();
        appUser.setId(appUserId);
        appUser.setFirstName(firstName);
        when(repository.existsById(appUserId)).thenReturn(true);
        when(repository.save(any(AppUser.class))).thenReturn(appUser);

        AppUserDTO appUserDTO = new AppUserDTO();
        appUserDTO.setFirstName(firstName);

        CustomResponse<AppUserDTO> response = userService.updateUser(appUserId, appUserDTO);
        Assert.assertEquals(response.getPayLoad().getId().longValue(), appUserId);
        Assert.assertEquals(response.getPayLoad().getFirstName(), firstName);
    }

    @Test(expected = UserNotFoundException.class)
    public void testUpdateAppUserWithWrongId() {
        AppUserDTO appUserDTO = new AppUserDTO();
        appUserDTO.setFirstName(firstName);

        userService.updateUser(appUserId, appUserDTO);
    }
}

