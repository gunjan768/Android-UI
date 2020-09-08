package com.example.animations.Bottom_Sheet;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.animations.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ExampleBottomSheetDialog extends BottomSheetDialogFragment
{
    private BottomSheetListener bottomSheetListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        Button button1 = view.findViewById(R.id.button_1);
        Button button2 = view.findViewById(R.id.button_2);

        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                bottomSheetListener.onButtonClicked("Button 1 clicked");

                dismiss();
            }
        });

        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                bottomSheetListener.onButtonClicked("Button 2 clicked");

                dismiss();
            }
        });

        return view;
    }

    public interface BottomSheetListener
    {
        void onButtonClicked(String text);
    }

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);

        try
        {
            bottomSheetListener = (BottomSheetListener) context;
        }
        catch(ClassCastException e)
        {
            throw new ClassCastException(context.toString() + " must implement Bottom Sheet Listener");
        }
    }
}
