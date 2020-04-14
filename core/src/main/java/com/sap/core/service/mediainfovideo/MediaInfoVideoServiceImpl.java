package com.sap.core.service.mediainfovideo;

import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component(immediate = true, service = MediaInfoVideoService.class)
public class MediaInfoVideoServiceImpl implements MediaInfoVideoService{

    private List<String> mediaInfo;

    @Override
    public List<String> getMediaInfo(Resource resource) {
        Resource childResource = resource.getChild("elements");
        if (childResource != null) {
            mediaInfo = populateModel(childResource);
        }
        return mediaInfo;
    }

    @Override
    public String getTrimUrlFromVideoPath(String videoPath){
        if(!"/embed/".matches(videoPath)){
            return videoPath.substring(0, videoPath.lastIndexOf("/watch?v=")).concat("/embed/")
                    .concat(videoPath.substring(videoPath.indexOf('=')+1));
        }else {
            return videoPath;
        }
    }

    private List<String> populateModel(Resource resource) {
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
}
