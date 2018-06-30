package com.developer.dinhduy.sqlitetutorial;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.Foodhoder> {
    List<Food> listfood;
    Context context;

    public FoodAdapter(List<Food> listfood, Context context) {
        this.listfood = listfood;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodAdapter.Foodhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_recyclerview,parent,false);

        return new Foodhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.Foodhoder holder, int position) {
        final Food food=listfood.get(position);
        byte [] picture=food.getPicture();
        Bitmap bitmap= BitmapFactory.decodeByteArray(picture,0,picture.length);

        holder.NAME_FOOD.setText(food.getName());
        holder.IMAGE_FOOD.setImageBitmap(bitmap);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.sqlite.DELETE(food.getID());
                MainActivity.Getdata();
                Toast.makeText(context, "DELETE "+food.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listfood.size();
    }

    public void find(List<Food> foodList) {
        listfood=new ArrayList<>();
        listfood.addAll(foodList);
        notifyDataSetChanged();
    }


    public class Foodhoder extends RecyclerView.ViewHolder {
        TextView NAME_FOOD;
        ImageView IMAGE_FOOD;
        public Foodhoder(View itemView) {
            super(itemView);
            NAME_FOOD=(TextView) itemView.findViewById(R.id.namefood);
            IMAGE_FOOD=(ImageView) itemView.findViewById(R.id.imagefood);
        }
    }
}
