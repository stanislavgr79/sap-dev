package com.sap.core.models.beans.navigationfooter;

public class CopyrightsBean {

    private boolean isURL;
    private String typeOfOpen;
    private String link;
    private String descriptionLink;

    public boolean isURL() {
        return isURL;
    }

    public void setURL(boolean statusURL) {
        isURL = statusURL;
    }

    public String getTypeOfOpen() {
        return typeOfOpen;
    }

    public void setTypeOfOpen(String typeOfOpen) {
        this.typeOfOpen = typeOfOpen;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescriptionLink() {
        return descriptionLink;
    }

    public void setDescriptionLink(String descriptionLink) {
        this.descriptionLink = descriptionLink;
    }
}