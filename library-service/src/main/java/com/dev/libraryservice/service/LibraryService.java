package com.dev.libraryservice.service;

import com.dev.libraryservice.bean.LibraryDTO;
import com.dev.libraryservice.util.CustomResponse;

import java.util.List;

public interface LibraryService {

    CustomResponse<List<LibraryDTO>> getAllLibraries();

    CustomResponse<LibraryDTO> getLibrary(Long libraryId);

    CustomResponse<LibraryDTO> saveLibrary(LibraryDTO libraryDTO);

    CustomResponse<LibraryDTO> deleteLibrary(Long libraryId);

    CustomResponse<LibraryDTO> updateLibrary(Long libraryId, LibraryDTO libraryDTO);
}
