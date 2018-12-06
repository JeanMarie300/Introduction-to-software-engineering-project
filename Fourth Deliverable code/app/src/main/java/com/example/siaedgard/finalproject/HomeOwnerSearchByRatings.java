package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

public class HomeOwnerSearchByRatings extends AppCompatActivity {


    private Spinner spinner;
    public static final String[] paths = {"Select your rating", "1", "2","3","4","5"};
    private String userId;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_owner_search_by_ratings);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            userId = bd.get("USER_ID").toString();
        }
        spinner = (Spinner)findViewById(R.id.Rating);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(HomeOwnerSearchByRatings.this,
                android.R.layout.simple_spinner_item,paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void OnFinish(View view) {

        String inputRate = spinner.getSelectedItem().toString();;
        MyDBHandler dbHandler = new MyDBHandler(this);
        List<User> serviceProviderFound = dbHandler.findServiceProviderByRating( inputRate);
        boolean invalidAuthentification = serviceProviderFound.isEmpty();
        if (invalidAuthentification) {
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Wrong information");
            alert.setMessage("No service Provider have this rating");
            alert.setPositiveButton("OK",null);
            alert.show();
        } else if (inputRate.equals("Select your rating")) {
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Wrong rating input");
            alert.setMessage("Please select a valid rating");
            alert.setPositiveButton("OK",null);
            alert.show();
        } else {
            Intent intent = new Intent(this, ServiceProviderListByRating.class);
            intent.putExtra("USER_ID", userId);
            intent.putExtra("RATING", inputRate);
            startActivity(intent);
        }
    }
}
