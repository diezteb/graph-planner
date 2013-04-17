package pl.edu.agh.ztis.planner.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.ztis.planner.planners.Planner;
import pl.edu.agh.ztis.planner.ws.PlanningAlgorithm;

import java.util.Map;

@Component
public class AlgorithmResolver {

    @Autowired
    private Map<String, Planner> planners;

    Planner resolveAlgorithm(PlanningAlgorithm algorithmName) {
        return planners.get(algorithmName.value());
    }
}
