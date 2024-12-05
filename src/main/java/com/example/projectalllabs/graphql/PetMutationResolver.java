package com.example.projectalllabs.graphql;

import com.example.projectalllabs.services.PetService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class PetMutationResolver implements GraphQLMutationResolver {

    private final PetService petService;

    public PetMutationResolver(PetService petService) {
        this.petService = petService;
    }

    public String deletePetById(Long id) {
        petService.deletePetById(id);
        return "Pet with ID " + id + " was deleted.";
    }
}
