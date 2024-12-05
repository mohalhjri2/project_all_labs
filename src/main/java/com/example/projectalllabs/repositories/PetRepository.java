package com.example.projectalllabs.repositories;

import com.example.projectalllabs.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByAnimalType(String animalType);
    List<Pet> findByBreedOrderByAge(String breed);
    List<Pet> findByNameIgnoreCase(String name);
}

