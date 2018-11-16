package com.example.siaedgard.finalproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class ServiceProviderAvailabilities extends AppCompatActivity {

    HashMap<String, Date> DateInput = new HashMap<String, Date>();
    EditText initialDate, finalDate;

    private int year; private int month; private int day;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_provider_availabilities);

        initialDate = (EditText) findViewById(R.id.Begining);
        finalDate = (EditText) findViewById(R.id.FinalTime);
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        initialDate.setText(new StringBuilder().append(month + 1).append("-").append(day).append("-") .append(year).append(" "));

    }

   /* Jean-Marie Part

    public void Oninitial (View view) {
         String initialDate = initialDate.toString();
        @Override
        protected Dialog onCreateDialog(int id) {
            switch (id) {
                case DATE_PICKER_ID:
                    return new DatePickerDialog(this, pickerListener, year, month,day); } return null; } private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(
                    DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                year = selectedYear;
                month = selectedMonth;
                day = selectedDay;
                Output.setText(new StringBuilder().append(month + 1) .append("-").append(day).append("-").append(year) .append(" "));
            }
        };

        showDialog(DATE_PICKER_ID);

    }

    public void OnFinal (View view) {
        String initialDate = initialDate.toString();
        @Override
        protected Dialog onCreateDialog(int id) {
            switch (id) {
                case DATE_PICKER_ID:
                    return new DatePickerDialog(this, pickerListener, year, month,day); } return null; } private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(
                    DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                year = selectedYear;
                month = selectedMonth;
                day = selectedDay;
                Output.setText(new StringBuilder().append(month + 1) .append("-").append(day).append("-").append(year) .append(" "));
            }
        };

        showDialog(DATE_PICKER_ID);

    }

    public void OnNext (View view) {
        finish();
        startActivity(getIntent());
    }

     Jean-Marie part */

    }
