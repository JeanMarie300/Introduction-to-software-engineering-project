package com.example.siaedgard.finalproject;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class HomeOwnerRating extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_layout);
        EditText FirsttxtView = findViewById(R.id.feedback_text);
        EditText SecondtxtView = findViewById(R.id.rating);
    }


    public void onFinish(View view){

        int i = 0;
        int min = 10;
        int max = 1000;
        }
    }
}