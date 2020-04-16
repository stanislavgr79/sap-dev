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

    List<CopyrightsBean> copyrightsColPartOne;
    List<CopyrightsBean> copyrightsColPartTwo;

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
    public List<List<CopyrightsBean>> populateMultiFieldCopyrightItems(List<Resource> copyrights) {
        List<List<CopyrightsBean>> copyrightsCol = new ArrayList<>();
        copyrightsColPartOne = new ArrayList<>();
        copyrightsColPartTwo = new ArrayList<>();
        if (checkResource(copyrights)) {
            for (int i = 0; i < copyrights.size(); i++) {
                Resource item = copyrights.get(i);
                if (item != null) {
                    fillCopyrightCollectionsByResourceSize(buildCopyrightBean(item), i, copyrights.size());
                } else {
                    logger.info(LOGGER_MESSAGE, item);
                }
            }
        }
        copyrightsCol.add(copyrightsColPartOne);
        copyrightsCol.add(copyrightsColPartTwo);
        return copyrightsCol;
    }

    private boolean checkResource(List<Resource> resources){
        return resources != null && !resources.isEmpty();
    }

    private CopyrightsBean buildCopyrightBean(Resource item){
        CopyrightsBean copyrightsBean = new CopyrightsBean();
        ValueMap vm = item.getValueMap();
        boolean isURL = getPropertyValue(vm, "isURL").equals("true");
        copyrightsBean.setURL(isURL);
        copyrightsBean.setTypeOfOpen(getPropertyValue(vm, TYPE_OF_OPEN_PROPERTY_KEY));
        copyrightsBean.setLink(correctLinkByURLValue(isURL, vm));
        copyrightsBean.setDescriptionLink(getPropertyValue(vm, "descriptionLink"));
        return copyrightsBean;
    }

    private void fillCopyrightCollectionsByResourceSize(CopyrightsBean copyrightsBean, int index, int resourceSize){
            if(index<resourceSize/2){
                copyrightsColPartOne.add(copyrightsBean);
            } else {
                copyrightsColPartTwo.add(copyrightsBean);
            }
    }

    private String correctLinkByURLValue(boolean isURL, ValueMap vm){
        if(!isURL){
            return getPropertyValue(vm, "link").concat(".html");
        }else {
            return (getPropertyValue(vm, "link"));
        }
    }

    private String getPropertyValue(final ValueMap properties, final String propertyName) {
        return properties.containsKey(propertyName) ? properties.get(propertyName, String.class) : StringUtils.EMPTY;
    }
}
