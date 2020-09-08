package com.example.staggeredimages;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.MyViewHolder>
{
    Context context;
    private static final String TAG = "StaggeredAdapter";
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    public StaggeredAdapter(Context context, ArrayList<String> mNames, ArrayList<String> mImageUrls)
    {
        this.context = context;
        this.mNames = mNames;
        this.mImageUrls = mImageUrls;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =  inflater.inflate(R.layout.layout_grid_item, parent,false);

        return new MyViewHolder(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position)
    {
        Log.d(TAG, "onBindViewHolder: called");

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_launcher_background);

        Glide.with(context)
                .load(mImageUrls.get(position))
                .apply(requestOptions)
                .into(holder.image);

        holder.name.setText(mNames.get(position));

        holder.image.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Log.d(TAG, "onClick: clicked on " + mNames.get(position));
                Toast.makeText(context, mNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        holder.mainLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mImageUrls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        ImageView image;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            name = itemView.findViewById(R.id.name_text_view);
            image = itemView.findViewById(R.id.image_view);
            mainLayout = itemView.findViewById(R.id.constraint_main_layout);
        }
    }
}
