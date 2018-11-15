package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

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
        if(test.equals(paths[0])){
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("USER_TYPE",  paths[0]);
            startActivity(intent);
            finish();
        }else if ((test.equals(paths[1]))) {
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("USER_TYPE",  paths[1]);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, ServiceProviderForm.class);
            intent.putExtra("USER_TYPE",  paths[2]);
            startActivity(intent);
            finish();
        }
    }
}
