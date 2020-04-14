package com.sap.core.models.beans.navigationfooter;

import com.sap.core.models.navigationfooter.LinksNames;

import java.util.List;

public class LinksGroupBean {

    private String groupTitle;
    private List<LinksNames> linksNames;

    public LinksGroupBean(String groupTitle, List<LinksNames> linksNames) {
        this.groupTitle = groupTitle;
        this.linksNames = linksNames;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public List<LinksNames> getLinksNames() {
        return linksNames;
    }

    public void setLinksNames(List<LinksNames> linksNames) {
        this.linksNames = linksNames;
    }
}
