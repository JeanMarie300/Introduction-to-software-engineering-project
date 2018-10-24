package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText LastName, FirstName ,Birthday , PostalCode, userProfile,Password;
    TextView result;
    Button ButtonFinish;
    private Spinner spinner;
    private String [] answers = new String [7];
    public static final String[] paths = {"Admin", "Home owner", "Service provider"};

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
        userProfile = (EditText) findViewById(R.id.userProfile);
        PostalCode = (EditText) findViewById(R.id.PostalCode);
        Birthday = (EditText) findViewById(R.id.Birthday);
        FirstName = (EditText) findViewById(R.id.FirstName);
        LastName = (EditText) findViewById(R.id.LastName);


    }

    public void OnFinish(View view) {

        answers[0] = LastName.getText().toString();
        answers[1]=  FirstName.getText().toString();
        answers[2] = Birthday.getText().toString();
        answers[3] = PostalCode.getText().toString();
        answers[4] = spinner.getSelectedItem().toString();
        answers[5] = userProfile.getText().toString();
        answers[6] =Password.getText().toString();
        boolean invalid = false;
        TextView text = (TextView)findViewById(R.id.textView);
        int i =0;
        while(invalid == false && i<answers.length ) {
            if (answers[i].isEmpty()) {
                invalid = true;
            }
            i++;
        }
        if( invalid == true){

            // check the other conditions in the loop above
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Empty field alert");
            alert.setMessage("You need to fill up all the field");
            alert.setPositiveButton("OK",null);
            alert.show();
        } else{
            Intent intent = new Intent(this, WelcomePage.class);
            intent.putExtra("USER_TYPE", answers[4]);
            intent.putExtra("FIRST_NAME",  answers[1]);
            intent.putExtra("LAST_NAME", answers[0]);
            startActivity(intent);
        }

    }

}