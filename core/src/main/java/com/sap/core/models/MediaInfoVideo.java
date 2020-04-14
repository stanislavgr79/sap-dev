package com.sap.core.models;

import com.sap.core.service.mediainfovideo.MediaInfoVideoService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = "sap/components/media/v1/media_info_video"
)
public class MediaInfoVideo {

    @Inject
    private MediaInfoVideoService mediaInfoVideoService;

    @ValueMapValue
    private String title;
    @ValueMapValue
    private String description;
    @ValueMapValue
    private String videoPath;
    @ValueMapValue
    private boolean typeOfLink;

    private List<String> mediaInfo;

    @Inject
    private SlingHttpServletRequest request;

    @PostConstruct
    public final void init() {
       mediaInfo = mediaInfoVideoService.getMediaInfo(request.getResource());
       if (!typeOfLink) videoPath = mediaInfoVideoService.getTrimUrlFromVideoPath(videoPath);
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public boolean isTypeOfLink() {
        return typeOfLink;
    }

    public List<String> getMediaInfo() {
        return mediaInfo;
    }
}

