package com.example.animations;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.Toast;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.flaviofaria.kenburnsview.Transition;

public class MainActivity extends AppCompatActivity
{
    KenBurnsView neroKenBurnsView;
    private boolean moving = true;
    private boolean isNeroImage = true;

    // The basic idea is to add an OnTouchListener to the view. Normally we would get all the raw touch data here (like ACTION_DOWN, ACTION_MOVE, ACTION_UP, etc.),
    // but instead of handling it ourselves, we will forward it on to a gesture detector to do the interpretation of the touch data. We are using a
    // SimpleOnGestureListener. The nice thing about this gesture detector is that we only need to override the gestures that we need. In the example here I
    // included a lot of them. You can remove the ones you don't need. (You should always return true in onDown(), though. Returning true means that we are handling
    // the event. Returning false will make the system stop giving us any more touch events.);
    private GestureDetector gestureDetector;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        neroKenBurnsView = findViewById(R.id.nero_dmc_KenBurnsView);

        AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();
        RandomTransitionGenerator generator = new RandomTransitionGenerator(5000, interpolator);
        neroKenBurnsView.setTransitionGenerator(generator);

        gestureDetector = new GestureDetector(this, new MyGestureListener());

        // Use this one or below one.
        neroKenBurnsView.setOnTouchListener(touchListener);

        // or use this one.
//        neroKenBurnsView.setOnTouchListener(new View.OnTouchListener()
//        {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent)
//            {
//                if(motionEvent.getAction() == MotionEvent.ACTION_UP)
//                {
//                    Toast.makeText(MainActivity.this, "Touched", Toast.LENGTH_SHORT).show();
//                }
//
//                return true;
//            }
//        });

        neroKenBurnsView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
//                if(moving)
//                {
//                    neroKenBurnsView.pause();
//                    moving = false;
//                }
//                else
//                {
//                    neroKenBurnsView.resume();
//                    moving = true;
//                }
            }
        });

        neroKenBurnsView.setTransitionListener(new KenBurnsView.TransitionListener()
        {
            @Override
            public void onTransitionStart(Transition transition)
            {
                // Toast.makeText(MainActivity.this, "Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTransitionEnd(Transition transition)
            {
                // Toast.makeText(MainActivity.this, "Finished", Toast.LENGTH_SHORT).show();
            }
        });
    }

    View.OnTouchListener touchListener = new View.OnTouchListener()
    {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent)
        {
            return gestureDetector.onTouchEvent(motionEvent);
        }
    };

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        public MyGestureListener() {
            super();
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e)
        {
            if(moving)
            {
                neroKenBurnsView.pause();
                moving = false;
            }
            else
            {
                neroKenBurnsView.resume();
                moving = true;
            }

            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e)
        {
            super.onLongPress(e);

            Toast.makeText(MainActivity.this, "Long pressed baby nice na !!!", Toast.LENGTH_SHORT).show();

            if(isNeroImage)
            {
                neroKenBurnsView.setImageResource(R.drawable.gunjan);

                isNeroImage = false;
            }
            else
            {
                neroKenBurnsView.setImageResource(R.drawable.nero_dmc);

                isNeroImage = true;
            }
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public void onShowPress(MotionEvent e) {
            super.onShowPress(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return super.onDown(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return super.onDoubleTapEvent(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onContextClick(MotionEvent e) {
            return super.onContextClick(e);
        }
    }
}