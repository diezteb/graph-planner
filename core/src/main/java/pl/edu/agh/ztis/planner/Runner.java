package pl.edu.agh.ztis.planner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.edu.agh.ztis.planner.service.ServicePublisher;

public class Runner {

    private static final Logger logger = LoggerFactory.getLogger(Runner.class);

    protected Runner() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bootstrap.xml");

        logger.info("Starting Server");
        applicationContext.getBean(ServicePublisher.class).run();
        logger.info("Server ready...");
    }

    public static void main(String args[]) throws Exception {
        new Runner();
    }
}
