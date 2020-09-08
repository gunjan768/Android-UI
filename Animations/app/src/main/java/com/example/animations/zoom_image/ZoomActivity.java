package com.example.animations.zoom_image;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.animations.R;
import com.zolad.zoominimageview.ZoomInImageViewAttacher;

public class ZoomActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);

        Button button = findViewById(R.id.second_image_view);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(ZoomActivity.this, SecondZoomActivity.class));
            }
        });
    }
}