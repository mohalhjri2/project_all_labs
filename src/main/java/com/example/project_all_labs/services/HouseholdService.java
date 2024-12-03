package com.example.project_all_labs.services;

import com.example.project_all_labs.entities.Household;

import java.util.List;

public interface HouseholdService {

    Household createHousehold(Household household);

    List<Household> getAllHouseholds();

    Household findByEircode(String eircode);

    Household findByEircodeWithPets(String eircode);

    List<Household> findHouseholdsWithNoPets();

    List<Household> findOwnerOccupiedHouseholds();

    void deleteHousehold(String eircode);
}
