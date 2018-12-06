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


    EditText PostalCode, UserName, Password, PhoneNumber , Company_name ,  License , expertise,  number;
    String LastName, FirstName ,Birthday, userType, userId;
    TextView result;
    private Spinner spinner, secondSpinner;
    private String [] answers = new String [5];
    private Bundle bd;
    public static final String[] paths = {"Select your years of experience ...","Less than a year", "1", "2","3","4","5+"};
    public static final String [] yesOrNo = {"Are you licensed ?","yes","no"};
    HashMap<String, String> userInfo = new HashMap<String, String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider);
        PostalCode = (EditText) findViewById(R.id.PostalCode);
        expertise = (EditText) findViewById(R.id.expertise);
        PhoneNumber = (EditText) findViewById(R.id.PhoneNumber);
        Company_name= (EditText) findViewById(R.id.Company_name);
        spinner = (Spinner)findViewById(R.id.experienceYears);
        secondSpinner = (Spinner)findViewById(R.id.License);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            FirstName = bd.get("USER_FIRSTNAME").toString();
            Birthday = bd.get("USER_BIRTHDAY").toString();
            userType = bd.get("USER_TYPE").toString();
            userId =   bd.get("USER_ID") .toString();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ServiceProviderForm.this,
                android.R.layout.simple_spinner_item,paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ArrayAdapter<String> secondAdapter = new ArrayAdapter<String>(ServiceProviderForm.this,
                android.R.layout.simple_spinner_item,yesOrNo);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        secondSpinner.setAdapter(secondAdapter);

    }
    public void newServiceProvider () {

        MyDBHandler dbHandler = new MyDBHandler(this);
        User user = dbHandler.findUserById(userId);
        if (userInfo.get("experience_years").equals(paths[1])) {
            int noExperienceYears = 0;
            ServiceProvider newServiceProvider = new ServiceProvider(user, userInfo.get("PhoneNumber"),
                    userInfo.get("Company_name"), userInfo.get("license"), noExperienceYears, userInfo.get("expertise"));
            dbHandler.ServiceProviderInfo(newServiceProvider, userId);
        } else if (userInfo.get("experience_years").equals(paths[5])) {
            int highExperienceYears = 5;
            ServiceProvider newServiceProvider = new ServiceProvider(user, userInfo.get("PhoneNumber"),
                    userInfo.get("Company_name"), userInfo.get("license"), highExperienceYears, userInfo.get("expertise"));
            dbHandler.ServiceProviderInfo(newServiceProvider, userId);
        } else {
            ServiceProvider newServiceProvider = new ServiceProvider(user, userInfo.get("PhoneNumber"),
                    userInfo.get("Company_name"), userInfo.get("license"),Integer.parseInt(userInfo.get("experience_years")), userInfo.get("expertise"));
            dbHandler.ServiceProviderInfo(newServiceProvider, userId);
        }
    }

    private boolean verifyPostalCode(String postalCode) {
        if (postalCode.length() > 6) {
            return false;
        }
       else if (!Character.isLetter(postalCode.charAt(0)) || !Character.isLetter(postalCode.charAt(2)) || !Character.isLetter(postalCode.charAt(4))) {
            return false;
        } else
            return Character.isDigit(postalCode.charAt(1)) && Character.isDigit(postalCode.charAt(3)) && Character.isDigit(postalCode.charAt(5));
    }

    public void OnFinish(View view) {

        answers[0] =  PhoneNumber.getText().toString();
        answers[1] = Company_name.getText().toString();
        answers[2] = spinner.getSelectedItem().toString();
        answers[3] = secondSpinner.getSelectedItem().toString();
        answers[4] = expertise.getText().toString();

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
        }  else{
            userInfo.put("PhoneNumber",answers[0]);
            userInfo.put("Company_name",answers[1]);
            if(answers[2].equals("Less than a year")) {
                userInfo.put("experience_years","0");
            } else if (answers[2].equals("5+")) {
                userInfo.put("experience_years","5");
            } else {
                userInfo.put("experience_years",answers[2]);
            }
            userInfo.put("license",answers[3]);
            userInfo.put("expertise",answers[4]);
            newServiceProvider();
            Intent intent = new Intent(this, ServiceProviderWelcomePage.class);
            intent.putExtra("USER_TYPE",  userType);
            intent.putExtra("FIRST_NAME",  FirstName);
            intent.putExtra("USER_ID", userId);
            startActivity(intent);
            finish();
        }
    }
}