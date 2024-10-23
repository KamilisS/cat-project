package com.example.cat_project.Cat.Adapter;

import com.example.cat_project.Cat.DTO.CatFormDTO;
import com.example.cat_project.Cat.Entity.Cat;

public class CatDTOToCatAdapter {
    public static void copy(CatFormDTO catDTO, Cat cat) {
        cat.setName(catDTO.getName());
        cat.setBreed(catDTO.getBreed());
        cat.setDateOfBirth(catDTO.getDateOfBirth());
        cat.setColor(catDTO.getColor());
    }
}
