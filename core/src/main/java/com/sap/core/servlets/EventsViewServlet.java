package com.sap.core.servlets;

import com.google.gson.Gson;
import com.sap.core.models.events.component.EventComponent;
import com.sap.core.service.events.viewer.EventsViewService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component(service = Servlet.class,
        property = {
                Constants.SERVICE_DESCRIPTION + "=EventsView Servlet",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                "sling.servlet.selectors=sort",
                "sling.servlet.resourceTypes=" + "sap/components/events/eventviewer/v1/eventviewer",
                "sling.servlet.extensions=" + "json" }

)
public class EventsViewServlet extends SlingSafeMethodsServlet {

    @Reference
    private transient EventsViewService eventsViewService;

    @Override
    protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws IOException {
        String sortByName = request.getParameter("sortEvent");

        String jsonResponse = "";
        Map<String, List<EventComponent>> events = new LinkedHashMap<>();
        if(!sortByName.equals("")) {
            eventsViewService.setKey(sortByName);
            events = eventsViewService.getAllEvents(sortByName);
        }

        jsonResponse = new Gson().toJson(events);
//
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }
}
