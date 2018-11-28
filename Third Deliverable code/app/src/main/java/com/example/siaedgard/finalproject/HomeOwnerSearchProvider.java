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
            String sessionType = bd.get("USER_TYPE") + " Session";
            Name = bd.get("FIRST_NAME").toString();
            lastName = bd.get("LAST_NAME").toString();
            userType = bd.get("USER_TYPE").toString();
            userId = bd.get("USER_ID").toString();
        }
    }

    public void OnByService(View view) {
        Intent intent = new Intent(this, HomeOwnerSearchByService.class);
        intent.putExtra("USER_TYPE",  userType);
        intent.putExtra("FIRST_NAME",  Name);
        intent.putExtra("LAST_NAME", lastName);
        intent.putExtra("USER_ID", userId);
        startActivity(intent);
    }

    public void OnByTime(View view) {
        Intent intent = new Intent(this, HomeOwnerSearchByTime.class);
        intent.putExtra("USER_TYPE",  userType);
        intent.putExtra("FIRST_NAME",  Name);
        intent.putExtra("LAST_NAME", lastName);
        intent.putExtra("USER_ID", userId);
        startActivity(intent);
    }

    public void OnByRating(View view) {
        Intent intent = new Intent(this, HomeOwnerSearchByRatings.class);
        intent.putExtra("USER_TYPE",  userType);
        intent.putExtra("FIRST_NAME",  Name);
        intent.putExtra("LAST_NAME", lastName);
        intent.putExtra("USER_ID", userId);
        startActivity(intent);
    }

}
