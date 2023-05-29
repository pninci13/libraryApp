package com.example.libraryapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class HistoryAdapter extends ArrayAdapter<String> {
    ArrayList<String> list;
    Context context;

    public HistoryAdapter(Context context, ArrayList<String> list) {
        super(context, R.layout.history_row, list);
        this.context = context;
        this.list = list;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.history_row, null);
            TextView number = convertView.findViewById(R.id.number);
            TextView name = convertView.findViewById(R.id.name);
            TextView change = convertView.findViewById(R.id.change);

            number.setText(position + 1 + ".");

            Item item = Item.fromString(list.get(position));
            name.setText(item.getName());
            change.setText(item.getHistory());

        }
        return convertView;
    }

}
