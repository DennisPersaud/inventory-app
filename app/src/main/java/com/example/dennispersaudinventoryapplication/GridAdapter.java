package com.example.dennispersaudinventoryapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class GridAdapter extends BaseAdapter {

    private final Context context;
    private final List<ItemData> items;
    private LayoutInflater inflater;

    public GridAdapter(Context context, List<ItemData> items) {

        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {

        return items.size();
    }

    @Override
    public ItemData getItem(int position) {

        return items.get(position);
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null) {

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.activity_row, parent, false);
        }

        TextView gridItemName = convertView.findViewById(R.id.gridItemName);
        TextView gridItemPrice = convertView.findViewById(R.id.gridItemPrice);
        TextView gridItemCount = convertView.findViewById(R.id.gridItemCount);

        ItemData id = this.getItem(position);

        gridItemName.setText(id.getItemName());
        gridItemPrice.setText(String.format("Price: $%s", id.getItemPrice()));
        gridItemCount.setText(String.format("Qty: %s", id.getItemCount()));

        return convertView;
    }
}
