package com.sap.core.models;

import lombok.Getter;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;

@Getter
@Model(adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = "sap/components/button/v2/button"
)
public class Button {

    @ValueMapValue
    private String description;
    @ValueMapValue
    private String linkLabel;
    @ValueMapValue
    private String linkTo;
    @ValueMapValue
    private boolean styleSide;
    @ValueMapValue
    private boolean typeOfLink;
    private String styleSideDescription;

    @PostConstruct
    protected void init() {
        if(styleSide){
                styleSideDescription = "justify-content: flex-end";
            } else {
                styleSideDescription = "justify-content: flex-start";
            }
    }

}
