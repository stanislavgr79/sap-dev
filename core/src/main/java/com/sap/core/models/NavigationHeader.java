package com.sap.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageFilter;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.*;

@Model(adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
        // resourceType = "sap/components/navigation"
)
public class NavigationHeader {

    private List<NavigationPage> items = new ArrayList<>();
    @ValueMapValue
    private String pathRootPage;
    private Page rootPage;
    private Locale locale;

    @SlingObject
    private ResourceResolver resourceResolver;
    @Inject
    private SlingHttpServletRequest request;
    @ScriptVariable
    private Page currentPage;

    public static class NavigationPage {
        private Page currentPage;
        private String findTitle;
        private String path;
        private String pageNumber;
        private String activeClass;

        public Page getCurrentPage() {
            return currentPage;
        }

        public String getFindTitle() {
            return findTitle;
        }

        public String getPageNumber() {
            return pageNumber;
        }

        public String getActiveClass() {
            return activeClass;
        }

        public String getPath() {
            return path;
        }
    }

    @PostConstruct
    protected void init() {

        if (pathRootPage != null) {
            rootPage = Objects.requireNonNull(resourceResolver.getResource(pathRootPage)).adaptTo(Page.class);

            Iterator<Page> childPages = null;
            if (rootPage != null) {
                childPages = rootPage.listChildren(new PageFilter(request));
                String pageNumber;
                int number = 0;
                pageNumber = "nav-li-".concat(String.valueOf(number));
                items.add(createNavigationPageModel(rootPage, pageNumber));
                while (childPages.hasNext()) {
                    Page page = childPages.next();
                    if (!checkPageIsHiddenInNavigation(page)) {
                        pageNumber = "nav-li-".concat(String.valueOf(number++));
                        items.add(createNavigationPageModel(page, pageNumber));
                    }
                }
            }
        }
    }

    private boolean checkPageIsHiddenInNavigation(Page page) {
        return page.isHideInNav();
    }

    private NavigationPage createNavigationPageModel(Page page, String pageNumber){
        NavigationPage navigationPage = new NavigationPage();
        navigationPage.currentPage = page;
        navigationPage.findTitle = getAvailableTitle(page);
        navigationPage.pageNumber = pageNumber;
        navigationPage.activeClass =  (isCurrentPageAreActive(page)) ? "active" : "";
        navigationPage.path = page.getPath();
        return navigationPage;
    }

    private boolean isCurrentPageAreActive(Page page) {
        return page.equals(currentPage);
    }

    public String getAvailableTitle(Page page) {
        String title = page.getTitle();
        if (title == null) {
            title = page.getNavigationTitle();
        }
        if (title == null) {
            title = page.getPageTitle();
        }
        return title;
    }

    public List<NavigationPage> getItems() {
        return items;
    }

    public Page getRootPage() {
        return rootPage;
    }

    public String getLocale() {
        return locale.getLanguage();
    }
}
