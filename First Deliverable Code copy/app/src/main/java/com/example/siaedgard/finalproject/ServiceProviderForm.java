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

public class ServiceProviderForm extends AppCompatActivity {


    EditText LastName, FirstName ,Birthday , PostalCode, UserName, Password, PhoneNumber , Company_name ,  License , expertise;
    TextView result;
    private Spinner spinner, secondSpinner;
    private String [] answers = new String [11];
    private Bundle bd;
    public static final String[] paths = {"Select your years of experience ...","Less than a year", "1", "2","3","4","5+"};
    public static final String [] yesOrNo = {"Are you licensed ?","yes","no"};
    HashMap<String, String> userInfo = new HashMap<String, String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider);
        Password = (EditText) findViewById(R.id.Password);
        UserName = (EditText) findViewById(R.id.userName);
        PostalCode = (EditText) findViewById(R.id.PostalCode);
        Birthday = (EditText) findViewById(R.id.Birthday);
        FirstName = (EditText) findViewById(R.id.FirstName);
        LastName = (EditText) findViewById(R.id.LastName);
        expertise = (EditText) findViewById(R.id.expertise);
        PhoneNumber = (EditText) findViewById(R.id.PhoneNumber);
        Company_name= (EditText) findViewById(R.id.Company_name);
        spinner = (Spinner)findViewById(R.id.experienceYears);
        secondSpinner = (Spinner)findViewById(R.id.License);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ServiceProviderForm.this,
                android.R.layout.simple_spinner_item,paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ArrayAdapter<String> secondAdapter = new ArrayAdapter<String>(ServiceProviderForm.this,
                android.R.layout.simple_spinner_item,yesOrNo);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        secondSpinner.setAdapter(secondAdapter);

        Intent intent = getIntent();
        bd = intent.getExtras();
    }
    public void newServiceProvider () {
        User user = new User(userInfo.get("FirstName"),userInfo.get("LastName"), userInfo.get("Birthday"), userInfo.get("PostalCode"), userInfo.get("UserType"), userInfo.get("UserName"), userInfo.get("Password"));
        MyDBHandler dbHandler = new MyDBHandler(this);
        long userID = dbHandler.addUsers(user);
        if (userInfo.get("experience_years").equals(paths[1])) {
            int noExperienceYears = 0;
            ServiceProvider newServiceProvider = new ServiceProvider(user, userInfo.get("PhoneNumber"),
                    userInfo.get("Company_name"), userInfo.get("license"), noExperienceYears, userInfo.get("expertise"));
            dbHandler.ServiceProviderInfo(newServiceProvider, Long.toString(userID));
        } else if (userInfo.get("experience_years").equals(paths[5])) {
            int highExperienceYears = 5;
            ServiceProvider newServiceProvider = new ServiceProvider(user, userInfo.get("PhoneNumber"),
                    userInfo.get("Company_name"), userInfo.get("license"), highExperienceYears, userInfo.get("expertise"));
            dbHandler.ServiceProviderInfo(newServiceProvider, Long.toString(userID));
        } else {
            ServiceProvider newServiceProvider = new ServiceProvider(user, userInfo.get("PhoneNumber"),
                    userInfo.get("Company_name"), userInfo.get("license"),Integer.parseInt(userInfo.get("experience_years")), userInfo.get("expertise"));
            dbHandler.ServiceProviderInfo(newServiceProvider, Long.toString(userID));
        }
    }

    public void OnFinish(View view) {

        answers[0] = LastName.getText().toString();
        answers[1]=  FirstName.getText().toString();
        answers[2] = Birthday.getText().toString();
        answers[3] = PostalCode.getText().toString();
        answers[4] =  PhoneNumber.getText().toString();
        answers[5] = Company_name.getText().toString();
        answers[6] = spinner.getSelectedItem().toString();
        answers[7] = secondSpinner.getSelectedItem().toString();
        answers[8] = UserName.getText().toString();
        answers[9] =Password.getText().toString();
        answers[10] = expertise.getText().toString();

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
        } else if (spinner.getSelectedItem().toString().equals(paths[0])){
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Incomplete years of experience");
            alert.setMessage("Please choose your number of years of experience");
            alert.setPositiveButton("OK",null);
            alert.show();
        } else if (spinner.getSelectedItem().toString().equals(yesOrNo[0])) {
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Don't know if licensed");
            alert.setMessage("You need to choose yes or no to determine if you are licensed or no");
            alert.setPositiveButton("OK",null);
            alert.show();
        } else {
            userInfo.put("FirstName",answers[1]);
            userInfo.put("LastName",answers[0]);
            userInfo.put("Birthday",answers[2]);
            userInfo.put("PostalCode",answers[3]);
            userInfo.put("PhoneNumber",answers[4]);
            userInfo.put("Company_name",answers[5]);
            userInfo.put("experience_years",answers[6]);
            userInfo.put("license",answers[7]);
            userInfo.put("UserType",bd.get("USER_TYPE").toString());
            userInfo.put("UserName",answers[8]);
            userInfo.put("Password",answers[9]);
            userInfo.put("expertise",answers[10]);
            newServiceProvider();
            Intent intent = new Intent(this, ServiceProviderWelcomePage.class);
            intent.putExtra("USER_TYPE",  bd.get("USER_TYPE").toString());
            intent.putExtra("FIRST_NAME",  userInfo.get("FirstName"));
            intent.putExtra("LAST_NAME", userInfo.get("LastName"));
            startActivity(intent);
        }
    }
}