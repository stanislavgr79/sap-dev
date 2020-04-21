package com.sap.core.models.beans.navigationfooter;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
@ToString
public class CopyrightsBean implements Serializable {

    private boolean isURL;
    private String typeOfOpen;
    private String link;
    private String descriptionLink;

}
