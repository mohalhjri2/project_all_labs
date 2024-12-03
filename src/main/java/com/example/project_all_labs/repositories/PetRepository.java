package com.example.project_all_labs.repositories;

import com.example.project_all_labs.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {

    // Correct method using the actual field name in the entity
    List<Pet> findByAnimalType(String animalType);

    List<Pet> findByBreedOrderByAgeAsc(String breed);

    @Modifying
    @Query("DELETE FROM Pet p WHERE LOWER(p.name) = LOWER(:name)")
    void deletePetsByNameIgnoreCase(String name);

    @Query("SELECT p.name, p.animalType, p.breed FROM Pet p")
    List<Object[]> getNameTypeAndBreed();

    @Query("SELECT AVG(p.age) as avgAge, MAX(p.age) as maxAge FROM Pet p")
    Object[] getPetStatistics();
}
