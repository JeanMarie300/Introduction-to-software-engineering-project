package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class user_Type extends AppCompatActivity {
    private Spinner spinner;
    public static final String[] paths = {"Admin", "Home owner", "Service provider"};
    EditText Birthday, FirstName, LastName;
    private String [] answers = new String [7];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);
        spinner = (Spinner)findViewById(R.id.DropDown1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(user_Type.this,
                android.R.layout.simple_spinner_item,paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        Birthday = (EditText) findViewById(R.id.Birthday);
        FirstName = (EditText) findViewById(R.id.FirstName);
        LastName = (EditText) findViewById(R.id.LastName);
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

    public void OnNext(View view) {
        String test=  spinner.getSelectedItem().toString();
        answers[0] = LastName.getText().toString();
        answers[1]=  FirstName.getText().toString();
        answers[2] = Birthday.getText().toString();

        boolean invalid = false;
        boolean temp = false;
        int i =0;
        while(!invalid && i<3 ) {
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
            if(test.equals(paths[0])){
                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("USER_TYPE",  test.toString());
                intent.putExtra("USER_BIRTHDAY",  Birthday.getText().toString());
                intent.putExtra("USER_FIRSTNAME",  FirstName.getText().toString());
                intent.putExtra("USER_LASTNAME",  LastName.getText().toString());
                startActivity(intent);
                finish();
            }else if ((test.equals(paths[1]))) {
                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("USER_TYPE",  test.toString());
                intent.putExtra("USER_BIRTHDAY",  Birthday.getText().toString());
                intent.putExtra("USER_FIRSTNAME",  FirstName.getText().toString());
                intent.putExtra("USER_LASTNAME",  LastName.getText().toString());


                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(this, ServiceProviderForm.class);
                intent.putExtra("USER_TYPE",  test.toString());
                intent.putExtra("USER_BIRTHDAY",  Birthday.getText().toString());
                intent.putExtra("USER_FIRSTNAME",  FirstName.getText().toString());
                intent.putExtra("USER_LASTNAME",  LastName.getText().toString());
                startActivity(intent);
                finish();
            }
        }

    }
}
