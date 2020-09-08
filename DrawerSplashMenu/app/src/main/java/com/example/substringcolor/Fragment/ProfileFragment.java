package com.example.substringcolor.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.substringcolor.R;

public class ProfileFragment extends Fragment implements View.OnClickListener
{
    public interface SwitchActivity
    {
        void changeActivityHandler();
    }

    SwitchActivity switchActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_profile, container,false);

        Button button = rootView.findViewById(R.id.buttonSwitch);
        button.setOnClickListener((View.OnClickListener) this);

        return rootView;
    }

    @Override
    public void onClick(View view)
    {
        switchActivity = (SwitchActivity) getContext();

        assert switchActivity != null;
        switchActivity.changeActivityHandler();
    }
}