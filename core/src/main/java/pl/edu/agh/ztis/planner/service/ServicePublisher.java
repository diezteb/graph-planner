package pl.edu.agh.ztis.planner.service;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.edu.agh.ztis.planner.ws.GraphPlanningPortType;

@Component
public class ServicePublisher {
    @Autowired
    private GraphPlanningPortType service;

    @Autowired
    @Value("${service.url}")
    private String url;

    public void run() {
        JaxWsServerFactoryBean serverFactoryBean = new JaxWsServerFactoryBean();
        serverFactoryBean.setServiceClass(GraphPlanningPortType.class);
        serverFactoryBean.setWsdlLocation("planning.wsdl");
        serverFactoryBean.setAddress(url);
        serverFactoryBean.setServiceBean(service);
        serverFactoryBean.getInInterceptors().add(new LoggingInInterceptor());
        serverFactoryBean.getOutInterceptors().add(new LoggingOutInterceptor());
        serverFactoryBean.create();
    }

}
