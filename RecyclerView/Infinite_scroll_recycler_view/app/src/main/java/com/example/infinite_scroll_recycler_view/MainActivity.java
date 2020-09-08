package com.example.infinite_scroll_recycler_view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    LinearLayoutManager manager;

    boolean isScrolling = false;
    int currentItems, totalItems, scrolledItems;
    ProgressBar progressBar;
    MyAdapter myAdapter;

    ArrayList<Integer> images;
    ArrayList<String> s1, s2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        s1 = new ArrayList<>();
        s2 = new ArrayList<>();
        images = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        manager = new LinearLayoutManager(this);

        myAdapter = new MyAdapter(this, s1, s2, images);

        addMoreItemsToList();

        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(manager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);

                // It will tell you that whether you scroll state has changed or not means whether it is moving ( user is scrolling ) or stooped.
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);

                currentItems = manager.getChildCount();
                totalItems = manager.getItemCount();
                scrolledItems = manager.findFirstVisibleItemPosition();

                // If visibleItems + scrolledItems == TotalItems then we need to fetch more items from the server.
                if(isScrolling && (currentItems + scrolledItems == totalItems))
                {
                    isScrolling = false;

                    fetchData();
                }
            }
        });
    }

    private void addMoreItemsToList()
    {
        int upto = 10;

        for(int i=0; i<=upto; i++)
        {
            images.add(R.drawable.message);
            s1.add("GUNJAN");
            s2.add("This is the love for me by my Emilia");

            myAdapter.notifyDataSetChanged();
        }
    }

    private void fetchData()
    {
        progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                addMoreItemsToList();

                progressBar.setVisibility(View.GONE);
            }
        }, 2000);
    }
}