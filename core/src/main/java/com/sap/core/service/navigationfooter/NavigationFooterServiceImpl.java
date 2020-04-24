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
                    socialsCol.add(buildSocialsBean(item));
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

    private SocialsBean buildSocialsBean(Resource item){
        SocialsBean socialsBean = new SocialsBean();
        ValueMap vm = item.getValueMap();
        boolean isURL = getPropertyValue(vm, "isURL").equals("true");
        socialsBean.setURL(isURL);
        socialsBean.setTitle(getPropertyValue(vm, "title"));
        socialsBean.setFileReference(getPropertyValue(vm, "fileReference"));
        socialsBean.setLink(correctLinkByURLValue(isURL, vm));
        socialsBean.setTypeOfOpen(getPropertyValue(vm, TYPE_OF_OPEN_PROPERTY_KEY));
        return socialsBean;
    }

    private CopyrightsBean buildCopyrightBean(Resource item){

        ValueMap vm = item.getValueMap();
        boolean isURL = getPropertyValue(vm, "isURL").equals("true");
        return CopyrightsBean.builder()
                .isURL(isURL)
                .typeOfOpen(getPropertyValue(vm, TYPE_OF_OPEN_PROPERTY_KEY))
                .link(correctLinkByURLValue(isURL, vm))
                .descriptionLink(getPropertyValue(vm, "descriptionLink"))
                .build();
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
