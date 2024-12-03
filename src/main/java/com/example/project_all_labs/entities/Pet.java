package com.example.project_all_labs.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "household_eircode")
    private Household household;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(name = "animal_type", nullable = false)
    private String animalType;

    @NotBlank
    @Column(nullable = false)
    private String breed;

    @Min(0)
    @Column(nullable = false)
    private int age;

    // Default Constructor
    public Pet() {}

    // Constructor without household
    public Pet(Long id, String name, String animalType, String breed, int age) {
        this.id = id;
        this.name = name;
        this.animalType = animalType;
        this.breed = breed;
        this.age = age;
    }

    // Constructor with all fields
    public Pet(Long id, Household household, String name, String animalType, String breed, int age) {
        this.id = id;
        this.household = household;
        this.name = name;
        this.animalType = animalType;
        this.breed = breed;
        this.age = age;
    }
}
