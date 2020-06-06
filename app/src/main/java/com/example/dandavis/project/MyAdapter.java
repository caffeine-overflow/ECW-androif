package com.example.dandavis.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    String[] images;
    Context context;
    View view1;
    ViewHolder viewHolder1;
    TextView textView;
    ImageView imageView;
    ArrayList<Category> categoryList;
    int [] categoryImages_;

    public MyAdapter(Context context1,  ArrayList<Category> SubjectValues1, int [] categoryImages){
        categoryList = new ArrayList<Category>(  );
        categoryList = SubjectValues1;
        categoryImages_ = categoryImages;
        context = context1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ImageView imageView;
        public ViewHolder(View v){
            super(v);
            textView = (TextView)v.findViewById(R.id.textViewRecyclerItem);
            imageView = (ImageView) v.findViewById(R.id.imageView);
        }
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        view1 = LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false);
        viewHolder1 = new ViewHolder(view1);
        viewHolder1.itemView.getLayoutParams().height = 450;
        //viewHolder1.itemView.getLayoutParams().width = 800;
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        holder.textView.setText(categoryList.get( position ).getName_());
        holder.imageView.setImageResource(categoryImages_[position]);
    }

    @Override
    public int getItemCount(){
        return categoryList.size();
    }
}
