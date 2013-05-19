package pl.edu.agh.ztis.planner.planners.discovery;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.core.type.AnnotationMetadata;
import pl.edu.agh.ztis.planner.ws.PlanningAlgorithm;

import java.util.Collection;
import java.util.Set;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.getOnlyElement;

public class PlannerBeanNameGenerator implements BeanNameGenerator {

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        if (definition instanceof AnnotatedBeanDefinition) {
            return determineBeanNameFromAnnotation((AnnotatedBeanDefinition) definition);
        }
        return null;
    }

    protected String determineBeanNameFromAnnotation(AnnotatedBeanDefinition annotatedDef) {
        AnnotationMetadata amd = annotatedDef.getMetadata();
        Set<String> types = amd.getAnnotationTypes();
        for (String type : types) {
            if (type.equals(PlannerComponent.class.getName())) {
                Collection<Object> annotationAttributes = amd.getAnnotationAttributes(type).values();
                return getOnlyElement(filter(annotationAttributes, PlanningAlgorithm.class)).value();
            }
        }
        return null;
    }
}
