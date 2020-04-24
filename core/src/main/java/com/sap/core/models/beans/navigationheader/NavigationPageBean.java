package com.sap.core.models.beans.navigationheader;

import com.day.cq.wcm.api.Page;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class NavigationPageBean {
    private Page currentPage;
    private String findTitle;
    private String path;
    private String pageNumber;
    private String activeClass;
}
