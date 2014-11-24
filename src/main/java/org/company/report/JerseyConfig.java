package org.company.report;

import org.company.report.controller.DeliveryPlanResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(DeliveryPlanResource.class);
    }

}
