package com.example.project_all_labs.services;

import com.example.project_all_labs.entities.Household;
import com.example.project_all_labs.repositories.HouseholdRepository;
import com.example.project_all_labs.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseholdServiceImpl implements HouseholdService {

    @Autowired
    private HouseholdRepository householdRepository;

    @Override
    public Household createHousehold(Household household) {
        return householdRepository.save(household);
    }

    @Override
    public List<Household> getAllHouseholds() {
        return householdRepository.findAll();
    }

    @Override
    public Household findByEircode(String eircode) {
        return householdRepository.findById(eircode)
                .orElseThrow(() -> new NotFoundException("Household not found"));
    }

    @Override
    public Household findByEircodeWithPets(String eircode) {
        return householdRepository.findByEircodeWithPets(eircode)
                .orElseThrow(() -> new NotFoundException("Household not found"));
    }

    @Override
    public List<Household> findHouseholdsWithNoPets() {
        return householdRepository.findHouseholdsWithNoPets();
    }

    @Override
    public List<Household> findOwnerOccupiedHouseholds() {
        return householdRepository.findByOwnerOccupied(true);
    }

    @Override
    public void deleteHousehold(String eircode) {
        householdRepository.deleteById(eircode);
    }
}
