package com.riteshk.fundhunt.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.riteshk.fundhunt.R;
import com.riteshk.fundhunt.entities.Entity;

import java.util.ArrayList;


public class EntityAdapter extends BaseAdapter {

    private Context context;
    private final ArrayList<Entity> entities;

    public EntityAdapter(Context context, ArrayList<Entity> entities) {
        this.context = context;
        this.entities = entities;
    }

    @Override
    public int getCount() {
        return entities.size();
    }

    @Override
    public Object getItem(int position) {
        return entities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.entity, null);
        }
        Entity entity = entities.get(position);
        if (entity != null) {
            TextView pos = (TextView) v.findViewById(R.id.position_textView);
            TextView fundName = (TextView) v.findViewById(R.id.fundName_textView);
            TextView col2 = (TextView) v.findViewById(R.id.col2_textView);
            TextView col3 = (TextView) v.findViewById(R.id.col3_textView);
            TextView col4 = (TextView) v.findViewById(R.id.col4_textView);

            pos.setText(String.valueOf(entity.getPosition()));
            fundName.setText(String.valueOf(entity.getFundName()));
            col2.setText(String.valueOf(entity.getCol2()));
            col3.setText(String.valueOf(entity.getCol3()));
            col4.setText(String.valueOf(entity.getCol4()));
        }
        return v;
    }
}
