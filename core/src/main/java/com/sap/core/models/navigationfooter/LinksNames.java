package com.sap.core.models.navigationfooter;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LinksNames {

    @ValueMapValue
    private boolean isURL;
    @ValueMapValue
    private String typeOfOpen;
    @ValueMapValue
    private String link;
    @ValueMapValue
    private String descriptionLink;

    public boolean isURL() {
        return isURL;
    }

    public String getTypeOfOpen() {
        return typeOfOpen;
    }

    public String getLink() {
        return link;
    }

    public String getDescriptionLink() {
        return descriptionLink;
    }
}
