package com.example.project_all_labs.services;

import com.example.project_all_labs.entities.Pet;
import com.example.project_all_labs.repositories.PetRepository;
import com.example.project_all_labs.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Override
    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet getPetById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pet not found"));
    }

    @Override
    public Pet updatePet(Long id, Pet updatedPet) {
        Pet existingPet = getPetById(id);
        existingPet.setName(updatedPet.getName());
        existingPet.setAge(updatedPet.getAge());
        existingPet.setAnimalType(updatedPet.getAnimalType());
        existingPet.setBreed(updatedPet.getBreed());
        return petRepository.save(existingPet);
    }

    @Override
    public void deletePetById(Long id) {
        petRepository.deleteById(id);
    }

    @Override
    public void deletePetsByName(String name) {
        petRepository.deleteAll(petRepository.findAll().stream()
                .filter(pet -> pet.getName().equalsIgnoreCase(name))
                .toList());
    }

    @Override
    public List<Pet> findPetsByAnimalType(String animalType) {
        return petRepository.findAll().stream()
                .filter(pet -> pet.getAnimalType().equalsIgnoreCase(animalType))
                .toList();
    }

    @Override
    public List<Pet> findPetsByBreed(String breed) {
        return petRepository.findAll().stream()
                .filter(pet -> pet.getBreed().equalsIgnoreCase(breed))
                .sorted((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()))
                .toList();
    }

    @Override
    public List<Object[]> getNameAndBreedOnly() {
        return petRepository.findAll().stream()
                .map(pet -> new Object[]{pet.getName(), pet.getAnimalType(), pet.getBreed()})
                .toList();
    }

    @Override
    public Object getPetStatistics() {
        long count = petRepository.count();
        double avgAge = petRepository.findAll().stream().mapToInt(Pet::getAge).average().orElse(0);
        int maxAge = petRepository.findAll().stream().mapToInt(Pet::getAge).max().orElse(0);
        return new Object[]{count, avgAge, maxAge};
    }
}
