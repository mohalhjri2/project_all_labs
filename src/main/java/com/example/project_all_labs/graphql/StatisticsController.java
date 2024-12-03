package com.example.project_all_labs.graphql;

import com.example.project_all_labs.entities.Pet;
import com.example.project_all_labs.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
public class StatisticsController {

    @Autowired
    private PetService petService;

    @QueryMapping
    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalPets", petService.getAllPets().size());
        stats.put("averageAge", petService.getAllPets().stream().mapToInt(Pet::getAge).average().orElse(0));
        stats.put("oldestAge", petService.getAllPets().stream().mapToInt(Pet::getAge).max().orElse(0));
        return stats;
    }
}
