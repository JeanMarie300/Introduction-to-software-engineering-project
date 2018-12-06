package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServiceProviderListByAvailability extends AppCompatActivity {
    TextView ServiceProviderName;
    HashMap<String, String> dateInfo;
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
            dateInfo = new HashMap<>();
            userId = bd.get("USER_ID").toString();
            dateInfo.put("initDate",bd.getString("INIT_DATE").toString());
            dateInfo.put("initTime", bd.getString("INIT_TIME").toString());
            dateInfo.put("finalTime", bd.getString("FINAL_TIME").toString());
            final List<User> providers = dbHandler.FindServiceProviderbyAvailability(dateInfo);
            UserList productsAdapter = new UserList(ServiceProviderListByAvailability.this, providers);
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


