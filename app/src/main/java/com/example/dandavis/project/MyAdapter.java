package com.example.dandavis.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    String[] SubjectValues;
    Context context;
    View view1;
    ViewHolder viewHolder1;
    TextView textView;
    ImageView imageView;

    public MyAdapter(Context context1, String[] SubjectValues1){
        SubjectValues = SubjectValues1;
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
        // set size of items here
        viewHolder1.itemView.getLayoutParams().height = 500;
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        holder.textView.setText(SubjectValues[position]);
        holder.imageView.setImageResource(R.drawable.ic_woman1);
    }

    @Override
    public int getItemCount(){
        return SubjectValues.length;
    }
}
