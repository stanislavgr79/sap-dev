package com.sap.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = "sap/components/media/v1/media_info_video"
)
public class MediaInfoVideo {

    @ValueMapValue
    private String title;
    @ValueMapValue
    private String description;
    @ValueMapValue
    private String videoPath;
    @ValueMapValue
    private boolean typeOfLink;
    private List<String> mediaInfo;

    @SlingObject
    private ResourceResolver resourceResolver;
    @Inject
    private SlingHttpServletRequest request;


    private void setTrimUrlFromVideoPath(String videoPath){
        if(!videoPath.matches("/embed/")){
            this.videoPath = videoPath.substring(0, videoPath.lastIndexOf("/watch?v=")).concat("/embed/").concat(videoPath.substring(videoPath.indexOf('=')+1));
        }

    }

    public List<String> getMediaInfo() {
            Resource childResource = request.getResource().getChild("elements");
            if (childResource != null) {
                mediaInfo = populateModel(childResource);
            }
        return mediaInfo;
    }

    List<String> populateModel(Resource resource) {
        mediaInfo = new ArrayList<>();
        if (resource != null) {
            Iterator<Resource> linkResources = resource.listChildren();
            while (linkResources.hasNext()) {
                Resource childResource = linkResources.next();
                if (childResource != null)
                    mediaInfo.add(childResource.getValueMap().get("text").toString());
            }
        }
        return mediaInfo;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoPath() {
        if (!typeOfLink) {
            setTrimUrlFromVideoPath(videoPath);
        }
        return videoPath;
    }

    public boolean isTypeOfLink() {
        return typeOfLink;
    }
}

