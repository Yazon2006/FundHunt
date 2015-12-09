package com.riteshk.fundhunt.entities;

import java.io.Serializable;


public class TableEntity implements Serializable {

    private String col1;

    private String col2;

    private String col3;

    private String col4;

    private String col5;

    public TableEntity(String col1, String col2, String col3, String col4, String col5) {
        this.col1 = col1;
        this.col2 = col2;
        this.col3 = col3;
        this.col4 = col4;
        this.col5 = col5;
    }

    public String getCol1() {
        return col1;
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

    public String getCol5() {
        return col5;
    }

}
