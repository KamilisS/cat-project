package com.example.cat_project.Cat.Exception;

public class CatNotFoundException extends RuntimeException {
    public CatNotFoundException(Long id) {
        super("Cat with ID " + id + " not found");
    }
}
