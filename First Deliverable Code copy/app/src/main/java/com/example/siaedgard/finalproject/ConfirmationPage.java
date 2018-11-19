package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ConfirmationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_page);
    }


    public void OncreatenewService(View view) {
        Intent intent = new Intent(this, ServiceProviderAvailabilities.class);
        startActivity(intent);
        finish();
    }


    public void OnreturntoWelcomepage(View view) {
        Intent intent = new Intent(this, ServiceProviderWelcomePage.class);
        startActivity(intent);
        finish();
    }




}
