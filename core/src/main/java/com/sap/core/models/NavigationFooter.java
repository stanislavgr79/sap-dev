package com.sap.core.models;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.*;

@Model(adaptables = { SlingHttpServletRequest.class, Resource.class },
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = "sap/components/navigation/v3/navigation"
)
public class NavigationFooter {

    private static final Logger LOGGER = LoggerFactory.getLogger(NavigationFooter.class);

    @SlingObject
    private ResourceResolver resourceResolver;
    @Inject
    private SlingHttpServletRequest request;

    @ValueMapValue
    private String title;
    @ValueMapValue
    private boolean typeOfLink;
    @ValueMapValue
    private String linkTo;
    @ValueMapValue
    private String typeOfOpen;

//
//
//
//    @ValueMapValue
//    private boolean isURL;
//    @ValueMapValue
//    private String link;
//    @ValueMapValue
//    private String descriptionLink;

//    @Inject @Via
//    @ChildResource
//    private List<String> copyrightsCol;


    @Inject
    @Via("resource")
    private List<Resource> copyrightNames;
    @Inject
    @Via("resource")
    private List<Resource> footerlinksNames;
    @Inject
    @Via("resource")
    private List<Resource> linksNames;

    public static class CopyrightsBean {
        boolean isURL;
        String typeOfOpen;
        String link;
        String descriptionLink;

        public boolean isURL() {
            return isURL;
        }

        public void setURL(boolean URL) {
            isURL = URL;
        }

        public String getTypeOfOpen() {
            return typeOfOpen;
        }

        public void setTypeOfOpen(String typeOfOpen) {
            this.typeOfOpen = typeOfOpen;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getDescriptionLink() {
            return descriptionLink;
        }

        public void setDescriptionLink(String descriptionLink) {
            this.descriptionLink = descriptionLink;
        }
    }

    private List<CopyrightsBean> copyrightsCol;
    public List<CopyrightsBean> getCopyrightsCol() {
        return copyrightsCol;
    }

//    private Map<FooterLinksBean, List<LinksNamesBean>> footerLinksMap;


    @PostConstruct
    public final void init() {
        populateMultiFieldCopyrightItems(copyrightNames);
    }

    private void populateMultiFieldCopyrightItems(List<Resource> copyrightNames) {
        if (copyrightNames != null && !copyrightNames.isEmpty()) {
            copyrightsCol = new ArrayList<>();
            for (Resource item : copyrightNames) {
                if (item != null) {
                    CopyrightsBean menuItem = new CopyrightsBean();
                    ValueMap vm = item.getValueMap();
                    boolean isURLCopyright = getPropertyValue(vm, "isURL").equals("true");
                    String typeOfOpenCopyright = getPropertyValue(vm, "typeOfOpen");
                    String linkCopyright = getPropertyValue(vm, "link");
                    String descriptionLinkCopyright = getPropertyValue(vm, "descriptionLink");
                    menuItem.setURL(isURLCopyright);
                    menuItem.setTypeOfOpen(typeOfOpenCopyright);
                    menuItem.setLink(linkCopyright);
                    menuItem.setDescriptionLink(descriptionLinkCopyright);
                    copyrightsCol.add(menuItem);

                } else {
                    LOGGER.info("ValueMap not found for resource  : {}", item);
                }
            }
        }
    }

    private String getPropertyValue(final ValueMap properties, final String propertyName) {
        return properties.containsKey(propertyName) ? properties.get(propertyName, String.class) : StringUtils.EMPTY;
    }




//    @Inject
//    public NavigationFooter (@Named("copyrightsCol") String copyrightsCol) {
//
//        // constructor code
//    }


    public String getTitle() {
        return title;
    }

    public String getLinkTo() {
        return linkTo;
    }

    public String getTypeOfOpen() {
        return typeOfOpen;
    }

    public boolean isTypeOfLink() {
        return typeOfLink;
    }

//    public boolean isURL() {
//        return isURL;
//    }
//
//    public String getLink() {
//        return link;
//    }
//
//    public String getDescriptionLink() {
//        return descriptionLink;
//    }
}
