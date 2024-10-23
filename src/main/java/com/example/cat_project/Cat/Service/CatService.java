package com.example.cat_project.Cat.Service;

import com.example.cat_project.Cat.Adapter.CatDTOToCatAdapter;
import com.example.cat_project.Cat.DTO.CatFormDTO;
import com.example.cat_project.Cat.Entity.Cat;
import com.example.cat_project.Cat.Exception.CatNotFoundException;
import com.example.cat_project.Cat.Repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CatService {

    private final CatRepository catRepository;

    @Autowired
    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public Page<Cat> getAllCats(Pageable pageable) {
        return catRepository.findAll(pageable);
    }

    public Cat getCatById(Long id) {
        return this.catRepository.findById(id).orElseThrow(()
                -> new CatNotFoundException(id));
    }

    public void deleteCatById(Long id) {
        this.catRepository.findById(id).orElseThrow(()
                -> new CatNotFoundException(id));
        catRepository.deleteById(id);
    }

    public void deleteAllCats() {
        catRepository.deleteAll();
    }

    public Cat saveFromDTOToEntity(CatFormDTO catDTO, Cat cat) {
        CatDTOToCatAdapter.copy(catDTO, cat);
        this.save(cat);
        return cat;
    }

    public void save(Cat cat) {
        this.catRepository.saveAndFlush(cat);
    }
}
