package com.sap.core.models.navigationfooter;

import com.sap.core.models.beans.navigationfooter.CopyrightsBean;
import com.sap.core.models.beans.navigationfooter.LinksGroupBean;
import com.sap.core.models.beans.navigationfooter.SocialsBean;
import com.sap.core.service.navigationfooter.NavigationFooterService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = {SlingHttpServletRequest.class, Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = "sap/components/navigation/v3/navigation"
)
public class NavigationFooter {

    @Inject
    private NavigationFooterService navigationFooterService;

    @ValueMapValue
    private String title;
    @ValueMapValue
    private boolean typeOfLink;
    @ValueMapValue
    private String linkTo;
    @ValueMapValue
    private String typeOfOpen;

    @Inject
    @Via("resource")
    private List<Resource> socials;
    @Inject
    @Via("resource")
    private List<Resource> copyrights;
    @Inject
    @Via("resource")
    @Optional
    private List<Resource> footerGroupLinks;

    private List<SocialsBean> socialsCol;
    private List<CopyrightsBean> copyrightsCol;
    private List<LinksGroupBean> footerGroupLinksCol;

    @PostConstruct
    public final void init() {
        if(checkListResource(socials)) socialsCol = navigationFooterService.populateMultiFieldSocialsItems(socials);
        if(checkListResource(copyrights)) copyrightsCol = navigationFooterService.populateMultiFieldCopyrightItems(copyrights);

        if (checkListResource(footerGroupLinks)) {
            footerGroupLinksCol = new ArrayList<>();
            for (Resource resource : footerGroupLinks) {
                LinksGroup linksGroup = resource.adaptTo(LinksGroup.class);

                assert linksGroup != null;
                LinksGroupBean linksGroupBean = new LinksGroupBean(linksGroup.getGroupTitle(), linksGroup.getLinksNames());
                footerGroupLinksCol.add(linksGroupBean);
            }
        }
    }

    private boolean checkListResource(List<Resource> resources) {
        return resources!=null && !resources.isEmpty();
    }

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

    public List<SocialsBean> getSocialsCol() {
        return socialsCol;
    }

    public List<CopyrightsBean> getCopyrightsCol() {
        return copyrightsCol;
    }

    public List<LinksGroupBean> getFooterGroupLinksCol() {
        return footerGroupLinksCol;
    }
}
