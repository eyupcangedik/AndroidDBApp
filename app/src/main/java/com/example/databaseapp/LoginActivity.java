package com.example.databaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etLoginUsername, etLoginPassword;
    Button btnLogin;
    TextView tvCreateAccount;

    DatabaseHelper databaseHelper;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        etLoginUsername = findViewById(R.id.et_LoginUsername);
        etLoginPassword = findViewById(R.id.et_LoginPassword);

        btnLogin = findViewById(R.id.btn_Login);

        tvCreateAccount = findViewById(R.id.tv_CreateAccount);

        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
                finishAffinity();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etLoginUsername.getText().toString();
                String password = etLoginPassword.getText().toString();

                if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)){

                    databaseHelper = new DatabaseHelper(LoginActivity.this);
                    String returnPassword = databaseHelper.findUser(username);

                    if(password.equals(returnPassword)){
                       Intent i = new Intent(LoginActivity.this, WellcomeActivity.class);
                       i.putExtra("USERNAME", username);
                       startActivity(i);
                    }
                    
                    else{
                        Toast.makeText(LoginActivity.this, "Hatalı kullanıcı adı veya şifre. ", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(LoginActivity.this, "Kullanıcı adı ve şifre boş bırakılamaz ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}