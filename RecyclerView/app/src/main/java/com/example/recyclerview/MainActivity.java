package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener
{
    SwipeRefreshLayout swipeRefreshLayout;

    RecyclerView recyclerView;
    MyAdapter myAdapter;

    ArrayList<String> s1, s2;
    ArrayList<Integer> images;

//    int images[] = { R.drawable.disha, R.drawable.dora, R.drawable.fre, R.drawable.gkk, R.drawable.goku, R.drawable.sky,
//            R.drawable.message, R.drawable.job, R.drawable.asus, R.drawable.goku1 };

//    int images[] = { R.drawable.message, R.drawable.message, R.drawable.message, R.drawable.message, R.drawable.message, R.drawable.message,
//            R.drawable.message, R.drawable.message, R.drawable.message, R.drawable.message };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

//        s1.add(getResources().getStringArray(R.array.programming_language));
//        s2 = getResources().getStringArray(R.array.description);

        s1 = new ArrayList<>();
        s2 = new ArrayList<>();
        images = new ArrayList<>();

        for(int i=0; i<10; i++)
        {
            s1.add("Be brave");
            s2.add("You have to face a lot of problems in life");
            images.add(R.drawable.message);
        }

        myAdapter = new MyAdapter(this, s1, s2, images);

        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // For horizontal scrolling.
        // recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(this);
    }


    @Override
    public void onRefresh()
    {
        s1.clear();
        s2.clear();
        images.clear();

        for(int i=0; i<10; i++)
        {
            s1.add("Gunjan");
            s2.add("Hey listen Mallika I am in love with you");
            images.add(R.drawable.message);
        }

        myAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }
}