package com.example.dandavis.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class CategoryItems extends AppCompatActivity {

    private DBAdapter db;
    CategoyItemsAdapter adapter;
    int categoryId;
    int categoryImages [] = {};
    ArrayList<Items> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_items);
        db = new DBAdapter(this);
        itemList = new ArrayList<Items>(  );
        categoryId = getIntent().getIntExtra("categoryId", -1);
        Log.i(String.valueOf(categoryId), "categoryId");

        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8"};

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.categoyRecyclerView);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        categoryImages = new int[]{R.drawable.a1, R.drawable.a11, R.drawable.a12, R.drawable.a13, R.drawable.a14, R.drawable.a15, R.drawable.a16, R.drawable.a17};

        db.open();
        Cursor c;
        c = db.getItemsByCategory(categoryId);
        if(c.moveToFirst())
        {
            do{
                Log.i(String.valueOf(c.getDouble(2)),"LAST");
                itemList.add( new Items(c.getInt( 0 ),c.getString( 1 ), c.getDouble(2)) );
            } while(c.moveToNext());
        }
        db.close();
        adapter = new CategoyItemsAdapter(this, data, categoryImages);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize( true );
    }


}
