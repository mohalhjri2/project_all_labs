package com.example.project_all_labs.controllers;

import com.example.project_all_labs.entities.Household;
import com.example.project_all_labs.services.HouseholdService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class HouseholdControllerTests {

    @Mock
    private HouseholdService householdService;

    @InjectMocks
    private HouseholdController householdController;

    private MockMvc mockMvc;

    private Household sampleHousehold;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(householdController).build();
        sampleHousehold = new Household("D01X123", 3, 5, true);
    }

    @Test
    void testGetAllHouseholds() throws Exception {
        when(householdService.getAllHouseholds()).thenReturn(List.of(sampleHousehold));
        mockMvc.perform(get("/households"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].eircode").value("D01X123"));
    }

    @Test
    void testGetHouseholdByEircode() throws Exception {
        when(householdService.findByEircode("D01X123")).thenReturn(sampleHousehold);
        mockMvc.perform(get("/households/D01X123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eircode").value("D01X123"));
    }

    @Test
    void testCreateHousehold() throws Exception {
        when(householdService.createHousehold(any())).thenReturn(sampleHousehold);
        String householdJson = """
                {
                    "eircode": "D01X123",
                    "numberOfOccupants": 3,
                    "maxNumberOfOccupants": 5,
                    "ownerOccupied": true
                }
                """;
        mockMvc.perform(post("/households")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(householdJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eircode").value("D01X123"));
    }

    @Test
    void testDeleteHousehold() throws Exception {
        doNothing().when(householdService).deleteHousehold("D01X123");
        mockMvc.perform(delete("/households/D01X123"))
                .andExpect(status().isNoContent());
        verify(householdService, times(1)).deleteHousehold("D01X123");
    }
}
