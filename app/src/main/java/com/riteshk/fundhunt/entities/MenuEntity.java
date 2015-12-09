package com.riteshk.fundhunt.entities;


import java.io.Serializable;

public class MenuEntity implements Serializable{

    private String itemName;

    private String screenContent;

    public MenuEntity (String itemName, String screenContent) {
        this.itemName = itemName;
        this.screenContent = screenContent;
    }

    public String getItemName() {
        return itemName;
    }

    public String getScreenContent() {
        return screenContent;
    }
}
