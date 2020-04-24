package com.sap.core.service.navigationheader;

import com.day.cq.wcm.api.Page;
import com.sap.core.models.beans.navigationheader.NavigationPageBean;

import java.util.Iterator;
import java.util.List;

public interface NavigationHeaderService {

    List<NavigationPageBean> getListNavigation(Page root, Page current, Iterator<Page> childPages);
}
