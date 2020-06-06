package com.example.dandavis.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ArrayList<Category> categoryList;
    private DBAdapter db;
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewManager;
    TextView textViewDisplay;
    int categoryImages [] ={R.drawable.blazer,R.drawable.denim,R.drawable.sweater,R.drawable.floral,R.drawable.cardigan};
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        userId = getIntent().getIntExtra("userId", -1);
        Log.i(String.valueOf(userId) , "userid");

        db = new DBAdapter(this);
        categoryList = new ArrayList<Category>(  );
        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById((R.id.toolbar));
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        recyclerView = findViewById( R.id.recyclerView );
        recyclerViewManager = new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( recyclerViewManager );
        recyclerView.setHasFixedSize( true );

        db.open();
        Cursor c;
        c = db.getAllCategories();
        if(c.moveToFirst())
        {
            do{
                categoryList.add( new Category(c.getInt( 0 ),c.getString( 1 )) );
                Log.i(c.getString( 1 ),"checking category");
            } while(c.moveToNext());
        }
        db.close();

        recyclerViewAdapter = new MyAdapter( getApplicationContext(), categoryList, categoryImages );
        recyclerView.setAdapter( recyclerViewAdapter );
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.profile:
                Toast.makeText(Main2Activity.this, "Profile", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }
        return false;
    }
}
