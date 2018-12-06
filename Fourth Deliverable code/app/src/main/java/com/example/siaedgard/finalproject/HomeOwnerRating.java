package com.example.siaedgard.finalproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class HomeOwnerRating extends AppCompatActivity {
    EditText FeedbackText, Rating;
    String Name, bookingId, userType, userId, sessionType, service_provider;
    Spinner spinner;
    public static final String[] paths = {"Select your rating", "1", "2","3","4","5"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_layout);
        FeedbackText =  findViewById(R.id.feedback_text);
        spinner = (Spinner)findViewById(R.id.rating);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(HomeOwnerRating.this,
                android.R.layout.simple_spinner_item,paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            userId = bd.get("USER_ID").toString();
            service_provider = bd.get("PROVIDER_ID").toString();
            bookingId = bd.get("BOOKING_ID").toString();
        }
    }

    public void onFinish(View view){
        MyDBHandler dbHandler = new MyDBHandler(this);
        String feedbackComment = FeedbackText.getText().toString();
        String serviceRating = spinner.getSelectedItem().toString();

        if(feedbackComment.isEmpty()||spinner.getSelectedItem().toString().equals(paths[0])){
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Empty field alert");
            alert.setMessage("You need to fill up all the field Or specify the rating");
            alert.setPositiveButton("OK",null);
            alert.show();
        } else {
            Feedback feedback = new Feedback("1",  feedbackComment, serviceRating, userId, service_provider,bookingId);
            long feedbackId = dbHandler.addFeedback(feedback);
            if (feedbackId == -2) {
                AlertDialog.Builder  alert = new AlertDialog.Builder(this);
                alert.setTitle("Service already rated");
                alert.setMessage("You have already rated this service ");
                alert.setPositiveButton("OK",null);
                alert.show();
            } else {
                feedback.setId(String.valueOf(feedbackId));
                Intent intent = new Intent(this, HomeOwnerWelcomePage.class);
                intent.putExtra("USER_ID", userId);
                startActivity(intent);
                finish();
            }
        }
    }

}