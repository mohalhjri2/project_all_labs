package com.example.projectalllabs.graphql;

import com.example.projectalllabs.entities.Pet;
import com.example.projectalllabs.services.PetService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PetQueryResolver implements GraphQLQueryResolver {

    private final PetService petService;

    public PetQueryResolver(PetService petService) {
        this.petService = petService;
    }

    public List<Pet> getAllPetsByAnimalType(String animalType) {
        return petService.getAllPets().stream()
                .filter(pet -> pet.getAnimalType().equalsIgnoreCase(animalType))
                .toList();
    }

    public Pet getPet(Long id) {
        return petService.getPetById(id);
    }
}
