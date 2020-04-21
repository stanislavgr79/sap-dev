package com.sap.core.service.events.component;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@Component(immediate = true, service = ServiceDataComponentEventModelForNameTypeAvailable.class)
@ServiceDescription("Service - Sap Event-Component: name of topic available")
@Designate(ocd = ServiceDataComponentEventModelForNameTypeAvailable.ServiceDataComponentEventModelForNameTypeAvailableConfig.class)
public class ServiceDataComponentEventModelForNameTypeAvailable {

    private String [] nameOfTypeArray = {};

    @ObjectClassDefinition
    public @interface ServiceDataComponentEventModelForNameTypeAvailableConfig {
        @AttributeDefinition(name = "Name of type", type = AttributeType.STRING)
        String[] nameOfTypeArray() default {"MyType"};
    }

    @Activate
    @Modified
    public void activate(ServiceDataComponentEventModelForNameTypeAvailableConfig config) {
        this.nameOfTypeArray = config.nameOfTypeArray();
    }

    public String[] getNameOfTypeArray() {
        return nameOfTypeArray;
    }
}
