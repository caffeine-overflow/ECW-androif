package com.example.dandavis.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
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
        ConstraintLayout categoryItemLayout;
        public ViewHolder(View v){
            super(v);
            textView = (TextView)v.findViewById(R.id.textViewRecyclerItem);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            categoryItemLayout = (ConstraintLayout) v.findViewById(R.id.categoryItems);
        }
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        view1 = LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false);
        viewHolder1 = new ViewHolder(view1);
        viewHolder1.itemView.getLayoutParams().height = 450;
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        holder.textView.setText(categoryList.get( position ).getName_());
        holder.imageView.setImageResource(categoryImages_[position]);

        holder.categoryItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CategoryItems.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("categoryId", categoryList.get( position ).getId_());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount(){
        return categoryList.size();
    }
}
