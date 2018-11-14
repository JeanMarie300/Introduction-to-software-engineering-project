package com.example.siaedgard.finalproject;

import android.content.DialogInterface;
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

            final Intent intent = new Intent(this, MainActivity.class);
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            startActivity(intent);
                            break;
                        case DialogInterface.BUTTON_NEGATIVE:
                            break;
                    }
                }
            };
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You entered a wrong username or password, click yes if you want to open a new account" +
                    " click no to try again").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();
        }
        else {
            Intent intent = new Intent(this, WelcomePage.class);
            intent.putExtra("USER_TYPE",  userFound.getUserType());
            intent.putExtra("FIRST_NAME",  userFound.getFirstName());
            intent.putExtra("LAST_NAME", userFound.getLastName());
            startActivity(intent);
        }
    }
}
