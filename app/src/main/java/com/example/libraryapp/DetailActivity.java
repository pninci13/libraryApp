package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.libraryapp.databinding.ActivityDetailBinding;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    Button lendButton;
    String name;
    String amount;
    String details;
    String state;
    String lessee_name;
    String lessee_phone;
    CheckBox availableBox, unavailableBox;

    String id;

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
            id = intent.getStringExtra("id");

            binding.detailName.setText(name);
            binding.detailAmount.setText(amount);
            binding.detailLessee.setText(lessee_name);
            binding.detailDescription.setText(details);
            binding.detailAvailability.setText(state);



//            if(state.equals("available")){
//                binding.availableItem.setChecked(true);
//                binding.unavailableItem.setChecked(false);
//            }else{
//                binding.unavailableItem.setChecked(true);
//                binding.availableItem.setChecked(false);
//            }

            binding.detailImage.setImageResource(R.drawable.item);
        }

        Button changeButton = findViewById(R.id.change_button);
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = Integer.parseInt(id);
                ArrayList<String> items = HomeActivity.getItems();

                Item item = Item.fromString(items.get(index));
                items.remove(index);

                if(item.getState().equals("available"))
                    item.setState("unavailable");
                else
                    item.setState("available");


                items.add(index, item.toString());
                HomeActivity.setItems(items);

//                onCreate(savedInstanceState);

            }
        });

        Button editButton = findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });

//        Log.d("CREATION", HomeActivity.getItems().get(0));
//        HomeActivity.getItems();

    }

//    public static void getList(ArrayList<String> items) {
//        availableBox = findViewById(R.id.availableItem);
//        unavailableBox = findViewById(R.id.unavailableItem);
//
//        Item item = Item.fromString(items.get(Integer.parseInt(id)));
//
////
////        if(availableBox.isChecked()){
////
////        }
//
//    }




//    public static void getList(ArrayList<String> items){
//        Item item = Item.fromString(items.get(Integer.parseInt(id)));
//
//
//        if(){
//
//        }
//
//        item.setState()
//
//    }

    void showCustomDialog() {
        final Dialog dialog = new Dialog(DetailActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_edit);

        //Initializing the views of the dialog.
        final EditText itemLesseeName = dialog.findViewById(R.id.lessee_name);
        final EditText itemLesseePhone = dialog.findViewById(R.id.lessee_phone);
        final EditText itemAmount = dialog.findViewById(R.id.amount);

        Button submitButton = dialog.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item_lessee_name = itemLesseeName.getText().toString();
                String item_lessee_phone = itemLesseePhone.getText().toString();
                String item_amount = itemAmount.getText().toString();

                int index = Integer.parseInt(id);
                int lesseeAmount, oldAmount;

                ArrayList<String> items = HomeActivity.getItems();

                Item oldItem = Item.fromString(items.get(index));
                Item newItem = Item.fromString(items.get(index));
//
                lesseeAmount = Integer.parseInt(item_amount);
                oldAmount = Integer.parseInt(oldItem.getAmount());

//                items.remove(index);

                if(lesseeAmount < oldAmount){
                    HomeActivity.removeItem(index);
                    newItem.setAmount(Integer.toString(lesseeAmount));
                    newItem.setLessee_name(item_lessee_name);
                    newItem.setLessee_phone(item_lessee_phone);
                    HomeActivity.addItem(newItem.toString());

                    oldItem.setAmount(Integer.toString(oldAmount - lesseeAmount));
                    HomeActivity.addItem(oldItem.toString());

                }
                else if(lesseeAmount == oldAmount) {
                    HomeActivity.removeItem(index);
                    newItem.setAmount(Integer.toString(lesseeAmount));
                    newItem.setLessee_name(item_lessee_name);
                    newItem.setLessee_phone(item_lessee_phone);
                    HomeActivity.addItem(newItem.toString());
//                    items.add(index, newItem.toString());
                } else{
                    HomeActivity.makeToast("Invalid Amount");
                }

                HomeActivity.setItems(items);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void changeStatus() {

    }
}