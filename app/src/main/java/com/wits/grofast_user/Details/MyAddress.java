package com.wits.grofast_user.Details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.wits.grofast_user.Adapter.ShowAllAddressAdapter;
import com.wits.grofast_user.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAddress extends AppCompatActivity {
    RecyclerView recyclerView;
    ShowAllAddressAdapter showAllAddressAdapter;
    List<Map<String, Object>> AddressItems;
    AppCompatButton add_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle(getString(R.string.my_address_page_name));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.outline_arrow_back_24);
        setContentView(R.layout.activity_my_address);
        recyclerView = findViewById(R.id.show_all_address_recycleview);
        add_address = findViewById(R.id.Add_address);
        AddressItems = new ArrayList<>();

        loadAddressItems();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        showAllAddressAdapter = new ShowAllAddressAdapter(this, AddressItems);
        recyclerView.setAdapter(showAllAddressAdapter);

        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),AddAddress.class);
                startActivity(i);
            }
        });

    }

    private void loadAddressItems() {
        Map<String, Object> item1 = new HashMap<>();
        item1.put("Address", "Bharuch");

        Map<String, Object> item2 = new HashMap<>();
        item2.put("Address", "Ankleshwar");

        AddressItems.add(item1);
        AddressItems.add(item2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}