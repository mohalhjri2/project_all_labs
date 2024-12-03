package com.example.project_all_labs.graphql;

import com.example.project_all_labs.entities.Household;
import com.example.project_all_labs.services.HouseholdService;
import com.example.project_all_labs.dto.HouseholdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLHouseholdController {

    @Autowired
    private HouseholdService householdService;

    @QueryMapping
    public List<Household> getAllHouseholds() {
        return householdService.getAllHouseholds();
    }

    @QueryMapping
    public Household getHousehold(String eircode) {
        return householdService.findByEircodeWithPets(eircode);
    }

    @MutationMapping
    public Household createHousehold(HouseholdDTO input) {
        Household household = new Household(
                input.getEircode(),
                input.getNumberOfOccupants(),
                input.getMaxNumberOfOccupants(),
                input.isOwnerOccupied(),
                null
        );
        return householdService.createHousehold(household);
    }

    @Secured("ROLE_ADMIN")
    @MutationMapping
    public Boolean deleteHousehold(String eircode) {
        householdService.deleteHousehold(eircode);
        return true;
    }
}
