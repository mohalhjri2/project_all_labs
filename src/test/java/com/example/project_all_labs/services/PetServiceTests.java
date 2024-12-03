package com.example.project_all_labs.services;

import com.example.project_all_labs.entities.Pet;
import com.example.project_all_labs.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PetServiceTests {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetServiceImpl petService;

    private Pet samplePet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        samplePet = new Pet(1L, "Buddy", "Dog", "Golden Retriever", 3);
    }

    @Test
    void testGetAllPets() {
        when(petRepository.findAll()).thenReturn(List.of(samplePet));
        List<Pet> pets = petService.getAllPets();
        assertEquals(1, pets.size());
        assertEquals("Buddy", pets.get(0).getName());
    }

    @Test
    void testGetPetById() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(samplePet));
        Pet pet = petService.getPetById(1L);
        assertNotNull(pet);
        assertEquals("Buddy", pet.getName());
    }

    @Test
    void testCreatePet() {
        when(petRepository.save(samplePet)).thenReturn(samplePet);
        Pet createdPet = petService.createPet(samplePet);
        assertNotNull(createdPet);
        assertEquals("Buddy", createdPet.getName());
    }

    @Test
    void testDeletePet() {
        doNothing().when(petRepository).deleteById(1L);
        assertDoesNotThrow(() -> petService.deletePetById(1L));
        verify(petRepository, times(1)).deleteById(1L);
    }
}
