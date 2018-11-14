package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText LastName, FirstName ,Birthday , PostalCode, UserName, Password;
    TextView result;
    private Spinner spinner;
    private String [] answers = new String [7];
    public static final String[] paths = {"Admin", "Home owner", "Service provider"};
    HashMap<String, String> userInfo = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner)findViewById(R.id.DropDown1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        Password = (EditText) findViewById(R.id.Password);
        UserName = (EditText) findViewById(R.id.userName);
        PostalCode = (EditText) findViewById(R.id.PostalCode);
        Birthday = (EditText) findViewById(R.id.Birthday);
        FirstName = (EditText) findViewById(R.id.FirstName);
        LastName = (EditText) findViewById(R.id.LastName);
    }
    public void newUser () {
        User user = new User(userInfo.get("FirstName"),userInfo.get("LastName"), userInfo.get("Birthday"), userInfo.get("PostalCode"), userInfo.get("UserType"), userInfo.get("UserName"), userInfo.get("Password"));
        MyDBHandler dbHandler = new MyDBHandler(this);
        dbHandler.addUsers(user);
    }

    public void OnFinish(View view) {

        answers[0] = LastName.getText().toString();
        answers[1]=  FirstName.getText().toString();
        answers[2] = Birthday.getText().toString();
        answers[3] = PostalCode.getText().toString();
        answers[4] = spinner.getSelectedItem().toString();
        answers[5] = UserName.getText().toString();
        answers[6] =Password.getText().toString();
        boolean invalid = false;
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
        } else{
            userInfo.put("FirstName",answers[1]);
            userInfo.put("LastName",answers[0]);
            userInfo.put("Birthday",answers[2]);
            userInfo.put("PostalCode",answers[3]);
            userInfo.put("UserType",answers[4]);
            userInfo.put("UserName",answers[5]);
            userInfo.put("Password",answers[6]);
            newUser();
            Intent intent = new Intent(this, WelcomePage.class);
            intent.putExtra("USER_TYPE",  userInfo.get("UserType"));
            intent.putExtra("FIRST_NAME",  userInfo.get("FirstName"));
            intent.putExtra("LAST_NAME", userInfo.get("LastName"));
            startActivity(intent);
        }

    }

}