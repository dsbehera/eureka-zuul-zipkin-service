package com.dev.libraryservice.service.impl;

import com.dev.libraryservice.bean.LibraryDTO;
import com.dev.libraryservice.entity.Library;
import com.dev.libraryservice.exception.LibraryNotFoundException;
import com.dev.libraryservice.repository.LibraryRepository;
import com.dev.libraryservice.service.LibraryService;

import com.dev.libraryservice.util.CustomResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Override
    public CustomResponse<List<LibraryDTO>> getAllLibraries() {
        return new CustomResponse<>(
                true,
                libraryRepository.findAll().stream().map(this::toDto).collect(Collectors.toList())
        );
    }

    @Override
    public CustomResponse<LibraryDTO> getLibrary(Long libraryId) {
        Library library = libraryRepository.findById(libraryId).orElseThrow(libraryNotFoundExceptionSupplier);

        return new CustomResponse<>(true, this.toDto(library));
    }

    @Override
    public CustomResponse<LibraryDTO> saveLibrary(LibraryDTO libraryDTO) {
        Library library = this.fromDto(libraryDTO);
        library = libraryRepository.save(library);
        return new CustomResponse<>(true, this.toDto(library));
    }

    @Override
    public CustomResponse<LibraryDTO> deleteLibrary(Long libraryId) {
        Library library = libraryRepository.findById(libraryId).orElseThrow(libraryNotFoundExceptionSupplier);

        libraryRepository.delete(library);
        return new CustomResponse<>(true, this.toDto(library));
    }

    @Override
    public CustomResponse<LibraryDTO> updateLibrary(Long libraryId, LibraryDTO libraryDTO) {
        if (!libraryRepository.existsById(libraryId)) {
            throw new LibraryNotFoundException("No record found");
        }

        Library library = this.fromDto(libraryDTO);
        library.setId(libraryId);
        libraryRepository.save(library);

        libraryDTO.setId(libraryId);
        return new CustomResponse<>(true, libraryDTO);
    }

    private LibraryDTO toDto(Library library) {
        LibraryDTO libraryDTO = new LibraryDTO();
        BeanUtils.copyProperties(library, libraryDTO);
        return libraryDTO;
    }

    private Library fromDto(LibraryDTO libraryDTO) {
        Library library = new Library();
        BeanUtils.copyProperties(libraryDTO, library);
        return library;
    }

    private Supplier<LibraryNotFoundException> libraryNotFoundExceptionSupplier
            = () -> new LibraryNotFoundException("No record found");

}
