package com.sap.core.models.beans.navigationfooter;

public class SocialsBean {

    private boolean isURL;
    private String fileReference;
    private String linkTo;
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

    public String getLinkTo() {
        return linkTo;
    }

    public void setLinkTo(String linkTo) {
        this.linkTo = linkTo;
    }

    public String getTypeOfOpen() {
        return typeOfOpen;
    }

    public void setTypeOfOpen(String typeOfOpen) {
        this.typeOfOpen = typeOfOpen;
    }
}
