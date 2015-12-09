package com.riteshk.fundhunt.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.riteshk.fundhunt.R;
import com.riteshk.fundhunt.adapters.EntityAdapter;
import com.riteshk.fundhunt.entities.MenuEntity;
import com.riteshk.fundhunt.entities.TableEntity;
import com.riteshk.fundhunt.tasks.DownloadMenuItemsTask;
import com.riteshk.fundhunt.tasks.DownloadMenuItemsTaskResult;
import com.riteshk.fundhunt.tasks.DownloadWebpageTask;
import com.riteshk.fundhunt.tasks.DownloadWebpageTaskResult;
import com.riteshk.fundhunt.utils.AppConstants;
import com.riteshk.fundhunt.utils.EntityHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;
import org.json.JSONObject;

import java.util.ArrayList;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private ActionBar actionBar;

    @ViewById
    protected Toolbar toolbar;

    @ViewById
    protected ListView listview;

    @ViewById
    protected ProgressBar progress;

    @InstanceState
    protected ArrayList<TableEntity> entities;

    @InstanceState
    protected ArrayList<MenuEntity> menuItems;

    @AfterViews
    protected void initViews() {
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
        }
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
        showProgress();
        new DownloadWebpageTask(new DownloadWebpageTaskResult() {
            @Override
            public void onTableDownload(JSONObject object) {
                processTable(object);
                hideProgress();
            }

            @Override
            public void onErrorTableDownload() {
                hideProgress();
            }
        }).execute(AppConstants.URL_TABLE);
    }

    private void downloadMenuItems() {
        new DownloadMenuItemsTask(new DownloadMenuItemsTaskResult() {
            @Override
            public void onMenuItemsDownload(JSONObject object) {
                processMenu(object);
            }

            @Override
            public void onErrorTableDownload() {

            }
        }).execute(AppConstants.URL_MENU);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menuItems != null) {
            menu.clear();

            for (int i = 0; i < menuItems.size(); i++) {
                MenuItem item = menu.add(menuItems.get(i).getItemName());

                if (item != null) {
                    final int menuItemCount = i;
                    item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (menuItemCount == 0) {
                                downloadTable();
                            } else {
                                Toast.makeText(MainActivity.this, menuItems.get(menuItemCount).getScreenContent(), Toast.LENGTH_SHORT).show();
                            }
                            return true;
                        }
                    });
                }
            }
        }
        return true;
    }

    private void processMenu(JSONObject object) {
        menuItems = EntityHelper.getMenuItems(object);
        invalidateOptionsMenu();
    }

    private void processTable(JSONObject object) {
        entities = EntityHelper.getTableItems(object);

        final EntityAdapter adapter = new EntityAdapter(this, entities);
        listview.setAdapter(adapter);
    }

    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

}
