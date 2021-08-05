package com.dev.libraryservice.controller.impl;

import com.dev.libraryservice.exception.LibraryNotFoundException;
import com.dev.libraryservice.service.LibraryService;
import com.dev.libraryservice.bean.LibraryDTO;
import com.dev.libraryservice.controller.LibraryController;
import com.dev.libraryservice.util.CustomResponse;
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
@WebMvcTest(LibraryController.class)
public class LibraryControllerTests {

    private static final long libraryId = 1l;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    LibraryService libraryService;

    @Test
    public void testGetAllLibraries() throws Exception {
        CustomResponse<List<LibraryDTO>> response = new CustomResponse<>(true, Arrays.asList(new LibraryDTO(), new LibraryDTO()));
        when(libraryService.getAllLibraries()).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/libraries"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.payLoad", hasSize(2)));
    }

    @Test
    public void testGetLibrary() throws Exception {
        LibraryDTO libraryDTO = new LibraryDTO();
        libraryDTO.setId(libraryId);

        CustomResponse<LibraryDTO> response = new CustomResponse<>(true, libraryDTO);
        when(libraryService.getLibrary(libraryId)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/libraries/"+libraryId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.payLoad.id", is((int) libraryId)));
    }

    @Test
    public void testGetLibraryWithWrongId() throws Exception {

        when(libraryService.getLibrary(libraryId)).thenThrow(LibraryNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/libraries/"+libraryId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.status", is(false)));
    }

    @Test
    public void testSaveLibrary() throws Exception {
        LibraryDTO libraryDTO = new LibraryDTO();
        libraryDTO.setId(libraryId);

        CustomResponse<LibraryDTO> response = new CustomResponse<>(true, libraryDTO);
        when(libraryService.saveLibrary(ArgumentMatchers.any(LibraryDTO.class))).thenReturn(response);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/libraries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"title\": \"Test Title\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.payLoad.id", is((int) libraryId)));
    }

    @Test
    public void testDeleteLibrary() throws Exception {
        LibraryDTO libraryDTO = new LibraryDTO();
        libraryDTO.setId(libraryId);

        CustomResponse<LibraryDTO> response = new CustomResponse<>(true, libraryDTO);
        when(libraryService.deleteLibrary(libraryId)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.delete("/libraries/"+libraryId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.payLoad.id", is((int) libraryId)));
    }

    @Test
    public void testUpdateLibrary() throws Exception {
        LibraryDTO libraryDTO = new LibraryDTO();
        libraryDTO.setId(libraryId);

        CustomResponse<LibraryDTO> response = new CustomResponse<>(true, libraryDTO);
        when(libraryService.updateLibrary(ArgumentMatchers.anyLong(), ArgumentMatchers.any(LibraryDTO.class))).thenReturn(response);

        mockMvc.perform(
                    MockMvcRequestBuilders.put("/libraries/"+libraryId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{ \"title\": \"Test Title\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.payLoad.id", is((int) libraryId)));
    }
}
