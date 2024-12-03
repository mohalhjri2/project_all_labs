package com.example.project_all_labs.services;

import com.example.project_all_labs.entities.Pet;
import com.example.project_all_labs.repositories.PetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PetRepositoryTest {

    @Autowired
    private PetRepository petRepository;

    @Test
    void testFindByAnimalType() {
        Pet dog = new Pet();
        dog.setName("Buddy");
        dog.setAnimalType("Dog");
        dog.setBreed("Golden Retriever");
        dog.setAge(3);

        petRepository.save(dog);

        List<Pet> dogs = petRepository.findByAnimalType("Dog");
        assertNotNull(dogs);
        assertFalse(dogs.isEmpty());
        assertEquals("Buddy", dogs.get(0).getName());
    }
}
