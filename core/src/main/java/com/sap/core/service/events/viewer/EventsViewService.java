package com.sap.core.service.events.viewer;

import com.sap.core.models.events.component.EventComponent ;

import java.util.List;
import java.util.Map;

public interface EventsViewService {

    Map<String, List<EventComponent>> getAllEvents(String keyForMap);
    void setKey(String key);

}
