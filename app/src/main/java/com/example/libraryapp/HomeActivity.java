package com.example.libraryapp;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    ImageView image;
    TextView openDialog;
    TextView infoTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        openDialog = findViewById(R.id.open_dialog);
        infoTv = findViewById(R.id.info_tv);
        openDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });


    }

    void showCustomDialog() {
        final Dialog dialog = new Dialog(HomeActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog);


        final EditText itemName = dialog.findViewById(R.id.item_name);
        final EditText itemAmount = dialog.findViewById(R.id.item_amount);
        final EditText itemDetails = dialog.findViewById(R.id.item_details);
        Button submitButton = dialog.findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = itemName.getText().toString();
                String amount = itemAmount.getText().toString();
                String details = itemDetails.getText().toString();
                populateInfoTv(name, amount, details);
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    //TODO: POPULATE THIS ITEMS INFOS INTO A LISTVIEW
    void populateInfoTv(String name, String amount, String details) {
        infoTv.setVisibility(View.VISIBLE);

        if (!name.isEmpty() && !amount.isEmpty()) {
            infoTv.setText(String.format(getString(R.string.info), name, amount, details));
            Toast toast = Toast.makeText(getApplicationContext(), "New Item added successfully", Toast.LENGTH_LONG);
            toast.show();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Missing values", Toast.LENGTH_LONG);
            toast.show();
        }
    }


    public void onClick(View view) {
    }
}