package com.example.substringcolor.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.substringcolor.CollapsedTabActivity;
import com.example.substringcolor.R;

public class MessageFragment extends Fragment implements View.OnClickListener
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_message, container,         false);

        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener((View.OnClickListener) this);

//        button.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        return rootView;
    }

    @Override
    public void onClick(View view)
    {
        // Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();

        // Use any ways you want : getContext() or view.getContext().
        Intent intent = new Intent(getContext(), CollapsedTabActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }
}