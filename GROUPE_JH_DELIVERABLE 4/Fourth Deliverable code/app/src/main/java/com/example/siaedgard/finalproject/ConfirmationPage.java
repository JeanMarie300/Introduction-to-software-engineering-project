package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ConfirmationPage extends AppCompatActivity {

    private String  userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_page);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            userId = (String) bd.get("USER_ID");
        }
    }

    public void OncreatenewService(View view) {
        Intent intent = new Intent(this, ServiceProviderAvailabilities.class);
        intent.putExtra("USER_ID", userId);
        startActivity(intent);
        finish();
    }

    public void OnreturntoWelcomepage(View view) {
        Intent intent = new Intent(this, ServiceProviderWelcomePage.class);
        intent.putExtra("USER_ID", userId);
        startActivity(intent);
        finish();
    }




}
