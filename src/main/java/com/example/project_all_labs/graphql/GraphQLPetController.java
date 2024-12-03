package com.example.project_all_labs.graphql;

import com.example.project_all_labs.entities.Pet;
import com.example.project_all_labs.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLPetController {

    @Autowired
    private PetService petService;

    @QueryMapping
    public List<Pet> getAllPetsByAnimalType(String animalType) {
        return petService.findPetsByAnimalType(animalType);
    }

    @QueryMapping
    public Pet getPet(Long id) {
        return petService.getPetById(id);
    }

    @Secured("ROLE_ADMIN")
    @MutationMapping
    public Boolean deletePet(Long id) {
        petService.deletePetById(id);
        return true;
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @QueryMapping
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }
}
