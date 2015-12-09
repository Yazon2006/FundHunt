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
            JSONArray rows = object.getJSONArray("rows");

            for (int i = 0; i < rows.length(); ++i) {
                JSONObject row = rows.getJSONObject(i);
                JSONArray columns = row.getJSONArray("c");

                Integer position = columns.getJSONObject(0).getInt("v");
                String fundName = columns.getJSONObject(1).getString("v");
                String col2 = columns.getJSONObject(2).getString("v");
                String col3 = columns.getJSONObject(3).getString("v");
                String col4 = columns.getJSONObject(4).getString("v");
                TableEntity tableEntity = new TableEntity(position, fundName, col2, col3, col4);
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
