package com.example.siaedgard.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    // These are the global variables
    EditText LastName, FirstName ,Birthday , PostalCode, userProfile,Password;
    TextView result;
    Button ButtonFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LastName  = (EditText) findViewById(R.id.LastName);
        FirstName  = (EditText) findViewById(R.id.FirstName);
        Birthday   = (EditText) findViewById(R.id.Birthday );
        PostalCode = (EditText) findViewById(R.id.PostalCode);
        userProfile = (EditText) findViewById(R.id.userProfile);

        Password = (EditText) findViewById(R.id.Password);
       ButtonFinish = (Button) findViewById(R.id.buttonFinish);

        /*
            Submit Button
        */
        ButtonFinish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String Name = LastName.getText().toString();
                String  Firstname  =  FirstName .getText().toString();
                String birthday = Birthday.getText().toString();
                String postalCode= PostalCode.getText().toString();
                String userprofile= userProfile .getText().toString();
                String password = Password.getText().toString();
                result.setText("Name:\t" + Name + "\nPassword:\t" + password );

            }
        });

        /*
            Reset Button
        */

    }
}