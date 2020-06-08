package com.example.dandavis.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Cart extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewManager;
    ArrayList<CartItem> cartItems;
    private DBAdapter db;
    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        db = new DBAdapter(this);
        cartItems = new ArrayList<CartItem>(  );
        userId = getIntent().getIntExtra("userId", -1);
        SharedPreferences sharedPreferences = getSharedPreferences("datapersistance", Context.MODE_PRIVATE);
        Map<String, ?> prefsMap = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry: prefsMap.entrySet()) {
            Set<String> valueSet = sharedPreferences.getStringSet(entry.getKey(), Collections.singleton("null"));
            Iterator it= valueSet.iterator();
            int index = 0;
            String quantity = "";
            String imageId = "";
            double price = 0;
            String productName = "";
            while(it.hasNext())
            {
              if(index == 0){
                  quantity = String.valueOf(it.next());
              }
              else{
                  imageId = String.valueOf(it.next());
              }
                index++;
            }

            Log.i(entry.getKey(),"PRODUCT id");
            db.open();
            Cursor mCursor = db.getProductById(Integer.parseInt(entry.getKey()));
            if(mCursor.getCount() != 0)  {
                mCursor.moveToFirst();
                price = mCursor.getFloat(2);
                productName = mCursor.getString(1);
            }
            db.close();

            Log.i(productName,"PRODUCT");
            cartItems.add( new CartItem( quantity,entry.getKey(), imageId, productName, price));
        }

        recyclerView = findViewById( R.id.cartRecyclerView );
        recyclerViewManager = new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( recyclerViewManager );
        recyclerView.setHasFixedSize( true );
        recyclerViewAdapter = new CartAdapter( getApplicationContext(), cartItems );
        recyclerView.setAdapter( recyclerViewAdapter );
    }
}
