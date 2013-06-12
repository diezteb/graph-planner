package pl.edu.agh.ztis.planner.planners.discovery;

import pl.edu.agh.ztis.planner.ws.PlanningAlgorithm;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PlannerComponent {

    PlanningAlgorithm algorithm();

}
