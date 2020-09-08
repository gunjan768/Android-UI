package com.example.animations.zoom_image;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.animations.R;

import java.io.File;

import ozaydin.serkan.com.image_zoom_view.ImageViewZoom;
import ozaydin.serkan.com.image_zoom_view.ImageViewZoomConfig;
import ozaydin.serkan.com.image_zoom_view.SaveFileListener;

public class SecondZoomActivity extends AppCompatActivity
{
    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;
    private ImageView imageView;

    private void initZoomImageView()
    {
        ImageViewZoomConfig imageViewZoomConfig = new ImageViewZoomConfig.Builder().saveProperty(true).saveMethod(
                ImageViewZoomConfig.ImageViewZoomConfigSaveMethod.always
        ).build();

        ImageViewZoom imageViewZoom = findViewById(R.id.ozaydin_image_view);

        imageViewZoom.setConfig(imageViewZoomConfig);

        imageViewZoom.saveImage(SecondZoomActivity.this, "ImageViewZoom","Test", Bitmap.CompressFormat.JPEG,
                1, imageViewZoomConfig, new SaveFileListener()
                {
                    @Override
                    public void onSuccess(File file)
                    {
                        Toast.makeText(SecondZoomActivity.this, "Successfully saved the image", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(Exception exception)
                    {
                        Toast.makeText(SecondZoomActivity.this, "Error in saving the image", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_zoom);

        initZoomImageView();

        imageView=findViewById(R.id.simple_image_view);
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
    }

    // This redirects all touch events in the activity to the gesture detector.
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        scaleGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener
    {
        public ScaleListener() {
            super();
        }

        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector)
        {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            imageView.setScaleX(mScaleFactor);
            imageView.setScaleY(mScaleFactor);

            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector)
        {
            return super.onScaleBegin(detector);
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector)
        {
            super.onScaleEnd(detector);

            imageView.setScaleX(1);
            imageView.setScaleY(1);
        }
    }
}