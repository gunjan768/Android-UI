package com.example.substringcolor.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.substringcolor.R;
import com.example.substringcolor.TabActivity;

import java.util.Objects;

public class ChatFragment extends Fragment implements View.OnClickListener
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_chat, container,false);

        Button button = rootView.findViewById(R.id.flipActivityButton);
        button.setOnClickListener(this);

        Button shareButton = rootView.findViewById(R.id.share_button);
        shareButton.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.share_button:

                showShareButton();
                break;

            case R.id.flipActivityButton:

                Intent intent = new Intent(getContext(), TabActivity.class);
                startActivity(intent);

                break;
        }
    }

    private void showShareButton()
    {
        Intent intent = new Intent(Intent.ACTION_SEND);

        final String appPackageName = Objects.requireNonNull(getActivity()).getPackageName();
        String appLink = "";

        try
        {
            appLink = "https://play.google.com/store/apps/details?id=" + appPackageName;
        }
        catch(Exception e)
        {
            appLink = "https://play.google.com/store/apps/details?id=" + appPackageName;
        }

        intent.setType("text/Link");

        String shareBody = "Hey! Please download my app from the link given below\n" + appLink, shareSub = "APP NAME";

        intent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
        intent.putExtra(Intent.EXTRA_TEXT, shareBody);

        startActivity(Intent.createChooser(intent, "Share this app using..."));
    }
}