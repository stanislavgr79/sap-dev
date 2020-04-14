package com.sap.core.models.navigationfooter;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LinksGroup {

    @Inject
    private String groupTitle;

    @Inject
    @Named("linksGroup")
    private List<Resource> linksGroups;

    private List<LinksNames> linksNames;

    @PostConstruct
    public final void init() {
        if(linksGroups!=null && !linksGroups.isEmpty()){
            linksNames = new ArrayList<>();
            for (Resource resource : linksGroups) {
                LinksNames linksName = resource.adaptTo(LinksNames.class);
                this.linksNames.add(linksName);
            }
        }
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public List<LinksNames> getLinksNames() {
        return linksNames;
    }

}
