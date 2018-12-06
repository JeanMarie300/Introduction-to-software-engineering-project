package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class User_Type_Selection extends AppCompatActivity {
    private Spinner spinner;
    public static final String[] paths = {"Admin", "Home owner", "Service provider"};
    EditText Birthday, Name, number ,address ,postalcode ,username,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newuseractivity);
        spinner = (Spinner)findViewById(R.id.DropDown1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(User_Type_Selection.this,
                android.R.layout.simple_spinner_item,paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void OnNext(View view) {
        String test=  spinner.getSelectedItem().toString();
        Intent intent = new Intent(this, user_Type.class);
        intent.putExtra("USER_TYPE",  test.toString());
        startActivity(intent);
        finish();
    }

}

