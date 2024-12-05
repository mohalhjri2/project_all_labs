package com.example.projectalllabs.dtos;

import jakarta.validation.constraints.NotBlank;

public record PetNameDTO(
        @NotBlank(message = "Name cannot be blank") String name
) {}
