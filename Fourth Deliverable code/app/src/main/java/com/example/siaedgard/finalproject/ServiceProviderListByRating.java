package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ServiceProviderListByRating extends AppCompatActivity {
    TextView ServiceProviderName;
    ListView listViewProvider;
    List<ServiceProvider> providers;
    String userId, userType, Name, providerId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provider_list_by_service);
        Intent intent = getIntent();
        providers = new ArrayList<>();
        ServiceProviderName = (TextView) findViewById(R.id.textViewName);
        listViewProvider = (ListView) findViewById(R.id.listViewProviders);
        MyDBHandler dbHandler = new MyDBHandler(this);
        Bundle bd = intent.getExtras();
        if (bd != null) {
            String rating = bd.get("RATING").toString();
            userId = bd.get("USER_ID").toString();
            final List<User> providers = dbHandler.findServiceProviderByRating(rating);
            UserList productsAdapter = new UserList(ServiceProviderListByRating.this, providers);
            listViewProvider.setAdapter(productsAdapter);

            listViewProvider.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    User user = providers.get(i);
                    showServiceProviderInformations (user);
                    return true;
                }
            });
        }
    }

    private void showServiceProviderInformations ( final User user){
        Intent intent = new Intent(this, ServiceProviderInformation.class);
        intent.putExtra("USER_ID", userId);
        intent.putExtra("PROVIDER_ID", user.getId());
        startActivity(intent);
    }

}


