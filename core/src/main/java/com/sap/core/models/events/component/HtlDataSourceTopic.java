package com.sap.core.models.events.component;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.sap.core.service.events.component.ServiceDataComponentEventModelForNameTopicAvailable;
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

@Model(adaptables = {SlingHttpServletRequest.class,Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class HtlDataSourceTopic {

    @SlingObject
    private ResourceResolver resourceResolver;
    @Inject
    private SlingHttpServletRequest request;
    @Inject
    private ServiceDataComponentEventModelForNameTopicAvailable topicModelAvailable;

    @PostConstruct
    protected void init() {
        final Map<String, String> topicMap = new LinkedHashMap<>();
        String[] arrayOfTopics = topicModelAvailable.getNameOfTopicArray();
        for(String el: arrayOfTopics){
            topicMap.put(el,el);
        }
        DataSource ds = new SimpleDataSource(new TransformIterator(topicMap.keySet().iterator(), o -> {
            String topicName = o.toString();
            ValueMap vm = new ValueMapDecorator(new HashMap<>());
            vm.put("value", topicMap.get(topicName));
            vm.put("text", topicName);

            return new ValueMapResource(resourceResolver, new ResourceMetadata(), "nt:unstructured", vm);
        }));
        request.setAttribute(DataSource.class.getName(), ds);
    }
}
