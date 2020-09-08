package com.example.chart.pie_chart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chart.R;
import com.example.chart.bar_chart.BarChartActivity;
import com.example.chart.line_chart.LineChartActivity;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity
{
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        initPieChar();

        Button lineChartBtn = findViewById(R.id.button_line_chart);
        Button barChartBtn = findViewById(R.id.button_bar_chart);

        lineChartBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(PieChartActivity.this, LineChartActivity.class));
            }
        });

        barChartBtn.setOnClickListener(view ->
        {
            startActivity(new Intent(PieChartActivity.this, BarChartActivity.class));
        });
    }

    private void initPieChar()
    {
        pieChart = findViewById(R.id.pie_chart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(true);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.animateY(1000, Easing.EaseInCirc);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(new PieEntry(54f, "India"));
        yValues.add(new PieEntry(34f, "SouthAfrica"));
        yValues.add(new PieEntry(44f, "UK"));
        yValues.add(new PieEntry(22f, "Japan"));
        yValues.add(new PieEntry(11f, "Russia"));
        yValues.add(new PieEntry(35f, "America"));

        Description description = new Description();
        description.setText("This is the all time favourite");
        description.setTextSize(15);
        description.setTextColor(Color.RED);
        pieChart.setDescription(description);

        PieDataSet dataSet = new PieDataSet(yValues, "Countries");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // ColorTemplate.JOYFUL_COLORS will distribute the colors to the entries automatically.
        // dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        // Here we are defining the colors of each entry by ourSelf.
        dataSet.setColors(Color.RED,Color.MAGENTA,Color.CYAN,Color.BLUE,Color.GREEN,Color.GRAY);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);
    }
}