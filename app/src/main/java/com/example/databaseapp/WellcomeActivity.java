package com.example.databaseapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WellcomeActivity extends AppCompatActivity {

    TextView tvWellcome;
    Button btnReset, btnViewUsers;

    DatabaseHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);

        tvWellcome = findViewById(R.id.tv_Wellcome);
        btnReset = findViewById(R.id.btn_Reset);
        btnViewUsers = findViewById(R.id.btn_ViewUsers);

        String username = getIntent().getStringExtra("USERNAME");
        if(username.equals("admin")){
            btnReset.setVisibility(View.VISIBLE);
        }
        else {
            btnReset.setVisibility(View.INVISIBLE);
        }
        tvWellcome.setText("Hoşgeldin " +username);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              showYesNoMessageBox("Veriler silinecek emin misiniz?");
            }
        });

        btnViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WellcomeActivity.this, ViewUsersActivity.class);
                startActivity(i);
            }
        });
    }

    private void showYesNoMessageBox(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //builder.setTitle(title);
        builder.setMessage(message);

        // Evet
        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                database = new DatabaseHelper(WellcomeActivity.this);
                int result = database.resetDatabase();
                if(result < 0 ){
                    Toast.makeText(WellcomeActivity.this, "Silme işlemi başarısız oldu", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else{
                    Toast.makeText(WellcomeActivity.this, "Kayıtlar silindi", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    Intent i = new Intent(WellcomeActivity.this, SignupActivity.class);
                    startActivity(i);
                    finishAffinity();
                }
            }
        });

        // Hayır
        builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                
                dialog.dismiss();
            }
        });

        // Mesaj kutusunu göster
        builder.show();
    }
}