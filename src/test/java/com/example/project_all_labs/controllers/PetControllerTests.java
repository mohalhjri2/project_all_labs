package com.example.project_all_labs.controllers;

import com.example.project_all_labs.entities.Pet;
import com.example.project_all_labs.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PetControllerTests {

    @Mock
    private PetService petService;

    @InjectMocks
    private PetController petController;

    private MockMvc mockMvc;

    private Pet samplePet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
        samplePet = new Pet(1L, "Buddy", "Dog", "Golden Retriever", 3);
    }

    @Test
    void testGetAllPets() throws Exception {
        when(petService.getAllPets()).thenReturn(List.of(samplePet));
        mockMvc.perform(get("/pets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Buddy"));
    }

    @Test
    void testGetPetById() throws Exception {
        when(petService.getPetById(1L)).thenReturn(samplePet);
        mockMvc.perform(get("/pets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Buddy"));
    }

    @Test
    void testCreatePet() throws Exception {
        when(petService.createPet(any())).thenReturn(samplePet);
        String petJson = """
                {
                    "name": "Buddy",
                    "animalType": "Dog",
                    "breed": "Golden Retriever",
                    "age": 3
                }
                """;
        mockMvc.perform(post("/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(petJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Buddy"));
    }

    @Test
    void testDeletePet() throws Exception {
        doNothing().when(petService).deletePetById(1L);
        mockMvc.perform(delete("/pets/1"))
                .andExpect(status().isNoContent());
        verify(petService, times(1)).deletePetById(1L);
    }
}
