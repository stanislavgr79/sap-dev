package com.sap.core.models.events.viewer;

import com.sap.core.models.events.component.EventComponent;
import com.sap.core.service.events.viewer.EventsViewService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Model(adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = "sap/components/events/eventviewer/v1/eventviewer"
)
public class EventViewer {

    @Inject
    private EventsViewService eventsViewService;

    @ValueMapValue
    @Default(values = "topic")
    private String keyForBuildMapOfEventsComponent;

    private Map<String, List<EventComponent>> allEvents;

    @PostConstruct
    public final void init() {
        allEvents = eventsViewService.getAllEvents(keyForBuildMapOfEventsComponent);
    }

    public void setKeyForBuildMapOfEventsComponent(String keyForBuildMapOfEventsComponent) {
        this.keyForBuildMapOfEventsComponent = keyForBuildMapOfEventsComponent;
    }

    public String getKeyForBuildMapOfEventsComponent() {
        return keyForBuildMapOfEventsComponent;
    }
    public Map<String, List<EventComponent>> getAllEvents() {
        return allEvents;
    }


}
