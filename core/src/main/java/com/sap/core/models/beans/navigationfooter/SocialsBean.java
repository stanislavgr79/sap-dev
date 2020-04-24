package com.sap.core.models.beans.navigationfooter;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
@ToString
public class SocialsBean {

    private boolean isURL;
    private String fileReference;
    private String title;
    private String link;
    private String typeOfOpen;
}
