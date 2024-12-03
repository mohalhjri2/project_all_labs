package com.example.project_all_labs.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseholdDTO {

    @NotBlank
    private String eircode;

    @Min(0)
    private int numberOfOccupants;

    @Min(1)
    private int maxNumberOfOccupants;

    private boolean ownerOccupied;
}
