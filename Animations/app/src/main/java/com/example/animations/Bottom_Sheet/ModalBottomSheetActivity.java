package com.example.animations.Bottom_Sheet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.animations.R;

public class ModalBottomSheetActivity extends AppCompatActivity implements ExampleBottomSheetDialog.BottomSheetListener
{
    private TextView textView;

    @Override
    public void onButtonClicked(String text)
    {
        textView.setText(text);
        textView.cancelLongPress();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modal_bottom_sheet);

        Button buttonOpenBottomSheet = findViewById(R.id.button_open_bottom_sheet);
        textView = findViewById(R.id.text_view_button_clicked);

        buttonOpenBottomSheet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ExampleBottomSheetDialog exampleBottomSheetDialog = new ExampleBottomSheetDialog();

                exampleBottomSheetDialog.show(getSupportFragmentManager(), "example_bottom_shee t");
            }
        });
    }
}