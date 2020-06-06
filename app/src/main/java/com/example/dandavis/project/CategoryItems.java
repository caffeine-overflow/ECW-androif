package com.example.dandavis.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class CategoryItems extends AppCompatActivity {

    int categoryId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_items);
        categoryId = getIntent().getIntExtra("categoryId", -1);
        Log.i(String.valueOf(categoryId), "categoryId");
    }
}
