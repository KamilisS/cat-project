package com.example.cat_project.Cat.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public class CatFormDTO {

    @NotBlank(message = "Name cannot be empty!")
    @Length(min = 2, message = "Name must be at least 2 characters long!")
    private String name;

    @NotBlank(message = "Breed cannot be empty!")
    @Length(min = 2, message = "Breed must be at least 2 characters long!")
    private String breed;

    @NotBlank(message = "Color cannot be empty!")
    @Length(min = 3, message = "Color must be at least 3 characters long!")
    private String color;

    @PastOrPresent(message = "Birth date cannot be in the future!")
    @NotNull(message = "Birth date cannot be empty!")
    private LocalDate dateOfBirth;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}