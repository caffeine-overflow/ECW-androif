package com.example.dandavis.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoyItemsAdapter extends RecyclerView.Adapter<CategoyItemsAdapter.ViewHolder> {

    private String[] mData;
    private int[] mImages;
    private LayoutInflater mInflater;

    CategoyItemsAdapter(Context context, String[] data, int [] images) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
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
        holder.myTextView.setText(mData[position]);
        holder.myImageView.setImageResource(mImages[position]);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.length;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;
        ImageView myImageView;
        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.info_text);
            myImageView = itemView.findViewById(R.id.imageView);
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData[id];
    }
}