package com.example.projectalllabs.graphql;

import com.example.projectalllabs.dtos.HouseholdStatisticsDTO;
import com.example.projectalllabs.entities.Household;
import com.example.projectalllabs.services.HouseholdService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HouseholdQueryResolver implements GraphQLQueryResolver {

    private final HouseholdService householdService;

    public HouseholdQueryResolver(HouseholdService householdService) {
        this.householdService = householdService;
    }

    public List<Household> getAllHouseholds() {
        return householdService.getAllHouseholds();
    }

    public Household getHousehold(String eircode) {
        return householdService.getHouseholdById(eircode, true);
    }

    public HouseholdStatisticsDTO getHouseholdStatistics() {
        return householdService.getHouseholdStatistics();
    }
}
