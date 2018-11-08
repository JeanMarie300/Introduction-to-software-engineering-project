package com.example.siaedgard.finalproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminUpdateService extends AppCompatActivity {
    TextView ServiceName;
    TextView ServiceHourRate;
    ListView listViewProducts;
    List <String> serviceName;
    List <String> hourRate;
    List<Services> services;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminupdateservices);
        services = new ArrayList<>();
        ServiceName = (TextView) findViewById(R.id.textViewName);
        ServiceHourRate = (TextView) findViewById(R.id.HourlyRate);
        MyDBHandler dbHandler = new MyDBHandler(this);
        Map ServiceAndRate = dbHandler.findServices();
        System.out.println(ServiceAndRate.toString());

       /* for (String element : ServiceAndRate.get("servicename")) {

        }
        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
            //getting product
            Product product = postSnapshot.getValue(Product.class);
            // adding product to the list
            products.add(product);
        }
        ProductList productsAdapter = new ProductList(MainActivity.this, products);
        listViewProducts.setAdapter(productsAdapter);


        if (product != null) {
            idView.setText(String.valueOf(product.getID()));
            skuBox.setText(String.valueOf(product.getSku()));
        } else {
            idView.setText("No Match Found");
        }*/
    }


    public void DisplayAllTheService () {
        Services services = new Services(ServiceName.getText().toString(), ServiceName.getText().toString());
        MyDBHandler dbHandler = new MyDBHandler(this);
        dbHandler.addServices(services);
    }

  /*  public void OnFinish(View view) {
        addNewService();
        startActivity(getIntent());
    }*/

}

