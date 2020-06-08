package com.example.dandavis.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Set;

public class Order extends AppCompatActivity {

    String productName = "";
    int productImageId;
    double price;
    int productId;
    TextView textView;
    TextView productPrice;
    ImageView imageView;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        textView = findViewById(R.id.productName);
        imageView = (ImageView) findViewById(R.id.productImage);
        productPrice = findViewById(R.id.productPrice);

        productName = getIntent().getStringExtra("productName");
        price = getIntent().getDoubleExtra("productPrice",0);
        productImageId = getIntent().getIntExtra("productImage",0);
        productId = getIntent().getIntExtra("productId",0);

        NumberFormat formatter = new DecimalFormat("##.00");
        String outputPrice = formatter.format(price);
        textView.setText(productName);
        productPrice.setText("$"+outputPrice);
        imageView.setImageResource(productImageId);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.quantity, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void addToCart(View view) {
        productId = getIntent().getIntExtra("productId",0);
        SharedPreferences sharedPreferences = getSharedPreferences("datapersistance", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> hash_Set = new HashSet<String>();
        spinner = findViewById(R.id.spinner);
        Log.i(String.valueOf(spinner.getSelectedItemPosition()),"THIS");
        hash_Set.add(String.valueOf(spinner.getSelectedItemPosition()+1));
        hash_Set.add(String.valueOf(getIntent().getIntExtra("productImage",0)));
        editor.putStringSet(String.valueOf(productId), hash_Set);
        editor.apply();
        Toast.makeText(Order.this, "Added to Cart", Toast.LENGTH_SHORT).show();
        finish();
    }
}
