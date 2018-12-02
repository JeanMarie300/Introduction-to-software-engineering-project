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
        setContentView(R.layout.login_activity);
        username = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.Password);
    }

    public void OnFinish(View view) {

        String inputUsername = username.getText().toString();

        String inputPassword = password.getText().toString();

        boolean emptyInput = inputUsername.isEmpty() || inputPassword.isEmpty();

        MyDBHandler dbHandler = new MyDBHandler(this);

        User userFound = dbHandler.findUser(inputUsername);

        boolean invalidAuthentification = userFound == null;

        if(emptyInput){
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Empty field alert");
            alert.setMessage("You need to fill up all the field");
            alert.setPositiveButton("OK",null);
            alert.show();
        }
        else if (invalidAuthentification) {
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Wrong information");
            alert.setMessage("You entered a wrong password or email");
            alert.setPositiveButton("OK",null);
            alert.show();
        }
        else {
            if (userFound.getUserType().equals("Home owner")) {
                Intent intent = new Intent(this, HomeOwnerWelcomePage.class);
                intent.putExtra("USER_TYPE", userFound.getUserType());
                intent.putExtra("FIRST_NAME", userFound.getFirstName());
                intent.putExtra("LAST_NAME", userFound.getLastName());
                intent.putExtra("USER_ID", userFound.getId());
                startActivity(intent);
                finish();
            } else {
                Intent intent = userFound.getUserType().equals("Admin") ? new Intent(this, AdminWelcomePage.class) : new Intent(this, ServiceProviderWelcomePage.class);
                intent.putExtra("USER_TYPE", userFound.getUserType());
                intent.putExtra("FIRST_NAME", userFound.getFirstName());
                intent.putExtra("LAST_NAME", userFound.getLastName());
                intent.putExtra("USER_ID", userFound.getId());
                startActivity(intent);
                finish();
            }
        }
    }

    public void OnNewUser(View view) {
        Intent intent = new Intent(this, User_Type_Selection.class);
        startActivity(intent);
    }
}