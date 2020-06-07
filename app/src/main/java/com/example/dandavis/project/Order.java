package com.example.dandavis.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Order extends AppCompatActivity {

    String productName = "";
    int productImageId;
    double price;
    TextView textView;
    TextView productPrice;
    ImageView imageView;
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
}
