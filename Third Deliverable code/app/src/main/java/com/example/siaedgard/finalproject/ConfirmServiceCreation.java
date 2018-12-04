package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmServiceCreation extends AppCompatActivity {

    private String userId;
    private TextView textView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_page);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        textView3 = findViewById(R.id.textView3);
        Button ButtonAction1 = findViewById(R.id.button4);
        ButtonAction1.setText("Create a new service");
        textView3.setText("You have succesfully added a new service in the application");
        if(bd != null)
        {
            userId = (String) bd.get("USER_ID");
        }
    }

    public void OncreatenewService(View view) {
        Intent intent = new Intent(this, AdminCreateService.class);
        intent.putExtra("USER_ID", userId);
        startActivity(intent);
        finish();
    }

    public void OnreturntoWelcomepage(View view) {
        Intent intent = new Intent(this, AdminWelcomePage.class);
        intent.putExtra("USER_ID", userId);
        startActivity(intent);
        finish();
    }




}