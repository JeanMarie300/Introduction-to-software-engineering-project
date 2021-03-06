package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class HomeOwnerSearchProvider extends AppCompatActivity {


    private String Name, lastName, userType, userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_option);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            userId = bd.get("USER_ID").toString();
        }
    }

    public void OnByService(View view) {
        Intent intent = new Intent(this, HomeOwnerSearchByService.class);
        intent.putExtra("USER_ID", userId);
        startActivity(intent);
    }

    public void OnByTime(View view) {
        Intent intent = new Intent(this, HomeOwnerSearchByTime.class);
        intent.putExtra("USER_ID", userId);
        startActivity(intent);
    }

    public void OnByRating(View view) {
        Intent intent = new Intent(this, HomeOwnerSearchByRatings.class);
        intent.putExtra("USER_ID", userId);
        startActivity(intent);
    }

}
