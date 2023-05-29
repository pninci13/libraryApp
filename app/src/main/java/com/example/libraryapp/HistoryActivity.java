package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    static ArrayList<String> history;
    static ListView listView;
    static HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        View openDialog = findViewById(R.id.open_dialog);
        View historyDialog = findViewById(R.id.history_dialog);
        listView = findViewById(R.id.list);
        Context context = getApplicationContext();

        history = new ArrayList<>();

        for (int i = 0; i < HomeActivity.allItems.size(); i++) {
            Item item = HomeActivity.objectList.get(i);

            for (int j = 0; j < item.history_list.size(); j++) {
                item.setHistory(item.history_list.get(j));
                history.add(item.toString());
            }
        }

        adapter = new HistoryAdapter(this, history);
        listView.setAdapter(adapter);

    }

    public static void addItem(String item) {
        history.add(item);
//        addItemJSON();
        listView.setAdapter(adapter);
    }
}