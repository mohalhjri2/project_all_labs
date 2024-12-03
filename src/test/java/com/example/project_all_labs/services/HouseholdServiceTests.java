package com.example.project_all_labs.services;

import com.example.project_all_labs.entities.Household;
import com.example.project_all_labs.repositories.HouseholdRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HouseholdServiceTests {

    @Mock
    private HouseholdRepository householdRepository;

    @InjectMocks
    private HouseholdServiceImpl householdService;

    private Household sampleHousehold;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleHousehold = new Household("D01X123", 3, 5, true);
    }

    @Test
    void testGetAllHouseholds() {
        when(householdRepository.findAll()).thenReturn(List.of(sampleHousehold));
        List<Household> households = householdService.getAllHouseholds();
        assertEquals(1, households.size());
        assertEquals("D01X123", households.get(0).getEircode());
    }

    @Test
    void testFindByEircode() {
        when(householdRepository.findById("D01X123")).thenReturn(Optional.of(sampleHousehold));
        Household household = householdService.findByEircode("D01X123");
        assertNotNull(household);
        assertEquals(3, household.getNumberOfOccupants());
    }

    @Test
    void testCreateHousehold() {
        when(householdRepository.save(sampleHousehold)).thenReturn(sampleHousehold);
        Household createdHousehold = householdService.createHousehold(sampleHousehold);
        assertNotNull(createdHousehold);
        assertEquals("D01X123", createdHousehold.getEircode());
    }

    @Test
    void testFindHouseholdsWithNoPets() {
        when(householdRepository.findHouseholdsWithNoPets()).thenReturn(List.of(sampleHousehold));
        List<Household> households = householdService.findHouseholdsWithNoPets();
        assertEquals(1, households.size());
    }

    @Test
    void testDeleteHousehold() {
        doNothing().when(householdRepository).deleteById("D01X123");
        assertDoesNotThrow(() -> householdService.deleteHousehold("D01X123"));
        verify(householdRepository, times(1)).deleteById("D01X123");
    }
}
