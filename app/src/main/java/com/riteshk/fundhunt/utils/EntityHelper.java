package com.riteshk.fundhunt.utils;

import com.riteshk.fundhunt.entities.MenuEntity;
import com.riteshk.fundhunt.entities.TableEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EntityHelper {

    public static ArrayList<TableEntity> getTableItems(JSONObject object) {
        ArrayList<TableEntity> entities = new ArrayList<>();

        try {
            JSONArray array = object.getJSONArray("FundHunt");

            for (int i = 0; i < array.length(); ++i) {
                JSONObject item = array.getJSONObject(i);
                String col1 = item.getString("col1");
                String col2 = item.getString("col2");
                String col3 = item.getString("col3");
                String col4 = item.getString("col4");
                String col5 = item.getString("col5");

                TableEntity tableEntity = new TableEntity(col1, col2, col3, col4, col5);
                entities.add(tableEntity);
            }

            return entities;

        } catch (JSONException e) {
            e.printStackTrace();
            return entities;
        }
    }

    public static ArrayList<MenuEntity> getMenuItems(JSONObject object) {
        ArrayList<MenuEntity> menuItems = new ArrayList<>();

        try {
            JSONArray array = object.getJSONArray("MenuItems");

            for (int i = 0; i < array.length(); ++i) {
                JSONObject item = array.getJSONObject(i);
                String itemName = item.getString("Menu_item");
                String screenContent = item.getString("Screen_content");
                menuItems.add(new MenuEntity(itemName, screenContent));
            }

            return menuItems;
        } catch (JSONException e) {
            e.printStackTrace();
            return menuItems;
        }
    }
}
