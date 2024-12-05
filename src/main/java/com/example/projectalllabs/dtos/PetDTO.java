package com.example.projectalllabs.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record PetDTO(
        @NotBlank(message = "Name cannot be blank") String name,
        @NotBlank(message = "Animal type cannot be blank") String animalType,
        @NotBlank(message = "Breed cannot be blank") String breed,
        @Min(value = 0, message = "Age must be non-negative") int age,
        @NotBlank(message = "Household Eircode cannot be blank") String householdEircode
) {}
