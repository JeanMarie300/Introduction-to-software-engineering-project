package com.example.siaedgard.finalproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class HomeOwnerSearchByTime extends AppCompatActivity {

    HashMap<String, String> dateInfo;
    EditText initialDate, InitTime, EndTime;
    Calendar firstCalendar, thirdCalendar, FourthCalendar;
    String userId, userType, Name, lastName;

    DatePickerDialog.OnDateSetListener initialdate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            firstCalendar.set(Calendar.YEAR, year);
            firstCalendar.set(Calendar.MONTH, monthOfYear);
            firstCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateFirstEditText();
        }

    };


    TimePickerDialog.OnTimeSetListener  InitTimeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay,
                              int minute) {
            thirdCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            thirdCalendar.set(Calendar.MINUTE, minute);
            updateThirdEditText();

        }
    };

    TimePickerDialog.OnTimeSetListener  EndTimeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay,
                              int minute) {
            FourthCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            FourthCalendar.set(Calendar.MINUTE, minute);
            updateFourthEditText();
        }
    };




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_owner_search_by_time);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            userId = (String) bd.get("USER_ID");
            userType = (String) bd.get("USER_TYPE");
            Name = (String) bd.get("FIRST_NAME");
            lastName = (String) bd.get("LAST_NAME");
        }

        dateInfo = new HashMap<>();
        dateInfo.put("userId",userId);

        firstCalendar = Calendar.getInstance();
        thirdCalendar = Calendar.getInstance();
        FourthCalendar = Calendar.getInstance();

        initialDate = (EditText) findViewById(R.id.Begining);
        InitTime = (EditText) findViewById(R.id.InitTime);
        EndTime = (EditText) findViewById(R.id.EndTime);




    }

    public void updateFirstEditText() {
        String myFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        initialDate.setText(sdf.format(firstCalendar.getTime()));
    }

    public void updateThirdEditText() {
        String myFormat = "hh:mm a";
        SimpleDateFormat sdf=new SimpleDateFormat(myFormat, Locale.US);
        Date d=new Date();
        InitTime.setText(sdf.format(thirdCalendar.getTime()));
    }

    public void updateFourthEditText() {
        String myFormat = "hh:mm a";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        EndTime.setText(sdf.format(FourthCalendar.getTime()));
    }

    private boolean isLegalDate(String date) {

        SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat finalDate = new SimpleDateFormat("yyyy/MM/dd");


        sdfrmt.setLenient(false);
        boolean value = false;
        try
        {
            sdfrmt.parse(date);
            if (new Date().after(sdfrmt.parse(date))) {
                value = false;
            } else {
                value = true;
            }

        }
        catch (ParseException e)
        {
            value = false;
            return value ;
        }
        return value;
    }

    public void OnNext (View view) {
        if (initialDate.getText().toString().matches("") || InitTime.getText().toString().matches("") || EndTime.getText().toString().matches("")) {
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Empty field");
            alert.setMessage("You need to fill up all the empty fields");
            alert.setPositiveButton("OK",null);
            alert.show();
        } else if (!isLegalDate(initialDate.getText().toString())) {
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Invalid date");
            alert.setMessage("Please enter a time slot that is greater than today");
            alert.setPositiveButton("OK",null);
            alert.show();
        } else {
            Intent intent = new Intent(this, ServiceProviderAvailabilities.class);
            startActivity(intent);
            finish();



        }
    }









}
