package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    EditText LastName, FirstName ,Birthday , PostalCode, userProfile,Password;
    TextView result;
    Button ButtonFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnFinish(View view) {
        Intent intent = new Intent(getApplicationContext(), WelcomePage.class);
        startActivityForResult (intent,0);
    }
}