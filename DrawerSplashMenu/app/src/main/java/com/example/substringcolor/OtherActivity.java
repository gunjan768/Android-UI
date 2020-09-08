package com.example.substringcolor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class OtherActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        TextView textView1 = findViewById(R.id.text_view_1);
        TextView textView2 = findViewById(R.id.text_view_2);
        TextView textView3 = findViewById(R.id.text_view_3);

        String text = "I want THIS and THIS to be colored";
        SpannableString ss = new SpannableString(text);

        // To change we string we need builder hence used SpannableStringBuilder ( like StringBuilder in case of String ).
        SpannableStringBuilder ssb = new SpannableStringBuilder(text);

        ForegroundColorSpan fcsRed = new ForegroundColorSpan(Color.RED);
        ForegroundColorSpan fcsGreen = new ForegroundColorSpan(Color.GREEN);

        BackgroundColorSpan bcsYellow = new BackgroundColorSpan(Color.YELLOW);

        // SPAN_EXCLUSIVE_EXCLUSIVE means exclude both start and end position.
        ssb.setSpan(fcsRed, 7, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(fcsGreen, 16, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(bcsYellow, 27, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ssb.append(" and this is Emilia");
        textView1.setText(ssb);

        String text1 = "I want THIS and THIS to be colored";
        SpannableString ss1 = new SpannableString(text1);

        ClickableSpan clickableSpan1 = new ClickableSpan()
        {
            @Override
            public void onClick(@NonNull View view)
            {
                Toast.makeText(OtherActivity.this, "One", Toast.LENGTH_SHORT).show();
            }

            // updateDrawState() is used to style the text.
            @Override
            public void updateDrawState(@NonNull TextPaint ds)
            {
                super.updateDrawState(ds);

                ds.setColor(Color.BLUE);
                ds.setUnderlineText(false);
            }
        };

        ClickableSpan clickableSpan2 = new ClickableSpan()
        {
            @Override
            public void onClick(@NonNull View view)
            {
                Toast.makeText(OtherActivity.this, "Two", Toast.LENGTH_SHORT).show();
            }
        };

        ss1.setSpan(clickableSpan1, 7,11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss1.setSpan(clickableSpan2, 16,20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView2.setText(ss1);

        // To make text clickable below line is needed.
        textView2.setMovementMethod(LinkMovementMethod.getInstance());

        String text3 = "BOLD and ITALIC and BOLD-ITALIC and UNDERLINE and STRIKE-THROUGH";
        SpannableString ss3 = new SpannableString(text3);

        StyleSpan boldSpan = new StyleSpan((Typeface.BOLD));
        StyleSpan italicSpan = new StyleSpan((Typeface.ITALIC));
        StyleSpan boldItalicSpan = new StyleSpan((Typeface.BOLD_ITALIC));

        UnderlineSpan underlineSpan = new UnderlineSpan();
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();

        ss3.setSpan(boldSpan, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss3.setSpan(italicSpan, 9, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss3.setSpan(boldItalicSpan, 20, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss3.setSpan(underlineSpan, 36, 45, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss3.setSpan(strikethroughSpan, 50, 64, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView3.setText(ss3);
    }
}