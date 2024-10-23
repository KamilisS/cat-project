package com.example.cat_project.Cat.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity(name = "cat")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID of a cat", example = "1")
    private Long id;

    @NotBlank(message = "Name cannot be empty!")
    @Length(min = 2, message = "Name must be at least 2 characters long!")
    @Column(name = "name", nullable = false)
    @Schema(description = "Cat name", example = "Jack")
    private String name;

    @NotBlank(message = "Breed cannot be empty!")
    @Length(min = 2, message = "Breed must be at least 2 characters long!")
    @Column(name = "breed", nullable = false)
    @Schema(description = "Breed of a cat", example = "Maine Coon")
    private String breed;

    @NotBlank(message = "Color cannot be empty!")
    @Length(min = 3, message = "Color must be at least 3 characters long!")
    @Column(name = "color", nullable = false)
    @Schema(description = "Color of the cat", example = "White")
    private String color;

    @NotNull(message = "Birth date cannot be empty!")
    @PastOrPresent(message = "Birth date cannot be in the future!")
    @Column(name = "date_of_birth", nullable = false)
    @Schema(description = "Date of birth of the cat", example = "2020-10-19")
    private LocalDate dateOfBirth;

    // this seems like a redundant field, since age can easily be calculated by using dateOfBirth
    // on the go, so I will not add this to database, but rather calculate it on the go using getter.
    @Transient
    @Schema(description = "Age of the cat", example = "5")
    private Long age;

    public Cat(String name, String breed, String color, LocalDate dateOfBirth) {
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.dateOfBirth = dateOfBirth;
    }

    public Cat() {}

    public Long getId() {
        return id;
    }

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

    public Long getAge() {
        LocalDate now = LocalDate.now();
        return java.time.temporal.ChronoUnit.YEARS.between(this.dateOfBirth, now);
    }
}
