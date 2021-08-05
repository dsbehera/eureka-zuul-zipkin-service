package com.dev.userservice.controller.impl;

import com.dev.userservice.bean.AppUserDTO;
import com.dev.userservice.exception.UserNotFoundException;
import com.dev.userservice.service.UserService;
import com.dev.userservice.util.CustomResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {
    private static final long appUserId = 1l;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    public void testGetAllAppUsers() throws Exception {
        CustomResponse<List<AppUserDTO>> response = new CustomResponse<>(true, Arrays.asList(new AppUserDTO(), new AppUserDTO()));
        when(userService.getAllUsers()).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.payLoad", hasSize(2)));
    }

    @Test
    public void testGetAppUser() throws Exception {
        AppUserDTO appUserDTO = new AppUserDTO();
        appUserDTO.setId(appUserId);

        CustomResponse<AppUserDTO> response = new CustomResponse<>(true, appUserDTO);
        when(userService.getUser(appUserId)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/"+appUserId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.payLoad.id", is((int) appUserId)));
    }

    @Test
    public void testGetAppUserWithWrongId() throws Exception {

        when(userService.getUser(appUserId)).thenThrow(UserNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/"+appUserId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.status", is(false)));
    }

    @Test
    public void testSaveAppUser() throws Exception {
        AppUserDTO appUserDTO = new AppUserDTO();
        appUserDTO.setId(appUserId);

        CustomResponse<AppUserDTO> response = new CustomResponse<>(true, appUserDTO);
        when(userService.saveUser(ArgumentMatchers.any(AppUserDTO.class))).thenReturn(response);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\": \"Deepak\"}")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.payLoad.id", is((int) appUserId)));
    }

    @Test
    public void testDeleteAppUser() throws Exception {
        AppUserDTO appUserDTO = new AppUserDTO();
        appUserDTO.setId(appUserId);

        CustomResponse<AppUserDTO> response = new CustomResponse<>(true, appUserDTO);
        when(userService.deleteUser(appUserId)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/"+appUserId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.payLoad.id", is((int) appUserId)));
    }

    @Test
    public void testUpdateAppUser() throws Exception {
        AppUserDTO appUserDTO = new AppUserDTO();
        appUserDTO.setId(appUserId);

        CustomResponse<AppUserDTO> response = new CustomResponse<>(true, appUserDTO);
        when(userService.updateUser(ArgumentMatchers.anyLong(), ArgumentMatchers.any(AppUserDTO.class))).thenReturn(response);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/users/"+appUserId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\": \"Deepak\"}")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.payLoad.id", is((int) appUserId)));
    }
}
