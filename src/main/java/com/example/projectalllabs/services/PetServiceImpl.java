package com.example.projectalllabs.services;

import com.example.projectalllabs.dtos.PetDTO;
import com.example.projectalllabs.entities.Household;
import com.example.projectalllabs.entities.Pet;
import com.example.projectalllabs.services.exceptions.BadDataException;
import com.example.projectalllabs.services.exceptions.NotFoundException;
import com.example.projectalllabs.repositories.HouseholdRepository;
import com.example.projectalllabs.repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final HouseholdRepository householdRepository;

    public PetServiceImpl(PetRepository petRepository, HouseholdRepository householdRepository) {
        this.petRepository = petRepository;
        this.householdRepository = householdRepository;
    }

    @Override
    public Pet createPet(PetDTO petDTO) {
        Household household = householdRepository.findById(petDTO.householdEircode())
                .orElseThrow(() -> new NotFoundException("Household not found with eircode: " + petDTO.householdEircode()));

        Pet pet = new Pet();
        pet.setName(petDTO.name());
        pet.setAnimalType(petDTO.animalType());
        pet.setBreed(petDTO.breed());
        pet.setAge(petDTO.age());
        pet.setHousehold(household);

        return petRepository.save(pet);
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet getPetById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pet not found with ID: " + id));
    }

    @Override
    public void deletePetById(Long id) {
        if (!petRepository.existsById(id)) {
            throw new NotFoundException("Pet not found with ID: " + id);
        }
        petRepository.deleteById(id);
    }

    @Override
    public Pet updatePetName(Long id, String name) {
        if (name == null || name.isBlank()) {
            throw new BadDataException("Pet name cannot be blank.");
        }

        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pet not found with ID: " + id));
        pet.setName(name);
        return petRepository.save(pet);
    }
}
