package com.example.dandavis.project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class CategoyItemsAdapter extends RecyclerView.Adapter<CategoyItemsAdapter.ViewHolder> {

    private int[] mImages;
    private LayoutInflater mInflater;
    private ArrayList<Items> itemList_;
    CategoyItemsAdapter(Context context, ArrayList<Items> itemList , int [] images) {
        this.mInflater = LayoutInflater.from(context);
        this.itemList_ = itemList;
        this.mImages = images;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycleview_category_items, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i(itemList_.get(position).getName_(), "OHM");
        holder.myTextView.setText(itemList_.get(position).getName_());
        NumberFormat formatter = new DecimalFormat("##.00");
        String output = formatter.format(itemList_.get(position).getPrice_());
        holder.itemPrice.setText("$"+output);
        holder.myImageView.setImageResource(mImages[position]);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return itemList_.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;
        TextView itemPrice;
        ImageView myImageView;
        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.info_text);
            myImageView = itemView.findViewById(R.id.imageView);
            itemPrice = itemView.findViewById(R.id.itemPrice);
        }
    }

}