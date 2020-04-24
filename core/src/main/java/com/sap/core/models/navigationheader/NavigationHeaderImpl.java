package com.sap.core.models.navigationheader;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageFilter;
import com.sap.core.models.beans.navigationheader.NavigationPageBean;
import com.sap.core.service.navigationheader.NavigationHeaderService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.*;

@Model(adaptables = {SlingHttpServletRequest.class, Resource.class},
        adapters = {NavigationHeader.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = NavigationHeaderImpl.RESOURCE_TYPE
)
public class NavigationHeaderImpl implements NavigationHeader{

    protected static final String RESOURCE_TYPE = "sap/components/navigation/v2/navigation";
    protected static final int LEVEL_OF_ABSOLUTE = 3;

    @Inject
    private NavigationHeaderService navigationHeaderService;

    @ValueMapValue
    private String pathRootPage;
    @ValueMapValue
    private String siteTitle;
    @SlingObject
    private ResourceResolver resourceResolver;
    @Inject
    private SlingHttpServletRequest request;
    @ScriptVariable
    private Page currentPage;

    private Page rootPage;
    private List<NavigationPageBean> items = new ArrayList<>();

    @PostConstruct
    protected void init() {
        initialization();
        buildListNavigationPageChildren();
    }

    private void initialization (){
        if(pathRootPage==null){
            rootPage = getRootPage(currentPage);
            pathRootPage = rootPage.getPath();
        }else {
            rootPage = Objects.requireNonNull(resourceResolver.getResource(pathRootPage)).adaptTo(Page.class);
        }
    }

    private Page getRootPage(Page page) {
        Page root = page.getAbsoluteParent(LEVEL_OF_ABSOLUTE);
        return (root != null) ? root : page;
    }

    private void buildListNavigationPageChildren(){
        Iterator<Page> childPages = null;
        if (rootPage != null) {
            childPages = rootPage.listChildren(new PageFilter(request));
        }
        items = navigationHeaderService.getListNavigation(rootPage, currentPage, childPages);
    }

    @Override
    public Page getRootPage() {
        return rootPage;
    }
    @Override
    public String getSiteTitle() {
        return siteTitle;
    }
    @Override
    public List<NavigationPageBean> getItems() {
        return items;
    }


    private Locale locale;

    public String getLocaleLang() {
        final String WEB_APPEND_PATH = "/us/en";
        String lang = rootPage.getLanguage().getLanguage();
        String path = "";
        if ("en".equals(lang)) {
            path = WEB_APPEND_PATH;
        }
        return path;
    }

    public String getLocale() {
        return locale.getLanguage();
    }

}
