package com.riteshk.fundhunt.ui;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.riteshk.fundhunt.R;
import com.riteshk.fundhunt.adapters.EntityAdapter;
import com.riteshk.fundhunt.entities.Entity;
import com.riteshk.fundhunt.tasks.DownloadMenuItemsTask;
import com.riteshk.fundhunt.tasks.DownloadMenuItemsTaskResult;
import com.riteshk.fundhunt.tasks.DownloadWebpageTask;
import com.riteshk.fundhunt.tasks.DownloadWebpageTaskResult;
import com.riteshk.fundhunt.utils.AppConstants;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById
    protected Toolbar toolbar;

    @ViewById
    protected ListView listview;

    @InstanceState
    protected ArrayList<Entity> entities;

    @InstanceState
    protected ArrayList<String> menuItems;

    @AfterViews
    protected void initViews() {
        setSupportActionBar(toolbar);

        downloadMenuItems();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (entities != null && !entities.isEmpty()) {
            final EntityAdapter adapter = new EntityAdapter(this, entities);
            listview.setAdapter(adapter);
            downloadTable();
        } else {
            downloadTable();
        }
    }

    private void downloadTable() {
        new DownloadWebpageTask(new DownloadWebpageTaskResult() {
            @Override
            public void onTableDownload(JSONObject object) {
                processTable(object);
            }
        }).execute(AppConstants.URL_TABLE);
    }

    private void downloadMenuItems() {
        new DownloadMenuItemsTask(new DownloadMenuItemsTaskResult() {
            @Override
            public void onMenuItemsDownload(JSONObject object) {
                processMenu(object);
            }
        }).execute(AppConstants.URL_MENU);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);

        if (menuItems != null) {
            menu.clear();

            for (int i = 0; i < menuItems.size(); i++) {
                MenuItem item = menu.add(menuItems.get(i));

                if (item != null) {
                    final int menuItemCount = i;
                    item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            Toast.makeText(MainActivity.this, menuItems.get(menuItemCount), Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    });
                }
            }
        }
        return true;
    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        if (menuItems != null) {
//            menu.clear();
//
//            for (int i = 0; i < menuItems.size(); i++) {
//                MenuItem item = menu.add(menuItems.get(i));
//
//                if (item != null) {
//                    item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//                        @Override
//                        public boolean onMenuItemClick(MenuItem item) {
//
//                            return true;
//                        }
//                    });
//                }
//            }
//        }
//        return super.onPrepareOptionsMenu(menu);
//    }

    private void processMenu(JSONObject object) {
        menuItems = new ArrayList<>();

        try {
            JSONArray array = object.getJSONArray("MenuItems");

            for (int i = 0; i < array.length(); ++i) {
                JSONObject item = array.getJSONObject(i);
                String menuItem = item.getString("Menu_item");
                menuItems.add(menuItem);
            }

            invalidateOptionsMenu();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void processTable(JSONObject object) {
        entities = new ArrayList<>();

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
                Entity entity = new Entity(position, fundName, col2, col3, col4);
                entities.add(entity);
            }

            final EntityAdapter adapter = new EntityAdapter(this, entities);
            listview.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
