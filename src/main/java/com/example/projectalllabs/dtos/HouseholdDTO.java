package com.example.projectalllabs.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record HouseholdDTO(
        @NotBlank(message = "Eircode cannot be blank") String eircode,
        @Min(value = 0, message = "Number of occupants must be non-negative") int numberOfOccupants,
        @Min(value = 1, message = "Max number of occupants must be at least 1") int maxNumberOfOccupants,
        boolean ownerOccupied
) {}
