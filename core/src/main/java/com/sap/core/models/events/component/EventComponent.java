package com.sap.core.models.events.component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Model(adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = "sap/components/events/event/v1/event"
)
public class EventComponent {

    @ValueMapValue
    private String title;
    @ValueMapValue
    private String titleLink;
    @ValueMapValue
    private boolean isURL;
    @ValueMapValue
    private String typeOfOpen;

    @ValueMapValue
    private String description;
    @ValueMapValue
    private Date eventStartDate;

    @ValueMapValue
    private String type;
    @ValueMapValue
    private String topic;

    @PostConstruct
    public final void init() {
        if(!isURL && titleLink!=null) titleLink = titleLink.concat(".html");
    }

}
