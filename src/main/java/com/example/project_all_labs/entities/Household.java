package com.example.project_all_labs.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "pets")
@Entity
@Table(name = "household")
public class Household {

    @Id
    @NotBlank
    @Size(min = 5, max = 8) // Match VARCHAR(8) in the database
    private String eircode;

    @Min(1)
    @Column(name = "number_of_occupants", nullable = false)
    private int numberOfOccupants;

    @Min(1)
    @Column(name = "max_number_of_occupants", nullable = false)
    private int maxNumberOfOccupants;

    @Column(name = "owner_occupied", nullable = false)
    private boolean ownerOccupied;

    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets = new ArrayList<>();

    // Default Constructor
    public Household() {}

    // Constructor without Pets
    public Household(String eircode, int numberOfOccupants, int maxNumberOfOccupants, boolean ownerOccupied) {
        this.eircode = eircode;
        this.numberOfOccupants = numberOfOccupants;
        this.maxNumberOfOccupants = maxNumberOfOccupants;
        this.ownerOccupied = ownerOccupied;
    }

    // Constructor with all fields
    public Household(String eircode, int numberOfOccupants, int maxNumberOfOccupants, boolean ownerOccupied, List<Pet> pets) {
        this.eircode = eircode;
        this.numberOfOccupants = numberOfOccupants;
        this.maxNumberOfOccupants = maxNumberOfOccupants;
        this.ownerOccupied = ownerOccupied;
        this.pets = pets != null ? pets : new ArrayList<>();
    }

    // Utility methods for managing pets
    public void addPet(Pet pet) {
        pets.add(pet);
        pet.setHousehold(this);
    }

    public void removePet(Pet pet) {
        pets.remove(pet);
        pet.setHousehold(null);
    }
}
