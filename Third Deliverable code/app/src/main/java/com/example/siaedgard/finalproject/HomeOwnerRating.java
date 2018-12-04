package com.example.siaedgard.finalproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class HomeOwnerRating extends AppCompatActivity {
    EditText FeedbackText, Rating;
    String Name, lastName, userType, userId, sessionType, service_provider;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_layout);
        FeedbackText =  findViewById(R.id.feedback_text);
        Rating = findViewById(R.id.rating);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            sessionType = bd.get("USER_TYPE") + " Session";
            Name = bd.get("FIRST_NAME").toString();
            userType = bd.get("USER_TYPE").toString();
            userId = bd.get("USER_ID").toString();
        }
    }

    public void onFinish(View view){
        MyDBHandler dbHandler = new MyDBHandler(this);
        String feedbackComment = FeedbackText.getText().toString();
        String serviceRating = Rating.getText().toString();

        if(feedbackComment.isEmpty()||serviceRating.isEmpty()){
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Empty field alert");
            alert.setMessage("You need to fill up all the field");
            alert.setPositiveButton("OK",null);
            alert.show();
        } else {
            Feedback feedback = new Feedback("1",  feedbackComment, serviceRating, userId, service_provider);
            long feedbackId = dbHandler.addFeedback(feedback);
            feedback.setId(String.valueOf(feedbackId));
            finish();
            startActivity(getIntent());
        }
    }

}