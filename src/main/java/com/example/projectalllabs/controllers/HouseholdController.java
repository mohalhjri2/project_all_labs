package com.example.projectalllabs.controllers;

import com.example.projectalllabs.dtos.HouseholdDTO;
import com.example.projectalllabs.dtos.HouseholdStatisticsDTO;
import com.example.projectalllabs.entities.Household;
import com.example.projectalllabs.services.HouseholdService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/households")
public class HouseholdController {

    private final HouseholdService householdService;

    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @GetMapping
    public ResponseEntity<List<Household>> getAllHouseholds() {
        return ResponseEntity.ok(householdService.getAllHouseholds());
    }

    @GetMapping("/{eircode}")
    public ResponseEntity<Household> getHouseholdById(@PathVariable String eircode) {
        return ResponseEntity.ok(householdService.getHouseholdById(eircode, true));
    }

    @PostMapping
    public ResponseEntity<Household> createHousehold(@Valid @RequestBody HouseholdDTO householdDTO) {
        Household createdHousehold = householdService.createHousehold(householdDTO);
        return ResponseEntity.ok(createdHousehold);
    }

    @DeleteMapping("/{eircode}")
    public ResponseEntity<Void> deleteHousehold(@PathVariable String eircode) {
        householdService.deleteHouseholdById(eircode);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{eircode}")
    public ResponseEntity<Household> updateHousehold(@PathVariable String eircode, @Valid @RequestBody HouseholdDTO householdDTO) {
        Household updatedHousehold = householdService.updateHousehold(eircode, householdDTO);
        return ResponseEntity.ok(updatedHousehold);
    }

    @GetMapping("/no-pets")
    public ResponseEntity<List<Household>> getHouseholdsWithNoPets() {
        return ResponseEntity.ok(householdService.findHouseholdsWithNoPets());
    }

    @GetMapping("/owner-occupied")
    public ResponseEntity<List<Household>> getOwnerOccupiedHouseholds() {
        return ResponseEntity.ok(householdService.findOwnerOccupiedHouseholds());
    }

    @GetMapping("/statistics")
    public ResponseEntity<HouseholdStatisticsDTO> getHouseholdStatistics() {
        return ResponseEntity.ok(householdService.getHouseholdStatistics());
    }
}
