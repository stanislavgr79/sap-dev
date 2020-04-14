package com.sap.core.service.navigationfooter;

import com.sap.core.models.beans.navigationfooter.CopyrightsBean;
import com.sap.core.models.beans.navigationfooter.SocialsBean;
import org.apache.sling.api.resource.Resource;

import java.util.List;

public interface NavigationFooterService {

    List<SocialsBean> populateMultiFieldSocialsItems(List<Resource> socials);
    List<CopyrightsBean> populateMultiFieldCopyrightItems(List<Resource> copyrights);
//    List<FooterLinksGroupBean> populateMultiFieldFooterLinksItems(LinkedHashMap<String, List<Resource>> footerGroupLinks);
}
