package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class user_Type extends AppCompatActivity {
    private Spinner spinner;
    public static final String[] paths = {"Admin", "Home owner", "Service provider"};
    EditText Birthday, Name, number ,address ,postalcode ,username,password;
    private String [] answers = new String [7];
    private String userType, userId;
    HashMap<String, String> userInfo = new HashMap<String, String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if (bd != null) {
            userType = bd.get("USER_TYPE").toString();
        }

        Name = (EditText) findViewById(R.id.FirstName);
        Birthday = (EditText) findViewById(R.id.Birthday);
        number = (EditText) findViewById(R.id.number);
        address = (EditText) findViewById(R.id.streetName);
        postalcode = (EditText) findViewById(R.id.PostalCode);
        username = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.Password);
    }

    boolean isLegalDate(String date) {
        SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy/MM/dd");
        sdfrmt.setLenient(false);
        try
        {
            sdfrmt.parse(date);
        }
        catch (ParseException e)
        {
            return false;
        }
        return true;
    }

    public boolean newUser () {
        User user = new User("1",userInfo.get("FirstName"),"", userInfo.get("Birthday"), userInfo.get("PostalCode"), userType, userInfo.get("UserName"), userInfo.get("Password"),userInfo.get("address"));
        MyDBHandler dbHandler = new MyDBHandler(this);
        long userID = dbHandler.addUsers(user);
        if(userID == -2) {
            return false;
        } else {
            user.setId(Long.toString(userID));
            userId = Long.toString(userID);
            return true;
        }
    }

    public void OnNext(View view) {
        answers[0]=  Name.getText().toString();
        answers[1] = Birthday.getText().toString();
        answers[2] = number.getText().toString();
        answers[3] = address.getText().toString();
        answers[4] = postalcode.getText().toString();
        answers[5] = username.getText().toString();
        answers[6] = password.getText().toString();

        boolean invalid = false;
        boolean temp = false;
        int i =0;
        while(!invalid && i<answers.length ) {
            if (answers[i].isEmpty()) {
                invalid = true;
            }
            i++;
        }
        if(invalid){
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Empty field alert");
            alert.setMessage("You need to fill up all the field");
            alert.setPositiveButton("OK",null);
            alert.show();
        } else if (!isLegalDate(Birthday.getText().toString())) {
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Invalid date");
            alert.setMessage("Please enter a valid birthday");
            alert.setPositiveButton("OK",null);
            alert.show();
        } else {
            userInfo.put("FirstName",answers[0]);
            userInfo.put("Birthday",answers[1]);
            userInfo.put("PostalCode",answers[4]);
            userInfo.put("UserName",answers[5]);
            userInfo.put("Password",answers[6]);
            userInfo.put("address",answers[3]);
            temp = newUser();
            if (!temp) {
                AlertDialog.Builder  alert = new AlertDialog.Builder(this);
                alert.setTitle("Username already taken");
                alert.setMessage("This username already exist");
                alert.setPositiveButton("OK",null);
                alert.show();
            } else {
                if(userType.equals(paths[0])){
                    Intent intent = new Intent(this,AdminWelcomePage.class);
                    intent.putExtra("USER_TYPE",  userType.toString());
                    intent.putExtra("USER_BIRTHDAY",  Birthday.getText().toString());
                    intent.putExtra("FIRST_NAME",  Name.getText().toString());
                    intent.putExtra("USER_ID", userId);
                    startActivity(intent);
                    finish();
                } else if ((userType.equals(paths[1]))) {
                    Intent intent = new Intent(this,HomeOwnerWelcomePage.class);
                    intent.putExtra("USER_TYPE",  userType.toString());
                    intent.putExtra("USER_BIRTHDAY",  Birthday.getText().toString());
                    intent.putExtra("FIRST_NAME",  Name.getText().toString());
                    intent.putExtra("USER_ID", userId);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(this, ServiceProviderForm.class);
                    intent.putExtra("USER_TYPE", userType.toString());
                    intent.putExtra("USER_BIRTHDAY", Birthday.getText().toString());
                    intent.putExtra("USER_FIRSTNAME", Name.getText().toString());
                    intent.putExtra("USER_ID", userId);
                    startActivity(intent);
                    finish();
                }
            }
        }

    }
}
