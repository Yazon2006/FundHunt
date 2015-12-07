package com.riteshk.fundhunt.ui;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.riteshk.fundhunt.R;
import com.riteshk.fundhunt.adapters.EntityAdapter;
import com.riteshk.fundhunt.entities.Entity;
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
    ArrayList<Entity> entities;

    @AfterViews
    protected void initViews() {
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (entities != null && !entities.isEmpty()) {
            final EntityAdapter adapter = new EntityAdapter(this, entities);
            listview.setAdapter(adapter);
            startDownload();
        } else {
            startDownload();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_developer: return true;
            case R.id.action_about: return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startDownload() {
        new DownloadWebpageTask(new DownloadWebpageTaskResult() {
            @Override
            public void onResult(JSONObject object) {
                processJson(object);
            }
        }).execute(AppConstants.URL_HOST);
    }

    private void processJson(JSONObject object) {
        entities = new ArrayList<>();

        try {
            JSONArray rows = object.getJSONArray("rows");

            for (int r = 0; r < rows.length(); ++r) {
                JSONObject row = rows.getJSONObject(r);
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
