package com.sap.core.service.navigationfooter;

import com.sap.core.models.beans.navigationfooter.CopyrightsBean;
import com.sap.core.models.beans.navigationfooter.SocialsBean;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Component(immediate = true, service = NavigationFooterService.class)
public class NavigationFooterServiceImpl implements NavigationFooterService{
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private static final String LOGGER_MESSAGE = "ValueMap not found for resource : {}";
    private static final String TYPE_OF_OPEN_PROPERTY_KEY = "typeOfOpen";

    @Override
    public List<SocialsBean> populateMultiFieldSocialsItems(List<Resource> socials) {
        List<SocialsBean> socialsCol = new ArrayList<>();
        if (socials != null && !socials.isEmpty()) {
            for (Resource item : socials) {
                if (item != null) {
                    SocialsBean menuItem = new SocialsBean();
                    ValueMap vm = item.getValueMap();
                    menuItem.setFileReference(getPropertyValue(vm, "fileReference"));
                    menuItem.setLinkTo(getPropertyValue(vm, "linkTo"));
                    menuItem.setTypeOfOpen(getPropertyValue(vm, TYPE_OF_OPEN_PROPERTY_KEY));
                    socialsCol.add(menuItem);

                } else {
                    logger.info(LOGGER_MESSAGE , item);
                }
            }
        }
        return socialsCol;
    }

    @Override
    public List<CopyrightsBean> populateMultiFieldCopyrightItems(List<Resource> copyrights) {
        List<CopyrightsBean> copyrightsCol = new ArrayList<>();
        if (copyrights != null && !copyrights.isEmpty()) {
            for (Resource item : copyrights) {
                if (item != null) {
                    CopyrightsBean menuItem = new CopyrightsBean();
                    ValueMap vm = item.getValueMap();
                    menuItem.setURL(getPropertyValue(vm, "isURL").equals("true"));
                    menuItem.setTypeOfOpen(getPropertyValue(vm, TYPE_OF_OPEN_PROPERTY_KEY));
                    menuItem.setLink(getPropertyValue(vm, "link"));
                    menuItem.setDescriptionLink(getPropertyValue(vm, "descriptionLink"));
                    copyrightsCol.add(menuItem);
                } else {
                    logger.info(LOGGER_MESSAGE , item);
                }
            }
        }
        return copyrightsCol;
    }

//    @Override
//    public List<FooterLinksGroupBean> populateMultiFieldFooterLinksItems (LinkedHashMap<String, List<Resource>> footerGroupLinks) {
//        List<FooterLinksGroupBean> footerGroupLinksCol = new ArrayList<>();
//        if (!footerGroupLinks.isEmpty()) {
//            footerGroupLinks.forEach((key, value) -> {
//
//                FooterLinksGroupBean footerLinksGroupBean = new FooterLinksGroupBean();
//                footerLinksGroupBean.setGroupTitle(key);
//                List<LinksNamesBean> namesBeanArrayList = new ArrayList<>();
//
//                for (Resource item : value) {
//                    if (item != null) {
//                        for (Resource linksNamesResource : item.getChildren()) {
//                            LinksNamesBean linksNamesBean = new LinksNamesBean();
//                            ValueMap vm = linksNamesResource.getValueMap();
//                            linksNamesBean.setURL(getPropertyValue(vm, "isURL").equals("true"));
//                            linksNamesBean.setTypeOfOpen(getPropertyValue(vm, TYPE_OF_OPEN_PROPERTY_KEY));
//                            linksNamesBean.setLink(getPropertyValue(vm, "link"));
//                            linksNamesBean.setDescriptionLink(getPropertyValue(vm, "descriptionLink"));
//                            namesBeanArrayList.add(linksNamesBean);
//                        }
//                    } else {
//                        logger.info(LOGGER_MESSAGE , item);
//                    }
//                }
//                footerLinksGroupBean.setLinksNamesBeans(namesBeanArrayList);
//                footerGroupLinksCol.add(footerLinksGroupBean);
//            });
//        }
//        return footerGroupLinksCol;
//    }

    private String getPropertyValue(final ValueMap properties, final String propertyName) {
        return properties.containsKey(propertyName) ? properties.get(propertyName, String.class) : StringUtils.EMPTY;
    }
}
