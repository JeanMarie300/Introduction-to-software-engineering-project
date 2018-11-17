package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.Password);
    }

    public void OnFinish(View view) {

        String inputUsername = username.getText().toString();

        String inputPassword = password.getText().toString();

        boolean emptyInput = inputUsername.isEmpty() || inputPassword.isEmpty();

        int i =0;

        // Verify that the person has a valid password or email


        boolean invalidAuthentification = false;

        if(emptyInput){
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Empty field alert");
            alert.setMessage("You need to fill up all the field");
            alert.setPositiveButton("OK",null);
            alert.show();
        } else{
            Intent intent = new Intent(this, WelcomePage.class);
           // intent.putExtra("USER_TYPE",  userInfo.get("UserType"));
            intent.putExtra("FIRST_NAME",  "");
            intent.putExtra("LAST_NAME", "");
            startActivity(intent);
        }
    }


}
