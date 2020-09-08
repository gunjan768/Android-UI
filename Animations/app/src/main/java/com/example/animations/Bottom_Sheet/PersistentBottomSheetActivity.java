package com.example.animations.Bottom_Sheet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.animations.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class PersistentBottomSheetActivity extends AppCompatActivity
{
    private BottomSheetBehavior bottomSheetBehavior;
    private TextView textViewState;
    private static final String TAG = "PersistentBottomSheetActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persistent_bottom_sheet);

        final View bottomSheet = findViewById(R.id.persistent_bottom_sheet);

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        textViewState = findViewById(R.id.text_view_state);
        Button buttonExpand = findViewById(R.id.button_expand);
        Button buttonCollapse = findViewById(R.id.button_collapse);

        buttonExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        buttonCollapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        if(!bottomSheetBehavior.isHideable())
        {
            Log.d(TAG, "onCreate: Not hidden");
            Log.d(TAG, "getHalfExpandedRatio: " + bottomSheetBehavior.getHalfExpandedRatio());
        }

        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback()
        {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState)
            {
                switch(newState)
                {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        textViewState.setText("Collapsed");

                        break;

                    case BottomSheetBehavior.STATE_DRAGGING:
                        textViewState.setText("Dragging");

                        break;

                    case BottomSheetBehavior.STATE_EXPANDED:
                        textViewState.setText("Expanded");

                        break;

                    case BottomSheetBehavior.STATE_HIDDEN:
                        textViewState.setText("Hidden");

                        break;

                    case BottomSheetBehavior.STATE_SETTLING:
                        textViewState.setText("Settling");

                        break;

                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
                        textViewState.setText("Half Hidden");

                        break;
                }

                Log.d(TAG, "Peek Height " + bottomSheetBehavior.getPeekHeight());
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset)
            {
                // textViewState.setText("Sliding");
            }
        });
    }
}