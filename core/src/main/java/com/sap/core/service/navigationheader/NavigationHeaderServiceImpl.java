package com.sap.core.service.navigationheader;

import com.day.cq.wcm.api.Page;
import com.sap.core.models.beans.navigationheader.NavigationPageBean;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component(immediate = true, service = NavigationHeaderService.class)
public class NavigationHeaderServiceImpl implements NavigationHeaderService{

    private Page currentPage;

    @Override
    public List<NavigationPageBean> getListNavigation(Page rootPage, Page currentPage, Iterator<Page> childPages) {
        this.currentPage = currentPage;
        List<NavigationPageBean> navigationPages = new ArrayList<>();
        String pageNumber;
        int number = 0;
        pageNumber = "nav-li-".concat(String.valueOf(number));
        navigationPages.add(createNavigationPageBeanModel(rootPage, pageNumber));
        while (childPages.hasNext()) {
            Page page = childPages.next();
            if (!checkPageIsHiddenInNavigation(page)) {
                pageNumber = "nav-li-".concat(String.valueOf(number++));
                navigationPages.add(createNavigationPageBeanModel(page, pageNumber));
            }
        }
        return navigationPages;
    }

    private boolean checkPageIsHiddenInNavigation(Page page) {
        return page.isHideInNav();
    }

    private NavigationPageBean createNavigationPageBeanModel(Page page, String pageNumber){
        return NavigationPageBean.builder()
                .currentPage(page)
                .findTitle(getAvailableTitle(page))
                .pageNumber(pageNumber)
                .activeClass((isCurrentPageAreActive(page)) ? "active" : "")
                .path(page.getPath())
                .build();
    }

    private boolean isCurrentPageAreActive(Page page) {
        return page.equals(currentPage);
    }

    private String getAvailableTitle(Page page) {
        String title = page.getTitle();
        if (title == null) {
            title = page.getNavigationTitle();
        }
        if (title == null) {
            title = page.getPageTitle();
        }
        return title;
    }
}
