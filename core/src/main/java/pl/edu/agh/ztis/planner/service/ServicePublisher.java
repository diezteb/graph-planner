package pl.edu.agh.ztis.planner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.edu.agh.ztis.planning.PlanningService;

import javax.xml.ws.Endpoint;

@Component
public class ServicePublisher {
    @Autowired
    private PlanningService service;

    @Autowired
    @Value("${service.url}")
    private String url;

    public void run() {
        Endpoint.publish(url, service);
    }

}
