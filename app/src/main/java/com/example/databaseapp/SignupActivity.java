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

public class SignupActivity extends AppCompatActivity {

    EditText etSignupEmail, etSignupUsername, etSignupPassword, etSignupPhone;
    Button btnSignup;
    TextView tvAlreadyAccount;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etSignupEmail = findViewById(R.id.et_SignupEmail);
        etSignupUsername = findViewById(R.id.et_SignupUsername);
        etSignupPassword = findViewById(R.id.et_SignupPassword);
        etSignupPhone = findViewById(R.id.et_SignupPhone);

        btnSignup = findViewById(R.id.btn_Signup);
        tvAlreadyAccount = findViewById(R.id.tv_AlreadyAccount);

        tvAlreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(i);
                finishAffinity();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etSignupEmail.getText().toString();
                String username = etSignupUsername.getText().toString();
                String password = etSignupPassword.getText().toString();
                String phone = etSignupPhone.getText().toString();

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(phone)){
                    databaseHelper = new DatabaseHelper(SignupActivity.this);
                    String result = databaseHelper.insertUser(email,username,password,phone);
                    Toast.makeText(SignupActivity.this, result, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(SignupActivity.this, "Bütün alanlar doldurulmalıdır !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}