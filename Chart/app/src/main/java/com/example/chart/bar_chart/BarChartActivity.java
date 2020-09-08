package com.example.chart.bar_chart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.chart.R;
import com.example.chart.line_chart.LineChartActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity
{
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        initBarChart();
    }

    private void initBarChart()
    {
        barChart = findViewById(R.id.bar_chart);

        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(true);

        ArrayList<BarEntry> barEntries1 = new ArrayList<>();

        // Remember that if you increase the entries in bar chart then please increase the maximum value of x-axis ( defined below ).
        barEntries1.add(new BarEntry(0, 40f));
        barEntries1.add(new BarEntry(1, 35f));
        barEntries1.add(new BarEntry(2, 25f));
        barEntries1.add(new BarEntry(3, 30f));
        barEntries1.add(new BarEntry(4, 60f));

        ArrayList<BarEntry> barEntries2 = new ArrayList<>();

        barEntries2.add(new BarEntry(0, 10f));
        barEntries2.add(new BarEntry(1, 29f));
        barEntries2.add(new BarEntry(2, 65f));
        barEntries2.add(new BarEntry(3, 18f));
        barEntries2.add(new BarEntry(4, 71f));


        BarDataSet barDataSet1 = new BarDataSet(barEntries1, "Data Set 1");
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet1.setValueTextSize(15);

        BarDataSet barDataSet2 = new BarDataSet(barEntries2, "Data Set 2");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet2.setValueTextSize(15);

        BarData barData = new BarData(barDataSet1, barDataSet2);

        barChart.setData(barData);
        barData.setBarWidth(0.3f);

        // fromX means first element ( chart ) of the bar charts will start from that position.
        barChart.groupBars(0, 0.1f, 0.09f);
        barChart.animateY(1000);

        String[] months = {"Jan", "Feb", "March", "July", "Sep"};
        XAxis xAxis = barChart.getXAxis();

        xAxis.setValueFormatter(new MyAxisValueFormatter(months));
        //xAxis.setGranularity(10f);
        xAxis.setCenterAxisLabels(true);

        // setAxisMinimum() will set the minimum value of x axis to 0 i.e x-axis will start from 0.
        xAxis.setAxisMinimum(0f);

        // setAxisMaximum() will set the maximum value of x axis for eg if set to 10 then it can have 10 bars ( including if group ).
        xAxis.setAxisMaximum(5f);

        // Place the X-axis both sides ( both up and down ).
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
    }

    public static class MyAxisValueFormatter extends IndexAxisValueFormatter
    {
        private String[] values;

        public MyAxisValueFormatter(String[] values)
        {
            this.values = values;
        }

        @Override
        public String getFormattedValue(float value)
        {
            if(value < values.length)
            return values[(int) value];

            return super.getFormattedValue(value);
        }
    }
}