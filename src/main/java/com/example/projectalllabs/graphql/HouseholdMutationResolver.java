package com.example.projectalllabs.graphql;

import com.example.projectalllabs.dtos.HouseholdDTO;
import com.example.projectalllabs.entities.Household;
import com.example.projectalllabs.services.HouseholdService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class HouseholdMutationResolver implements GraphQLMutationResolver {

    private final HouseholdService householdService;

    public HouseholdMutationResolver(HouseholdService householdService) {
        this.householdService = householdService;
    }

    public Household createNewHousehold(HouseholdDTO household) {
        return householdService.createHousehold(household);
    }

    public String deleteHouseholdById(String eircode) {
        householdService.deleteHouseholdById(eircode);
        return "Household with eircode " + eircode + " was deleted.";
    }
}
