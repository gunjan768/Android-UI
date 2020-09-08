package com.example.infinite_scroll_recycler_view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infinite_scroll_recycler_view.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{
    ArrayList<String> data1, data2;
    ArrayList<Integer> images;
    Context context;

    public MyAdapter(Context ct, ArrayList<String> s1, ArrayList<String> s2, ArrayList<Integer> images)
    {
        context = ct;
        data1 = s1;
        data2 = s2;
        this.images = images;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =  inflater.inflate(R.layout.my_row, parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position)
    {
        holder.title.setText(data1.get(position));
        holder.description.setText(data2.get(position));
        holder.myImage.setImageResource(images.get(position));

//        holder.mainLayout.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Intent intent = new Intent(context, SecondActivity.class);
//
//                intent.putExtra("data1", data1[position]);
//                intent.putExtra("data2", data2[position]);
//                intent.putExtra("myImage", images[position]);
//
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount()
    {
        return images.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView title, description;
        ImageView myImage;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            title = itemView.findViewById(R.id.language_txt);
            description = itemView.findViewById(R.id.description_txt);
            myImage = itemView.findViewById(R.id.myImageView);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}