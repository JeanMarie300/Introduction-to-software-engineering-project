package com.example.siaedgard.finalproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class searchOptionsMainPage extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //check the layout search_layout.xml for activity on this page
    }

    public void gotToSearchByRating()
    {
        // please launch the next activity here
        // for example if the user choose to the option of searching by rating
        // you will have to launch the page rating
        // check the layout search_by_rating.xml
        // you can provide this as onclick

        // Sample according to what we did before in AdminCreateService
        // you may have to provide an extra info about the type of search
        // string type = rating << for this case because it is the goToSearchByRating
        /*
            Intent intent = new Intent(this, DisplayResultsFromSearch.class);
            //intent.putExtra("USER_TYPE",  userInfo.get("UserType"));
            //intent.putExtra("FIRST_NAME",  userInfo.get("FirstName"));
            //intent.putExtra("LAST_NAME", userInfo.get("LastName"));
            //intent.putExtra("USER_TYPE", userInfo.get("LastName"));
            //intent.putExtra("USER_ID", userId);

            startActivity(intent);  // should we launch it like this?
            finish();
         */
    }
    public void gotToSearchByServices()
    {
        // please launch the next activity here
        // for example if the user choose to the option of searching by Services
        // you will have to launch the page rating
        // you can provide this as onclick
    }
    public void gotToSearchByTime()
    {
        // please launch the next activity here
        // for example if the user choose to the option of searching by rTime
        // you will have to launch the page rating
        // you can provide this as onclick
    }

}

