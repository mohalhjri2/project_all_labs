package com.example.projectalllabs.services;

import com.example.projectalllabs.dtos.HouseholdDTO;
import com.example.projectalllabs.dtos.HouseholdStatisticsDTO;
import com.example.projectalllabs.entities.Household;
import com.example.projectalllabs.services.exceptions.BadDataException;
import com.example.projectalllabs.services.exceptions.NotFoundException;
import com.example.projectalllabs.repositories.HouseholdRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseholdServiceImpl implements HouseholdService {

    private final HouseholdRepository householdRepository;

    public HouseholdServiceImpl(HouseholdRepository householdRepository) {
        this.householdRepository = householdRepository;
    }

    @Override
    public Household createHousehold(HouseholdDTO householdDTO) {
        if (householdDTO.numberOfOccupants() > householdDTO.maxNumberOfOccupants()) {
            throw new BadDataException("Number of occupants cannot exceed the maximum number of occupants.");
        }

        Household household = new Household();
        household.setEircode(householdDTO.eircode());
        household.setNumberOfOccupants(householdDTO.numberOfOccupants());
        household.setMaxNumberOfOccupants(householdDTO.maxNumberOfOccupants());
        household.setOwnerOccupied(householdDTO.ownerOccupied());
        return householdRepository.save(household);
    }

    @Override
    public List<Household> getAllHouseholds() {
        return householdRepository.findAll();
    }

    @Override
    public Household getHouseholdById(String eircode, boolean includePets) {
        return householdRepository.findById(eircode)
                .orElseThrow(() -> new NotFoundException("Household not found with eircode: " + eircode));
    }

    @Override
    public void deleteHouseholdById(String eircode) {
        Household household = householdRepository.findById(eircode)
                .orElseThrow(() -> new NotFoundException("Household not found with eircode: " + eircode));

        householdRepository.delete(household);
    }

    @Override
    public Household updateHousehold(String eircode, HouseholdDTO householdDTO) {
        Household household = householdRepository.findById(eircode)
                .orElseThrow(() -> new NotFoundException("Household not found with eircode: " + eircode));

        if (householdDTO.numberOfOccupants() > householdDTO.maxNumberOfOccupants()) {
            throw new BadDataException("Number of occupants cannot exceed the maximum number of occupants.");
        }

        household.setNumberOfOccupants(householdDTO.numberOfOccupants());
        household.setMaxNumberOfOccupants(householdDTO.maxNumberOfOccupants());
        household.setOwnerOccupied(householdDTO.ownerOccupied());
        return householdRepository.save(household);
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
    public HouseholdStatisticsDTO getHouseholdStatistics() {
        long emptyHouses = householdRepository.findAll().stream()
                .filter(h -> h.getNumberOfOccupants() == 0)
                .count();
        long fullHouses = householdRepository.findAll().stream()
                .filter(h -> h.getNumberOfOccupants() == h.getMaxNumberOfOccupants())
                .count();
        return new HouseholdStatisticsDTO(emptyHouses, fullHouses);
    }
}
