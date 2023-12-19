package com.example.databaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ViewUsersActivity extends AppCompatActivity {

    ListView lvUsers, lvUserData;
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

        lvUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                
                String clickedItem = adapterView.getItemAtPosition(i).toString().trim();
                String paramName = "username";
                String paramValue = clickedItem;

                Intent intent =  new Intent(ViewUsersActivity.this, UserDataActivity.class);;
                intent.putExtra(paramName, paramValue);
                startActivity(intent);
            }
        });
    }
}