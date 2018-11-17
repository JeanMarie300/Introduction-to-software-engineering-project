package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ServiceProviderWelcomePage extends AppCompatActivity {

    private static  final String[] ActionBar = {"Enter Availabilities", "Delete services from profile", "Add Services To profil"};
    private String Name, lastName, userType;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
        TextView FirsttxtView = findViewById(R.id.sessionText);
        TextView SecondtxtView = findViewById(R.id.welcomeText);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            String sessionType = bd.get("USER_TYPE") + " Session";
            Button ButtonAction1 = findViewById(R.id.Action1);
            ButtonAction1.setText(ActionBar[0]);
            Button ButtonAction2 = findViewById(R.id.Action2);
            ButtonAction2.setText(ActionBar[1]);
            Button ButtonAction3 = findViewById(R.id.Action3);
            ButtonAction3.setText(ActionBar[2]);
            String getName = "Welcome "+(String) bd.get("FIRST_NAME")+ " " + bd.get("LAST_NAME");
            FirsttxtView.setText(sessionType);
            SecondtxtView.setText(getName);
            userId = (String) bd.get("USER_ID");
        }
    }

    public void OnFinish(View view) {
        Intent intent = new Intent(this, ServiceProviderAvailabilities.class);
        intent.putExtra("USER_TYPE",  userType);
        intent.putExtra("FIRST_NAME",  Name);
        intent.putExtra("LAST_NAME", lastName);
        intent.putExtra("USER_ID", userId);
        startActivity(intent);
    }

    public void OnFinish2(View view) {
        Intent intent = new Intent(this, AdminUpdateService.class);
        startActivity(intent);
    }

}
