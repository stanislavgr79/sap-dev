package com.sap.core.service.mediainfovideo;

import org.apache.sling.api.resource.Resource;

import java.util.List;

public interface MediaInfoVideoService {

    List<String> getMediaInfo(Resource resource);
    String getTrimUrlFromVideoPath(String videoPath);
}
