package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText LastName, FirstName ,Birthday , PostalCode, UserName, Password;
    TextView result;
    String userId, userType;
    private String [] answers = new String [7];
    HashMap<String, String> userInfo = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Password = (EditText) findViewById(R.id.Password);
        UserName = (EditText) findViewById(R.id.userName);
        PostalCode = (EditText) findViewById(R.id.PostalCode);
        Birthday = (EditText) findViewById(R.id.Birthday);
        FirstName = (EditText) findViewById(R.id.FirstName);
        LastName = (EditText) findViewById(R.id.LastName);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if (bd != null) {
            userType = bd.get("USER_TYPE").toString();
        }
    }
    public boolean newUser () {
        User user = new User("1",userInfo.get("FirstName"),userInfo.get("LastName"), userInfo.get("Birthday"), userInfo.get("PostalCode"), userType, userInfo.get("UserName"), userInfo.get("Password"));
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

    public void OnFinish(View view) {
        answers[0] = LastName.getText().toString();
        answers[1]=  FirstName.getText().toString();
        answers[2] = Birthday.getText().toString();
        answers[3] = PostalCode.getText().toString();
        answers[4] = UserName.getText().toString();
        answers[5] =Password.getText().toString();
        boolean invalid = false;
        boolean isValidDate = false;
        boolean temp = false;
        int i =0;
        while(!invalid && i<6 ) {
            if (answers[i].isEmpty()) {
                invalid = true;
            }
            i++;
        }

        if (!invalid) {
            isValidDate = isLegalDate(Birthday.getText().toString());
        }
        if(invalid){
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Empty field alert");
            alert.setMessage("You need to fill up all the field");
            alert.setPositiveButton("OK",null);
            alert.show();
        } else if (!isValidDate) {
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Invalid date");
            alert.setMessage("Please enter a valid birthday");
            alert.setPositiveButton("OK",null);
            alert.show();
        } else {
            userInfo.put("FirstName",answers[1]);
            userInfo.put("LastName",answers[0]);
            userInfo.put("Birthday",answers[2]);
            userInfo.put("PostalCode",answers[3]);
            userInfo.put("UserName",answers[4]);
            userInfo.put("Password",answers[5]);
            temp = newUser();
            if (!temp) {
                AlertDialog.Builder  alert = new AlertDialog.Builder(this);
                alert.setTitle("Username already taken");
                alert.setMessage("This username already exist");
                alert.setPositiveButton("OK",null);
                alert.show();
            } else {
                Intent intent = new Intent(this, AdminWelcomePage.class);
                intent.putExtra("FIRST_NAME",  userInfo.get("FirstName"));
                intent.putExtra("LAST_NAME", userInfo.get("LastName"));
                intent.putExtra("USER_TYPE", userType);
                intent.putExtra("USER_ID", userId);
                startActivity(intent);
                finish();
            }

        }
    }
}