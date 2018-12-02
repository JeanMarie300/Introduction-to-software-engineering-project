package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class HomeOwnerSearchByRatings extends AppCompatActivity {


    EditText rate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_owner_search_by_ratings);
    }

    public void OnFinish(View view) {

        String inputRate = rate.getText().toString();
        MyDBHandler dbHandler = new MyDBHandler(this);
        User rateFound = dbHandler.findUser( inputRate);
        boolean invalidAuthentification = rateFound == null;

        if (invalidAuthentification) {
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Wrong information");
            alert.setMessage("The service doesn't exist");
            alert.setPositiveButton("OK",null);
            alert.show();
        } else {
            Intent intent = new Intent(this, ServiceProviderRatings.class);
            startActivity(intent);
            finish();
        }
    }
}
