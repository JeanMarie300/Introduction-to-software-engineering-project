package com.example.siaedgard.finalproject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ServiceList extends ArrayAdapter<Services> {
    private Activity context;
    List<Services> services;

    public ServiceList(Activity context, List<Services> services) {
        super(context, R.layout.servicelist, services);
        this.context = context;
        this.services = services;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.servicelist, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewRate = (TextView) listViewItem.findViewById(R.id.textViewRate);

        Services service = services.get(position);
        textViewName.setText(service.getName());
        textViewRate.setText(String.valueOf(service.gethourRate()));
        return listViewItem;
    }
}