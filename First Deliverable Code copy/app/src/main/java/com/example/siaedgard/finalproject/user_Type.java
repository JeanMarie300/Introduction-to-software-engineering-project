package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import static com.example.siaedgard.finalproject.MainActivity.paths;

public class user_Type extends AppCompatActivity {
    private Spinner spinner;
    Button Next;
    private String [] answers = new String [2];
    public static final String[] paths = {"Admin", "Home owner", "Service provider"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);
        spinner = (Spinner)findViewById(R.id.DropDown1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(user_Type.this,
                android.R.layout.simple_spinner_item,paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }



    public void OnNext(View view) {
        String test=  spinner.getSelectedItem().toString();
        if(test.equals(paths[0]) || (test.equals(paths[1]))){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            Intent intent = new Intent(this, ServiceProvider.class);
            startActivity(intent);
            finish();
        }
    }
}
