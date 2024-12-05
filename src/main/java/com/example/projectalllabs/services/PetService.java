package com.example.projectalllabs.services;

import com.example.projectalllabs.dtos.PetDTO;
import com.example.projectalllabs.entities.Pet;

import java.util.List;

public interface PetService {

    Pet createPet(PetDTO petDTO);

    List<Pet> getAllPets();

    Pet getPetById(Long id);

    void deletePetById(Long id);

    Pet updatePetName(Long id, String name);
}
