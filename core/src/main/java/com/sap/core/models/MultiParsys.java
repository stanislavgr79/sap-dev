package com.sap.core.models;

import lombok.Getter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Getter
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class MultiParsys {

    @ValueMapValue
    private Integer column;
    @ValueMapValue
    private String titleParsys;
    @ValueMapValue
    private String descriptionButton;
    @ValueMapValue
    private String linkLabel;
    @ValueMapValue
    private String linkTo;
    @ValueMapValue
    private boolean typeOfLink;
    private List<String> table;
    private String style = null;

    @PostConstruct
    protected void init() {
        if (column != null && column>0) {
            buildParsysByColumn();
        }
    }

    private void buildParsysByColumn() {
        table = new ArrayList<>();
        for (int i = 1; i <= column; i++) {
                table.add("par_c" + i);
            }
        style = "grid-template-columns: repeat(" + column + ", 1fr)";
    }

}
