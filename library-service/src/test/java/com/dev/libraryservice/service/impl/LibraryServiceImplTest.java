package com.dev.libraryservice.service.impl;

import com.dev.libraryservice.entity.Library;
import com.dev.libraryservice.exception.LibraryNotFoundException;
import com.dev.libraryservice.util.CustomResponse;
import com.dev.libraryservice.bean.LibraryDTO;
import com.dev.libraryservice.repository.LibraryRepository;
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
public class LibraryServiceImplTest {

    private static final long libraryId = 1L;
    private static final String libraryName = "Test Book";

    @InjectMocks
    LibraryServiceImpl libraryService;

    @Mock
    LibraryRepository repository;

    @Test
    public void testGetAllLibraries() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Library(), new Library()));
        CustomResponse<List<LibraryDTO>> response = libraryService.getAllLibraries();
        Assert.assertEquals(response.getPayLoad().size(), 2);
    }

    @Test
    public void testGetLibrary() {
        Library library = new Library();
        library.setId(libraryId);
        when(repository.findById(libraryId)).thenReturn(Optional.of(library));
        CustomResponse<LibraryDTO> response = libraryService.getLibrary(libraryId);
        Assert.assertEquals(response.getPayLoad().getId().longValue(), libraryId);
    }

    @Test(expected = LibraryNotFoundException.class)
    public void testGetLibraryWithWrongId() {
        when(repository.findById(libraryId)).thenReturn(Optional.ofNullable(null));
        CustomResponse<LibraryDTO> response = libraryService.getLibrary(libraryId);
    }

    @Test
    public void testSaveLibrary() {
        Library library = new Library();
        library.setId(libraryId);

        when(repository.save(any(Library.class))).thenReturn(library);
        CustomResponse<LibraryDTO> response = libraryService.saveLibrary(new LibraryDTO());
        Assert.assertEquals(response.getPayLoad().getId().longValue(), libraryId);
    }

    @Test
    public void testDeleteLibrary() {
        when(repository.findById(libraryId)).thenReturn(Optional.of(new Library()));
        doNothing().when(repository).delete(any(Library.class));
        libraryService.deleteLibrary(libraryId);
        verify(repository, times(1)).delete(any(Library.class));
    }

    @Test(expected = LibraryNotFoundException.class)
    public void testDeleteLibraryWithWrongId() {
        when(repository.findById(libraryId)).thenReturn(Optional.ofNullable(null));
        libraryService.deleteLibrary(libraryId);
    }

    @Test
    public void testUpdateLibrary() {
        Library library = new Library();
        library.setId(libraryId);
        library.setName(libraryName);
        when(repository.existsById(libraryId)).thenReturn(true);
        when(repository.save(any(Library.class))).thenReturn(library);

        LibraryDTO libraryDTO = new LibraryDTO();
        libraryDTO.setName(libraryName);

        CustomResponse<LibraryDTO> response = libraryService.updateLibrary(libraryId, libraryDTO);
        Assert.assertEquals(response.getPayLoad().getId().longValue(), libraryId);
        Assert.assertEquals(response.getPayLoad().getName(), libraryName);
    }

    @Test(expected = LibraryNotFoundException.class)
    public void testUpdateLibraryWithWrongId() {
        LibraryDTO libraryDTO = new LibraryDTO();
        libraryDTO.setName(libraryName);

        libraryService.updateLibrary(libraryId, libraryDTO);
    }
}
