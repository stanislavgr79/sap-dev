package com.sap.core.models.events.component;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.sap.core.service.events.component.ServiceDataComponentEventModelForNameTypeAvailable;
import org.apache.commons.collections.iterators.TransformIterator;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Model(adaptables = {SlingHttpServletRequest.class, Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class HtlDataSourceType {

    @SlingObject
    private ResourceResolver resourceResolver;
    @Inject
    private SlingHttpServletRequest request;
    @Inject
    private ServiceDataComponentEventModelForNameTypeAvailable typeModelAvailable;

    @PostConstruct
    protected void init() {
        final Map<String, String> typeMap = new LinkedHashMap<>();
        String[] arrayOfType = typeModelAvailable.getNameOfTypeArray();
        for(String el: arrayOfType){
            typeMap.put(el,el);
        }
        DataSource ds = new SimpleDataSource(new TransformIterator(typeMap.keySet().iterator(), o -> {
            String typeName = o.toString();
            ValueMap vm = new ValueMapDecorator(new HashMap<>());
            vm.put("value", typeMap.get(typeName));
            vm.put("text", typeName);

            return new ValueMapResource(resourceResolver, new ResourceMetadata(), "nt:unstructured", vm);
        }));
        request.setAttribute(DataSource.class.getName(), ds);
    }
}
