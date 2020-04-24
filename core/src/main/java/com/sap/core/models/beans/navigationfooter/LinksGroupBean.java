package com.sap.core.models.beans.navigationfooter;

import com.sap.core.models.navigationfooter.LinksNames;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
@ToString
public class LinksGroupBean {

    private String groupTitle;
    private List<LinksNames> linksNames;
}
