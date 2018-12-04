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

public class ServiceProviderAvailabilities extends AppCompatActivity {

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
        setContentView(R.layout.service_provider_availabilities);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            userId = (String) bd.get("USER_ID");
        }

        dateInfo = new HashMap<>();
        dateInfo.put("userId",userId);

        firstCalendar = Calendar.getInstance();
        thirdCalendar = Calendar.getInstance();
        FourthCalendar = Calendar.getInstance();

        initialDate = (EditText) findViewById(R.id.Begining);
        InitTime = (EditText) findViewById(R.id.InitTime);
        EndTime = (EditText) findViewById(R.id.EndTime);



        initialDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DatePickerDialog(ServiceProviderAvailabilities.this, initialdate, firstCalendar
                        .get(Calendar.YEAR), firstCalendar.get(Calendar.MONTH),
                        firstCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        InitTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new TimePickerDialog(ServiceProviderAvailabilities.this, InitTimeListener, thirdCalendar
                        .get(Calendar.HOUR_OF_DAY), thirdCalendar.get(Calendar.MINUTE),
                        false).show();
            }
        });

        EndTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new TimePickerDialog(ServiceProviderAvailabilities.this, EndTimeListener, FourthCalendar
                        .get(Calendar.HOUR_OF_DAY), FourthCalendar.get(Calendar.MINUTE),
                        false).show();
            }
        });
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
            dateInfo.put("initDate", initialDate.getText().toString());
            dateInfo.put("initTime", InitTime.getText().toString());
            dateInfo.put("finalTime", EndTime.getText().toString());
            addAvailabilities (dateInfo,userId);
            finish();
            Intent intent = new Intent(this, ConfirmationPage.class);
            intent.putExtra("USER_ID", userId);
            startActivity(intent);
        }
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

    public void addAvailabilities (HashMap<String, String> map, String Id) {
        MyDBHandler dbHandler = new MyDBHandler(this);
        dbHandler.addAvailabilities(map, Integer.parseInt(Id));

    }
}
