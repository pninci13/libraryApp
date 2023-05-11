package com.example.libraryapp;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.libraryapp.databinding.ActivityHomeBinding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeActivity extends AppCompatActivity {
    ImageView image;
//    TextView openDialog;
    ImageView openDialog;
    TextView infoTv;

    static ListView listView;
    EditText input;
    ImageView enter;
    static ListViewAdapter adapter;
    static ArrayList<String> items;
    static Context context;

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        openDialog = findViewById(R.id.open_dialog);
        openDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });

        listView = findViewById(R.id.list);
//        enter = findViewById(R.id.add);
        context = getApplicationContext();

        items = new ArrayList<>();
        Item item;

        item = new Item("apple", "30", "lalala", "", "", ""  );
        addItem(item.toString());

        item = new Item("bannana", "34", "lasasdas", "3", "asas", "oooo"  );
        addItem(item.toString());

        listView.setLongClickable(true);
        adapter = new ListViewAdapter(this, items);
        listView.setAdapter(adapter);

//        binding.list.setClickable(true);
//        binding.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
//                Item item = Item.fromString(items.get(i));
//
//                intent.putExtra("name", item.getName());
//                intent.putExtra("amount", item.getAmount());
//                intent.putExtra("details", item.getDetails());
//                intent.putExtra("state", item.getState());
//                intent.putExtra("lessee_name", item.getLessee_name());
//                intent.putExtra("lessee_phone", item.getLessee_phone());
//
//                startActivity(intent);
//            }
//        });

        // Display the item name when the item's row is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
                Item item = Item.fromString(items.get(i));

                intent.putExtra("name", item.getName());
                intent.putExtra("amount", item.getAmount());
                intent.putExtra("details", item.getDetails());
                intent.putExtra("state", item.getState());
                intent.putExtra("lessee_name", item.getLessee_name());
                intent.putExtra("lessee_phone", item.getLessee_phone());

                startActivity(intent);
            }
        });
        // Remove an item when its row is long pressed
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                removeItem(i);
                return false;
            }
        });

        // add item when the user presses the enter button
//        enter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String text = input.getText().toString();
//                if (text == null || text.length() == 0) {
//                    makeToast("Enter an item.");
//                } else {
//                    addItem(text);
//                    input.setText("");
//                    makeToast("Added " + text);
//                }
//            }
//        });
        loadContent();

    }

    // function to read grocery list from file and load it into ListView
    public void loadContent() {
        File path = getApplicationContext().getFilesDir();
        File readFrom = new File(path, "list.txt");
        byte[] content = new byte[(int) readFrom.length()];

        FileInputStream stream = null;
        try {
            stream = new FileInputStream(readFrom);
            stream.read(content);

            String s = new String(content);
            // [Apple, Banana, Kiwi, Strawberry]
            s = s.substring(1, s.length() - 1);
            String split[] = s.split(", ");

            // There may be no items in the grocery list.
            if (split.length == 1 && split[0].isEmpty())
                items = new ArrayList<>();
            else items = new ArrayList<>(Arrays.asList(split));

            adapter = new ListViewAdapter(this, items);
            listView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Override onDestroy() to save the contents of the grocery list right before the app is terminated
    @Override
    protected void onDestroy() {
        File path = getApplicationContext().getFilesDir();
        try {
            FileOutputStream writer = new FileOutputStream(new File(path, "list.txt"));
            writer.write(items.toString().getBytes());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    // function to remove an item given its index in the grocery list.
    public static void removeItem(int i) {
        makeToast("Removed: " + items.get(i));
        items.remove(i);
        listView.setAdapter(adapter);
    }

    // function to add an item given its name.
    public static void addItem(String item) {
        items.add(item);
        listView.setAdapter(adapter);
    }

    // function to make a Toast given a string
    static Toast t;

    private static void makeToast(String s) {
        if (t != null) t.cancel();
        t = Toast.makeText(context, s, Toast.LENGTH_SHORT);
        t.show();
    }

    //Function to display the custom dialog.
    void showCustomDialog() {

        final Dialog dialog = new Dialog(HomeActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog);

        //Initializing the views of the dialog.
        final EditText itemName = dialog.findViewById(R.id.item_name);
        final EditText itemAmount = dialog.findViewById(R.id.item_amount);
        final EditText itemDetails = dialog.findViewById(R.id.item_details);
        final EditText lesseeName = dialog.findViewById(R.id.lessee_name);
        final EditText lesseePhone = dialog.findViewById(R.id.lessee_phone);

        Button submitButton = dialog.findViewById(R.id.submit_button);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = itemName.getText().toString();
                String amount = itemAmount.getText().toString();
                String details = itemDetails.getText().toString();
                String lessee_name = lesseeName.getText().toString();
                String lessee_phone = lesseePhone.getText().toString();

                Pattern p = Pattern.compile("^\\d{10}$");
                Matcher m = p.matcher(lessee_phone);

                if(!name.isEmpty() && !amount.isEmpty()){
                    if(lessee_name.isEmpty() && lessee_phone.isEmpty()){
                        Item item = new Item(name, amount, details, "available", "", ""  );
                        addItem(item.toString());
                        makeToast("Added with available");
                    }else if(!lessee_name.isEmpty() && !lessee_phone.isEmpty()){
                        if(m.matches()){
                            Item item = new Item(name, amount, details, "unavailable", lessee_name, lessee_phone);
                            addItem(item.toString());
                            makeToast("Added with unavailable");
                        }else{
                            makeToast("Phone is wrong");
                        }
                    }else{
                        makeToast("Missing Lessee Values");
                    }
                }else{
                    makeToast("Missing Item Values");
                }


//                Item item = new Item(name, amount, details, "", "", ""  );

//                populateInfoTv(name,amount,details);

//                addItem(item.toString());
//                input.setText("");
//                makeToast("Added " + name);

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    void populateInfoTv(String name, String amount, String details) {
        infoTv.setVisibility(View.VISIBLE);

        if(!name.isEmpty() && !amount.isEmpty()){
            infoTv.setText(String.format(getString(R.string.info), name, amount, details));
            Toast toast = Toast.makeText(getApplicationContext(), "New Item added succesfully", Toast.LENGTH_LONG);
            toast.show();
        }else{
            Toast toast = Toast.makeText(getApplicationContext(), "Missing values", Toast.LENGTH_LONG);
            toast.show();
        }
    }


}