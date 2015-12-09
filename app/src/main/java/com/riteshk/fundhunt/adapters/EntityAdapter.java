package com.riteshk.fundhunt.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.riteshk.fundhunt.R;
import com.riteshk.fundhunt.entities.TableEntity;

import java.util.ArrayList;


public class EntityAdapter extends BaseAdapter {

    private Context context;
    private final ArrayList<TableEntity> entities;

    public EntityAdapter(Context context, ArrayList<TableEntity> entities) {
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
        TableEntity tableEntity = entities.get(position);
        if (tableEntity != null) {
            TextView col1 = (TextView) v.findViewById(R.id.col1_textView);
            TextView col2 = (TextView) v.findViewById(R.id.col2_textView);
            TextView col3 = (TextView) v.findViewById(R.id.col3_textView);
            TextView col4 = (TextView) v.findViewById(R.id.col4_textView);
            TextView col5 = (TextView) v.findViewById(R.id.col5_textView);

            col1.setText(tableEntity.getCol1());
            col2.setText(tableEntity.getCol2());
            col3.setText(tableEntity.getCol3());
            col4.setText(tableEntity.getCol4());
            col5.setText(tableEntity.getCol5());
        }
        return v;
    }
}
