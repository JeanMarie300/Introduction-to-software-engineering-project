package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmBooking extends AppCompatActivity {

    private String Name, lastName, userType, userId;
    private TextView textView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_page);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        textView3 = findViewById(R.id.textView3);
        textView3.setText("You have succesfully booked your service");
        if(bd != null)
        {
            userId = (String) bd.get("USER_ID");
            MyDBHandler dbHandler = new MyDBHandler(this);
            User user = dbHandler.findUserById(userId);
            Name = user.getFirstName();
            userType = user.getUserType();
            Button ButtonAction2 = findViewById(R.id.button4);
            ButtonAction2.setText("Book another service provider");
        }
    }

    public void OncreatenewService(View view) {
        Intent intent = new Intent(this, HomeOwnerSearchProvider.class);
        intent.putExtra("USER_TYPE",  userType);
        intent.putExtra("FIRST_NAME",  Name);
        intent.putExtra("USER_ID", userId);
        startActivity(intent);
        finish();
    }

    public void OnreturntoWelcomepage(View view) {
        Intent intent = new Intent(this, HomeOwnerWelcomePage.class);
        intent.putExtra("USER_TYPE",  userType);
        intent.putExtra("FIRST_NAME",  Name);
        intent.putExtra("USER_ID", userId);
        startActivity(intent);
        finish();
    }




}