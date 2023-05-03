package com.example.libraryapp;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        Button submitButton = dialog.findViewById(R.id.submit_button);
//      Button addImage = findViewById(R.id.add_Image);
//
//        addImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View vi) {
//                Toast toast = Toast.makeText(getApplicationContext(), "ola imagem", Toast.LENGTH_LONG);
//                toast.show();
//                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//                startActivityForResult(Intent.createChooser(intent,"Ecolha sua imagem"),1000);
//            }
//        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = itemName.getText().toString();
                String amount = itemAmount.getText().toString();
                String details = itemDetails.getText().toString();
                populateInfoTv(name,amount,details);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

//   void addImageItem() {
//        Button addImage = findViewById(R.id.add_Image);
//        addImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//                intent.setType("image/*");
//                startActivityForResult(Intent.createChooser(intent,"Ecolha sua imagem"),1000);
//                Toast toast = Toast.makeText(getApplicationContext(), "ola imagem", Toast.LENGTH_LONG);
//                toast.show();
//                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
//                    String[] permission = (Manifest.permission.READ_EXTERNAL_STORAGE);
//                    requestPermissions(permission, 1001);
//                }else{
//                    chooseImage();
//                }
//            }
//        });
//    }

//    private void chooseImage(){
//        Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//        intent.setType("image/*");
//        startActivityForResult(Intent.createChooser(intent,"Ecolha sua imagem"),1000);
//    }
//
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK) {
//            if (requestCode == 1000) {
//                image.setImageURI(data.getData());
//            }
//        }
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode){
//            case 1001: {
//                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    chooseImage();
//                }else{
//                    Toast
//                }
//            }
//        }
//    }

    void populateInfoTv(String name, String amount, String details) {
        infoTv.setVisibility(View.VISIBLE);
//        String acceptedText = "have";
//        if(!hasAcceptedTerms) {
//            acceptedText = "have not";
//        }

//        infoTv.setText(String.format("New item successfully added"));
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