package com.riteshk.fundhunt.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.riteshk.fundhunt.R;
import com.riteshk.fundhunt.entities.MenuEntity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_menu_item)
public class MenuItemActivity extends AppCompatActivity {

    @ViewById
    protected Toolbar toolbar;

    @Extra
    protected MenuEntity menuEntity;

    @ViewById
    protected TextView description_textView;

    @AfterViews
    protected void initViews() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            if (menuEntity != null) {
                actionBar.setTitle(menuEntity.getItemName());
            }
        }

        if (menuEntity != null) {
            description_textView.setText(menuEntity.getScreenContent());
        }
    }

}
