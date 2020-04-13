package com.sap.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.Date;

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

    public Date getNewsDate() {
        return newsDate;
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
}
