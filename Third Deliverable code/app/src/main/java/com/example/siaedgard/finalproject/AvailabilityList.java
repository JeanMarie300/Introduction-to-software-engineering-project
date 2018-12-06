package com.example.siaedgard.finalproject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AvailabilityList extends ArrayAdapter<Availability> {
    private Activity context;
    List<Availability> availabilities;

    public AvailabilityList(Activity context, List<Availability> availabilities) {
        super(context, R.layout.servicelist, availabilities);
        this.context = context;
        this.availabilities = availabilities;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.servicelist, null, true);
        Availability availability = availabilities.get(position);
        Date date = null;


        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try
        {
            date = format.parse(availability.getInitialDate());
        }
        catch (ParseException e)
        {
            System.out.println("Problem while parsing the date");
        }

        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd");
        String stringDate = formatter.format(date);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewRate = (TextView) listViewItem.findViewById(R.id.textViewRate);

        textViewName.setText(stringDate);
        textViewRate.setText("From "+ availability.getInitialTime() + " to " + availability.getFinalTime());
        return listViewItem;
    }
}