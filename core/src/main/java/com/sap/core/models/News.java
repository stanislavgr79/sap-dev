package com.sap.core.models;

import lombok.Getter;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.Date;

@Getter
@Model(adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = "sap/components/news/v1/news"
)
public class News {

    @ValueMapValue
    private Date newsDate;
    @ValueMapValue
    private String description;
    @ValueMapValue
    private String linkLabel;
    @ValueMapValue
    private String linkTo;

}
