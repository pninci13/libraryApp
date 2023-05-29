package com.example.libraryapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


//TODO: Set font styles
//TODO: Start implementation of cards in home activity
//TODO: Make cards for home activity

public class MainActivity extends AppCompatActivity {
    private Button loginBtn;
    private Button signUpBtn;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = findViewById(R.id.loginButton);
        signUpBtn = findViewById(R.id.signUpButton);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = findViewById(R.id.emailInput);
                password = findViewById(R.id.passwordInput);

                if (login())
                    openHomeActivity();
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = findViewById(R.id.emailInput);
                password = findViewById(R.id.passwordInput);

                if(signUp())
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
            Toast toast = Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG);
            toast.show();
            return true;
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Login Error", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
    }

    private boolean signUp(){
        if(username.getText().toString().equals("username") && password.getText().toString().equals("password")){
            Toast toast = Toast.makeText(getApplicationContext(), "Signup Success", Toast.LENGTH_LONG);
            toast.show();
            return true;
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Signup Error", Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
    }

}