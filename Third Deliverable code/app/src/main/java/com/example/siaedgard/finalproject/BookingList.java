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

public class BookingList extends ArrayAdapter<Booking> {
    private Activity context;
    List<Booking> bookings;

    public BookingList(Activity context, List<Booking> bookings) {
        super(context, R.layout.providerlist, bookings);
        this.context = context;
        this.bookings = bookings;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.booking_list, null, true);
        Booking booking= bookings.get(position);
        Date date = null;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try
        {
            date = format.parse(booking.getDateOfBooking());
        }
        catch (ParseException e)
        {
            System.out.println("Problem while parsing the date");
        }

        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd");
        String stringDate = formatter.format(date);


        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewDate = (TextView) listViewItem.findViewById(R.id.textViewDate);
        TextView textViewRating = (TextView) listViewItem.findViewById(R.id.textViewRating);

        if (booking.getRating().equals("-1")) {
            textViewRating.setText("Service not rated");
        } else {
            textViewRating.setText( "Service rate : "+booking.getRating() + " /5");
        }

        textViewName.setText( "Booking with "+ booking.getService_provider_id());

        textViewDate.setText( "On  "+ stringDate);

        return listViewItem;
    }
}