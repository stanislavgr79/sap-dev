package com.sap.core.models.beans.navigationfooter;

public class SocialsBean {

    private boolean isURL;
    private String fileReference;
    private String title;
    private String link;
    private String typeOfOpen;

    public boolean isURL() {
        return isURL;
    }

    public void setURL(boolean URL) {
        isURL = URL;
    }

    public String getFileReference() {
        return fileReference;
    }

    public void setFileReference(String fileReference) {
        this.fileReference = fileReference;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTypeOfOpen() {
        return typeOfOpen;
    }

    public void setTypeOfOpen(String typeOfOpen) {
        this.typeOfOpen = typeOfOpen;
    }
}
