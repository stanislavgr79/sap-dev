package com.sap.core.service.events.viewer;

import com.google.common.collect.Lists;
import com.sap.core.models.events.component.EventComponent;
import com.sap.core.service.events.component.ServiceDataComponentEventModelForNameTopicAvailable;
import com.sap.core.service.events.component.ServiceDataComponentEventModelForNameTypeAvailable;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.*;

@Component(immediate = true, service = EventsViewService.class)
@ServiceDescription("EventsViewService")
public class EventsViewServiceImpl implements EventsViewService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private static final String SERVICE_USER_NAME = "system_user";
    private static final String PATH_ADMIN_PAGE_NODE = "/content/sap/root-admin/admin-events/jcr:content/root2/container/container";

    private Node nodeAdminPage;
    private Session session;

    @Reference
    private ServiceDataComponentEventModelForNameTopicAvailable  topicAvailable;
    @Reference
    private ServiceDataComponentEventModelForNameTypeAvailable  typeAvailable;
    @Reference
    private ResourceResolverFactory resourceResolverFactory;
    @SlingObject
    private ResourceResolver resourceResolver;

    private LinkedHashMap<String, List<EventComponent>> mapOfEventsComponent;
    private Node node;
    private NodeIterator nodeIterator;

    private String key;

    @Override
    public Map<String, List<EventComponent>> getAllEvents(String keyForBuildMapOfEventsComponent)  {
        if(key==null) key = keyForBuildMapOfEventsComponent;
        buildSession();
        List<String> keyForMap = getListKeyForMap();
        mapOfEventsComponent = fillEmptyListOfEventsComponentByNewKey(keyForMap);

        if (initialization()) {
            nodeIterator = getNodes(nodeAdminPage);

            while (nodeIterator.hasNext()) {
                node = nodeIterator.nextNode();

                for (String el : keyForMap) {
                    String nameTopic = "";
                    try {
                        nameTopic = node.getProperty(key).getValue().getString();
                    } catch (RepositoryException e) {
                        logger.error(String.format("getProperty: %s", e.getMessage()));
                    }
                    putListComponentsToMapByKey(nameTopic, el);
                }
            }
        }
        return mapOfEventsComponent;
    }

    @Override
    public void setKey(String key){
        this.key = key;
    }

    private NodeIterator getNodes(Node nodeAdminPage){
        try {
            nodeIterator = nodeAdminPage.getNodes();
        } catch (RepositoryException e) {
            logger.error(String.format("getNodes: %s", e.getMessage()));
        }
        return nodeIterator;
    }

    private void putListComponentsToMapByKey(String nameTopic, String el) {
        if (nameTopic.equals(el)) {
            LinkedList<EventComponent> list = (LinkedList<EventComponent>) mapOfEventsComponent.get(el);
            EventComponent eventComponent = null;
            try {
                eventComponent = buildEventComponent();
            } catch (RepositoryException e) {
                logger.error(String.format("buildEventComponent: %s", e.getMessage()));
            }
            list.add(eventComponent);
            mapOfEventsComponent.put(el, list);
        }
    }

    private EventComponent buildEventComponent() throws RepositoryException {
        String description = "";
        if(node.hasProperty("description")){
            description = node.getProperty("description").getString();
        }
        boolean isURL = node.getProperty("isURL").getString().equals("true");
        String link = node.getProperty("titleLink").getString();
        if(!isURL) link = link.concat(".html");
        return EventComponent.builder()
                .title(node.getProperty("title").getString())
                .titleLink(link)
                .isURL(isURL)
                .typeOfOpen(node.getProperty("typeOfOpen").getString())
                .eventStartDate(node.getProperty("eventStartDate").getDate().getTime())
                .topic(node.getProperty("topic").getValue().getString())
                .description(description)
                .build();
    }

    private List<String> getListKeyForMap() {
        List<String> keyForMap = new ArrayList<>();
        switch (key) {
            case "topic":
                keyForMap = Lists.newArrayList(topicAvailable.getNameOfTopicArray());
                break;
            case "type":
                keyForMap = Lists.newArrayList(typeAvailable.getNameOfTypeArray());
                break;
            default:
                logger.error("no any key for map");
        }
        return keyForMap;
    }

    private LinkedHashMap<String, List<EventComponent>> fillEmptyListOfEventsComponentByNewKey(List<String> keyForMap) {
        mapOfEventsComponent = new LinkedHashMap<>();
        if (keyForMap != null) {
            for (String el : keyForMap) {
                mapOfEventsComponent.put(el, new LinkedList<>());
            }
        }
        return mapOfEventsComponent;
    }

    private boolean initialization() {
        try {
            nodeAdminPage = session.getNode(PATH_ADMIN_PAGE_NODE);
        } catch (RepositoryException e) {
            logger.error(String.format("initialization: %s", e.getMessage()));
            return false;
        }
        return true;
    }

    protected void buildSession() {
        Map<String, Object> param = new HashMap<>();
        param.put(ResourceResolverFactory.SUBSERVICE, SERVICE_USER_NAME);
        try {
            resourceResolver = resourceResolverFactory.getServiceResourceResolver(param);
        } catch (org.apache.sling.api.resource.LoginException e) {
            logger.error(String.format("buildSession: %s", e.getMessage()));
        }
        session = resourceResolver.adaptTo(Session.class);
    }
}
