package com.example.databaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;

import java.util.List;

public class UserDataActivity extends AppCompatActivity {

    ListView lvUserData;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        lvUserData = findViewById(R.id.lv_UserData);

        Intent intent = getIntent();
        if (intent != null) {
            String paramName = "username";
            String paramValue = intent.getStringExtra(paramName);

            databaseHelper = new DatabaseHelper(UserDataActivity.this);
            List<String> userDataList = databaseHelper.userData(paramValue);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, userDataList);
            lvUserData.setAdapter(adapter);

        }
    }
}