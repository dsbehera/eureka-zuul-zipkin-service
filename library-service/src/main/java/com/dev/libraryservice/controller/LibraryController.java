package com.dev.libraryservice.controller;

import com.dev.libraryservice.bean.LibraryDTO;
import com.dev.libraryservice.service.LibraryService;
import com.dev.libraryservice.util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libraries")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping
    public CustomResponse<List<LibraryDTO>> getAllLibraries() {
        return libraryService.getAllLibraries();
    }

    @GetMapping(path = "/{libraryId}")
    public CustomResponse<LibraryDTO> getLibrary(Long libraryId) {
        return libraryService.getLibrary(libraryId);
    }

    @PostMapping
    public CustomResponse<LibraryDTO> saveLibrary(LibraryDTO libraryDTO) {
        return libraryService.saveLibrary(libraryDTO);
    }

    @DeleteMapping(path = "/{libraryId}")
    public CustomResponse<LibraryDTO> deleteLibrary(Long libraryId) {
        return libraryService.deleteLibrary(libraryId);
    }

    @PutMapping(path = "/{libraryId}")
    public CustomResponse<LibraryDTO> updateLibrary(Long libraryId, LibraryDTO libraryDTO) {
        return libraryService.updateLibrary(libraryId, libraryDTO);
    }

}
