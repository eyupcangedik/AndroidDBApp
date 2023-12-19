package com.example.databaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ViewUsersActivity extends AppCompatActivity {

    ListView lvUsers;
    DatabaseHelper databaseHelper;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);

        lvUsers = findViewById(R.id.lv_Users);
        databaseHelper = new DatabaseHelper(ViewUsersActivity.this);
        List<String> userList = databaseHelper.listAllUsers();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, userList);
        lvUsers.setAdapter(adapter);
    }
}