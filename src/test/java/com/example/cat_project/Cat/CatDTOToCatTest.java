package com.example.cat_project.Cat;

import com.example.cat_project.Cat.Adapter.CatDTOToCatAdapter;
import com.example.cat_project.Cat.DTO.CatFormDTO;
import com.example.cat_project.Cat.Entity.Cat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CatDTOToCatTest {

    private CatFormDTO catDTO;
    private Cat cat;

    @BeforeEach
    void setUp() {
        catDTO = new CatFormDTO();
        catDTO.setName("Rainė");
        catDTO.setBreed("Maine Coon");
        catDTO.setColor("Gray");
        catDTO.setDateOfBirth(LocalDate.of(2020, 2, 1));

        cat = new Cat();
    }

    @Test
    void testCopyCatDTOToCat() {
        CatDTOToCatAdapter.copy(catDTO, cat);

        assertEquals(catDTO.getName(), cat.getName());
        assertEquals(catDTO.getBreed(), cat.getBreed());
        assertEquals(catDTO.getColor(), cat.getColor());
        assertEquals(catDTO.getDateOfBirth(), cat.getDateOfBirth());
    }

    @Test
    void testCopyWithEmptyCatDTO() {
        CatFormDTO emptyCatDTO = new CatFormDTO();

        CatDTOToCatAdapter.copy(emptyCatDTO, cat);

        assertNull(cat.getName());
        assertNull(cat.getBreed());
        assertNull(cat.getColor());
        assertNull(cat.getDateOfBirth());
    }
}
