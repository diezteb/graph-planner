package pl.edu.agh.ztis.planner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.edu.agh.ztis.planner.executors.PlanningExecutor;

import java.io.IOException;

public class PlanningRunner {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bootstrap.xml");
        PlanningExecutor executor = applicationContext.getBean(PlanningExecutor.class);
        executor.executePlanning(args[0]);
    }
}
