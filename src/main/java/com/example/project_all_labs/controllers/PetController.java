package com.example.project_all_labs.controllers;

import com.example.project_all_labs.dto.PetDTO;
import com.example.project_all_labs.entities.Pet;
import com.example.project_all_labs.services.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable Long id) {
        return ResponseEntity.ok(petService.getPetById(id));
    }

    @PostMapping
    public ResponseEntity<Pet> createPet(@Valid @RequestBody PetDTO petDTO) {
        Pet pet = new Pet(null, petDTO.getName(), petDTO.getAnimalType(), petDTO.getBreed(), petDTO.getAge());
        return ResponseEntity.ok(petService.createPet(pet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.deletePetById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<Pet> changePetName(@PathVariable Long id, @RequestBody String name) {
        Pet updatedPet = petService.getPetById(id);
        updatedPet.setName(name);
        return ResponseEntity.ok(petService.updatePet(id, updatedPet));
    }
}
