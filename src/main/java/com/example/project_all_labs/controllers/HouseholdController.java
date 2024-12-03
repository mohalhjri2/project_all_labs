package com.example.project_all_labs.controllers;

import com.example.project_all_labs.dto.HouseholdDTO;
import com.example.project_all_labs.entities.Household;
import com.example.project_all_labs.services.HouseholdService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/households")
public class HouseholdController {

    @Autowired
    private HouseholdService householdService;

    @GetMapping
    public ResponseEntity<List<Household>> getAllHouseholds() {
        return ResponseEntity.ok(householdService.getAllHouseholds());
    }

    @GetMapping("/{eircode}")
    public ResponseEntity<Household> getHousehold(@PathVariable String eircode) {
        return ResponseEntity.ok(householdService.findByEircode(eircode));
    }

    @GetMapping("/no-pets")
    public ResponseEntity<List<Household>> getHouseholdsWithNoPets() {
        return ResponseEntity.ok(householdService.findHouseholdsWithNoPets());
    }

    @PostMapping
    public ResponseEntity<Household> createHousehold(@Valid @RequestBody HouseholdDTO householdDTO) {
        Household household = new Household(
                householdDTO.getEircode(),
                householdDTO.getNumberOfOccupants(),
                householdDTO.getMaxNumberOfOccupants(),
                householdDTO.isOwnerOccupied(),
                null
        );
        return ResponseEntity.ok(householdService.createHousehold(household));
    }

    @DeleteMapping("/{eircode}")
    public ResponseEntity<Void> deleteHousehold(@PathVariable String eircode) {
        householdService.deleteHousehold(eircode);
        return ResponseEntity.noContent().build();
    }
}
