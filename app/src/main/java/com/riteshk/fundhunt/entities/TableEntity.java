package com.riteshk.fundhunt.entities;

import java.io.Serializable;


public class TableEntity implements Serializable {

    private Integer position;

    private String fundName;

    private String col2;

    private String col3;

    private String col4;

    public TableEntity(Integer position, String fundName, String col2, String col3, String col4) {
        this.position = position;
        this.fundName = fundName;
        this.col2 = col2;
        this.col3 = col3;
        this.col4 = col4;
    }

    public Integer getPosition() {
        return position;
    }

    public String getCol2() {
        return col2;
    }

    public String getCol3() {
        return col3;
    }

    public String getCol4() {
        return col4;
    }

    public String getFundName() {
        return fundName;
    }

}
