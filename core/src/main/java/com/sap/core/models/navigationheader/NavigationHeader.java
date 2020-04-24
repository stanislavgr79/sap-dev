package com.sap.core.models.navigationheader;

import com.day.cq.wcm.api.Page;
import com.sap.core.models.beans.navigationheader.NavigationPageBean;

import java.util.List;

public interface NavigationHeader {

    Page getRootPage();
    String getSiteTitle();
    List<NavigationPageBean> getItems();
}
