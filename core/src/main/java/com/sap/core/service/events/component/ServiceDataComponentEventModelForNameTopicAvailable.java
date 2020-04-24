package com.sap.core.service.events.component;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@Component(immediate = true, service = ServiceDataComponentEventModelForNameTopicAvailable.class)
@ServiceDescription("Service - Sap Event-Component: name of topic available")
@Designate(ocd = ServiceDataComponentEventModelForNameTopicAvailable.ServiceDataComponentEventModelForNameTopicAvailableConfig.class)
public class ServiceDataComponentEventModelForNameTopicAvailable {

    private String [] nameOfTopicArray = {};

    @ObjectClassDefinition
    public @interface ServiceDataComponentEventModelForNameTopicAvailableConfig {
        @AttributeDefinition(name = "Name of topic", type = AttributeType.STRING)
        String[] nameOfTopicArray() default {"Database", "Cloud", "Mobile", "Other Topics"};
    }

    @Activate
    @Modified
    public void activate(ServiceDataComponentEventModelForNameTopicAvailableConfig config) {
        this.nameOfTopicArray = config.nameOfTopicArray();
    }

    public String[] getNameOfTopicArray() {
        return nameOfTopicArray;
    }
}
