package com.sap.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = "sap/components/hero/v3/hero"
)
public class HeroListInfo {

    @ValueMapValue
    private String title;
    @ValueMapValue
    private String description;
    private List<String> heroList;

    @SlingObject
    private ResourceResolver resourceResolver;
    @Inject
    private SlingHttpServletRequest request;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getHeroList() {
            Resource childResource = request.getResource().getChild("elements");
            if (childResource != null) {
                heroList = populateModel(childResource);
            }
        return heroList;
    }

    List<String> populateModel(Resource resource) {
        heroList = new ArrayList<>();
        if (resource != null) {
            Iterator<Resource> linkResources = resource.listChildren();
            while (linkResources.hasNext()) {
                Resource childResource = linkResources.next();
                if (childResource != null)
                    heroList.add(childResource.getValueMap().get("text").toString());
            }
        }
        return heroList;
    }
}
