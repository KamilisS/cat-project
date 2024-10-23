package com.example.cat_project.Cat;

import com.example.cat_project.Cat.Adapter.CatDTOToCatAdapter;
import com.example.cat_project.Cat.DTO.CatFormDTO;
import com.example.cat_project.Cat.Entity.Cat;
import com.example.cat_project.Cat.Exception.CatNotFoundException;
import com.example.cat_project.Cat.Repository.CatRepository;
import com.example.cat_project.Cat.Service.CatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CatServiceTest {
    @Mock
    private CatRepository catRepository;

    @InjectMocks
    private CatService catService;

    private Cat cat;
    private CatFormDTO catDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cat = new Cat();
        cat.setName("testname");
        cat.setBreed("testbreed");
        cat.setColor("testcolor");
        cat.setDateOfBirth(LocalDate.now());
        catDTO = new CatFormDTO();
    }

    @Test
    void testSave() {
        catService.save(cat);
        verify(catRepository).saveAndFlush(cat);
    }

    @Test
    void testGetAllCats() {
        Pageable pageable = Pageable.unpaged();
        Page<Cat> catPage = mock(Page.class);
        when(catRepository.findAll(pageable)).thenReturn(catPage);

        Page<Cat> result = catService.getAllCats(pageable);

        assertNotNull(result);
        verify(catRepository).findAll(pageable);
    }

    @Test
    void testGetCatByIdFound() {
        when(catRepository.findById(1L)).thenReturn(Optional.of(cat));

        Cat result = catService.getCatById(1L);

        assertNotNull(result);
        assertEquals(cat.getId(), result.getId());
        verify(catRepository).findById(1L);
    }

    @Test
    void testDeleteCatByIdFound() {
        when(catRepository.findById(1L)).thenReturn(Optional.of(cat));

        catService.deleteCatById(1L);

        verify(catRepository).deleteById(1L);
    }

    @Test
    void testDeleteCatByIdNotFound() {
        when(catRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CatNotFoundException.class, () -> catService.deleteCatById(1L));
        verify(catRepository).findById(1L);
    }

    @Test
    void testDeleteAllCats() {
        catService.deleteAllCats();
        verify(catRepository).deleteAll();
    }

    @Test
    void testSaveFromDTOToEntity() {
        CatDTOToCatAdapter.copy(catDTO, cat);
        when(catRepository.saveAndFlush(cat)).thenReturn(cat);

        Cat result = catService.saveFromDTOToEntity(catDTO, cat);

        assertNotNull(result);
        verify(catRepository).saveAndFlush(cat);
    }
}
