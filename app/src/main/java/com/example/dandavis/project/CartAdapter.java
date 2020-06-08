package com.example.dandavis.project;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    Context context;
    View view1;
    ViewHolder viewHolder1;
    TextView textView;
    ImageView imageView;
    ArrayList<CartItem> cartItems;


    public CartAdapter(Context context1, ArrayList<CartItem> values){
        cartItems = new ArrayList<CartItem>(  );
        cartItems = values;
        context = context1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public TextView productNameView;
        public ImageView imageView;
        ConstraintLayout cartLayout;
        public ViewHolder(View v){
            super(v);
            textView = (TextView)v.findViewById(R.id.cartQuanity);
            productNameView =  (TextView)v.findViewById(R.id.cartProductName);
            imageView =  v.findViewById(R.id.cartImageView);
            cartLayout = (ConstraintLayout) v.findViewById(R.id.cartRecycler);
        }
    }

    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        view1 = LayoutInflater.from(context).inflate(R.layout.cart_recycler_view,parent,false);
        viewHolder1 = new ViewHolder(view1);
        viewHolder1.itemView.getLayoutParams().height = 250;
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        NumberFormat formatter = new DecimalFormat("##.00");
        String output = formatter.format(cartItems.get(position).getPrice());
         holder.textView.setText( "$"+output+" X " + cartItems.get(position).getQuantity() );
         holder.productNameView.setText( cartItems.get(position).getProductName() );
         holder.imageView.setImageResource(Integer.parseInt(cartItems.get(position).getImageId()));
    }

    @Override
    public int getItemCount(){
        return cartItems.size();
    }
}
