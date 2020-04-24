package com.sap.core.models.navigationfooter;

import lombok.Getter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;

@Getter
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

    @PostConstruct
    public final void init() {
        if(!isURL){
            link = link.concat(".html");
        }
    }
}
