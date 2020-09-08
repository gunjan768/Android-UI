package com.example.animations.Shared_Element;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.animations.Bottom_Sheet.ModalBottomSheetActivity;
import com.example.animations.Bottom_Sheet.PersistentBottomSheetActivity;
import com.example.animations.MainActivity;
import com.example.animations.R;
import com.example.animations.zoom_image.ZoomActivity;

import java.util.Objects;

public class SharedActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);

        setTitle("Shared Element Activity");

        Fade fade = new Fade();
        View decor = getWindow().getDecorView();

        fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);

        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);

        final ImageView imageView = findViewById(R.id.image_view_gunjan);
        Button button = findViewById(R.id.button_shared);
        Button toKensBurnView = findViewById(R.id.button_to_kens_burn_view);
        Button persistentBottomSheet = findViewById(R.id.persistent_bottom_sheet);
        Button modalBottomSheet = findViewById(R.id.modal_bottom_sheet);
        Button zoomActivityBtn = findViewById(R.id.zoom_image_view);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(SharedActivity.this, SecondActivity.class);

                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        SharedActivity.this, imageView, Objects.requireNonNull(ViewCompat.getTransitionName(imageView))
                );

                startActivity(intent, activityOptionsCompat.toBundle());
            }
        });

        toKensBurnView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(SharedActivity.this, MainActivity.class));
            }
        });

        persistentBottomSheet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(SharedActivity.this, PersistentBottomSheetActivity.class));
            }
        });

        modalBottomSheet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(SharedActivity.this, ModalBottomSheetActivity.class));
            }
        });

        zoomActivityBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(SharedActivity.this, ZoomActivity.class));
            }
        });
    }
}