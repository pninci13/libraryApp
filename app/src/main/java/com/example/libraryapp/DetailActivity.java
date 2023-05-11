package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.libraryapp.R;
import com.example.libraryapp.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    Button lendButton;
    String name, amount, details, state, lessee_name, lessee_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        lendButton = findViewById(R.id.lend_item_button);
//        lendButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showCustomDialog();
//            }
//        });

        Intent intent = this.getIntent();
        if (intent != null){
            name = intent.getStringExtra("name");
            amount = intent.getStringExtra("amount");
            details = intent.getStringExtra("details");
            state = intent.getStringExtra("state");
            lessee_name = intent.getStringExtra("lessee_name");
            lessee_phone = intent.getStringExtra("lessee_phone");

            binding.detailName.setText(name);
            binding.detailAmount.setText(amount);
            binding.detailLessee.setText(lessee_name);
            binding.detailDescription.setText(details);
            binding.detailAvailability.setText(state);
            binding.detailImage.setImageResource(R.drawable.item);
        }
    }

    void showCustomDialog() {
        final Dialog dialog = new Dialog(DetailActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_add_lessee);

        //Initializing the views of the dialog.
        final EditText lesseeName = dialog.findViewById(R.id.lessee_name);
        final EditText lesseePhone = dialog.findViewById(R.id.lessee_phone);
        Button submitButton = dialog.findViewById(R.id.submit_lessee_button);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lessee_name = lesseeName.getText().toString();
                lessee_phone = lesseePhone.getText().toString();

                Item item = new Item(name, amount, details, "unavailable", lessee_name, lessee_phone);

//                populateInfoTv(name,amount,details);
//
//                addItem(item.toString());
//                input.setText("");
//                makeToast("Added " + name);

                dialog.dismiss();
            }
        });

        dialog.show();
    }

}