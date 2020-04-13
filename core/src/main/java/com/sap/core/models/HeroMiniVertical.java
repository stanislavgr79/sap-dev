package com.sap.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class HeroMiniVertical {

    @ValueMapValue
    private String title;
    @ValueMapValue
    private String description;
    @ValueMapValue
    private String linkLabel;
    @ValueMapValue
    private String linkTo;
    @ValueMapValue
    private String typeOfOpen;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLinkLabel() {
        return linkLabel;
    }

    public String getLinkTo() {
        return linkTo;
    }

    public String getTypeOfOpen() {
        return typeOfOpen;
    }
}
