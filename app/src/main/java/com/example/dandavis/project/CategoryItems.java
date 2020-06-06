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

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.categoyRecyclerView);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        if(categoryId == 1)
            categoryImages = new int[]{R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5, R.drawable.b6, R.drawable.b7, R.drawable.b8};
        else if(categoryId == 2)
            categoryImages = new int[]{R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5, R.drawable.c6, R.drawable.c7, R.drawable.c8};
        else if(categoryId == 3)
            categoryImages = new int[]{R.drawable.e1, R.drawable.e2, R.drawable.e3, R.drawable.e4, R.drawable.e5, R.drawable.e6, R.drawable.e7, R.drawable.e8};
        else if(categoryId == 4)
            categoryImages = new int[]{R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6, R.drawable.d7, R.drawable.d8};
        else
            categoryImages = new int[]{R.drawable.a1, R.drawable.a11, R.drawable.a12, R.drawable.a13, R.drawable.a14, R.drawable.a15, R.drawable.a16, R.drawable.a17};


        db.open();
        Cursor c;
        c = db.getItemsByCategory(categoryId);
        if(c.moveToFirst())
        {
            do{
                Log.i(c.getString(1),"LAST");
                itemList.add( new Items(c.getInt( 0 ),c.getString( 1 ), c.getDouble(2)) );
            } while(c.moveToNext());
        }
        db.close();
        adapter = new CategoyItemsAdapter(this, itemList, categoryImages);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize( true );
    }


}
