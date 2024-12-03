package com.example.project_all_labs.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String animalType;

    @NotBlank
    private String breed;

    @Min(0)
    private int age;
}
