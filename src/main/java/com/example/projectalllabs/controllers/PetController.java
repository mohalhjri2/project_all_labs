package com.example.projectalllabs.controllers;

import com.example.projectalllabs.dtos.PetDTO;
import com.example.projectalllabs.dtos.PetNameDTO;
import com.example.projectalllabs.entities.Pet;
import com.example.projectalllabs.services.PetService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
        return ResponseEntity.ok(petService.getPetById(id));
    }

    @PostMapping
    public ResponseEntity<Pet> createPet(@Valid @RequestBody PetDTO petDTO) {
        Pet createdPet = petService.createPet(petDTO);
        return ResponseEntity.ok(createdPet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.deletePetById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<Pet> updatePetName(@PathVariable Long id, @Valid @RequestBody PetNameDTO petNameDTO) {
        Pet updatedPet = petService.updatePetName(id, petNameDTO.name());
        return ResponseEntity.ok(updatedPet);
    }
}
