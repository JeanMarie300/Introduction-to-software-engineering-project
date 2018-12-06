package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class AdminWelcomePage extends AppCompatActivity {


    private static  final String[] ActionBar = {"Create service", "Update and delete services"};
    private String Name, lastName, userType, userId;
    User user;

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
            userId = bd.get("USER_ID").toString();
            MyDBHandler dbHandler = new MyDBHandler(this);
            user = dbHandler.findUserById(userId);
            String sessionType = user.getUserType() + " Session";
            Button ButtonAction1 = findViewById(R.id.Action1);
            ButtonAction1.setText(ActionBar[0]);
            Button ButtonAction2 = findViewById(R.id.Action2);
            ButtonAction2.setText(ActionBar[1]);
            Name = user.getFirstName();
            String getName = "Welcome "+Name;
            FirsttxtView.setText(sessionType);
            SecondtxtView.setText(getName);
        }
    }

    public void OnFinish(View view) {
        Intent intent = new Intent(this, AdminCreateService.class);
        intent.putExtra("USER_ID",  user.getId());
        startActivity(intent);
    }

    public void OnFinish2(View view) {
        Intent intent = new Intent(this, AdminUpdateService.class);
        intent.putExtra("USER_ID",  user.getId());
        startActivity(intent);
    }

}
