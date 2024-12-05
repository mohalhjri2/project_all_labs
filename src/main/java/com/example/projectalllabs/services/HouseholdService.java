package com.example.projectalllabs.services;

import com.example.projectalllabs.dtos.HouseholdDTO;
import com.example.projectalllabs.dtos.HouseholdStatisticsDTO;
import com.example.projectalllabs.entities.Household;

import java.util.List;

public interface HouseholdService {

    Household createHousehold(HouseholdDTO householdDTO);

    List<Household> getAllHouseholds();

    Household getHouseholdById(String eircode, boolean includePets);

    void deleteHouseholdById(String eircode);

    Household updateHousehold(String eircode, HouseholdDTO householdDTO);

    List<Household> findHouseholdsWithNoPets();

    List<Household> findOwnerOccupiedHouseholds();

    HouseholdStatisticsDTO getHouseholdStatistics();
}
