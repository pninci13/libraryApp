package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private Button loginBtn;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = findViewById(R.id.loginButton);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = findViewById(R.id.emailInput);
                password = findViewById(R.id.passwordInput);

                if (login())
                    openHomeActivity();
            }
        });
    }

    private void openHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private boolean login() {
        if (username.getText().toString().equals("username") && password.getText().toString().equals("password")) {
            Toast toast = Toast.makeText(getApplicationContext(), "Login or Signup Success", Toast.LENGTH_LONG);
            toast.show();
            return true;
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Login or Signup Error", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
    }

}