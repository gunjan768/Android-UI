package com.example.chart.line_chart;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.example.chart.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class LineChartActivity extends AppCompatActivity implements OnChartGestureListener, OnChartValueSelectedListener
{
    private static final String TAG = "LineChartActivity";
    private LineChart lineChart;

    private void initPieChart()
    {
        lineChart = findViewById(R.id.line_chart);

        lineChart.setOnChartGestureListener(this);
        lineChart.setOnChartValueSelectedListener(this);

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);

        LimitLine upperLimit = new LimitLine(65f, "Danger");
        upperLimit.setLineWidth(4f);

        // enableDashedLine() will alter the upper limit line size and shape.
        upperLimit.enableDashedLine(10f, 10f, 10f);

        upperLimit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upperLimit.setTextSize(20f);

        LimitLine lowerLimit = new LimitLine(35f, "Threshold");
        lowerLimit.setLineWidth(4f);

        // enableDashedLine() will alter the lower limit line size and shape.
        lowerLimit.enableDashedLine(25f, 15f, 10f);

        lowerLimit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        lowerLimit.setTextSize(20f);

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(upperLimit);
        leftAxis.addLimitLine(lowerLimit);
        leftAxis.setAxisMaximum(100f);
        leftAxis.setAxisMinimum(25f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawLimitLinesBehindData(true);

        lineChart.getAxisRight().setEnabled(true);

        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(0, 60f));
        yValues.add(new Entry(1, 50f));
        yValues.add(new Entry(2, 70f));
        yValues.add(new Entry(3, 30f));
        yValues.add(new Entry(4, 50f));
        yValues.add(new Entry(5, 65f));
        yValues.add(new Entry(6, 30f));

        LineDataSet set1 = new LineDataSet(yValues, "Data set 1");

        set1.setFillAlpha(110);
        set1.setColor(Color.RED);
        set1.setLineWidth(2f);
        set1.setValueTextSize(15f);
        set1.setValueTextColor(Color.GREEN);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);
        lineChart.setData(data);

        String[] months = {"Jan", "Feb", "March", "May", "July", "Aug", "Sep"};

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new MyAxisValueFormatter(months));
        xAxis.setGranularity(0.2f);

        // Place the X-axis both sides ( both up and down ).
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);

        initPieChart();
    }

    public static class MyAxisValueFormatter extends IndexAxisValueFormatter
    {
        private String[] values;

        public MyAxisValueFormatter(String[] values)
        {
            super();

            this.values = values;
        }

        @Override
        public String getFormattedValue(float value)
        {
            return values[(int)value];
        }
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture)
    {
        Log.d(TAG, "onChartGestureStart: X " + me.getX() + " Y : " + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.d(TAG, "onChartGestureEnd: ");
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.d(TAG, "onChartLongPressed: ");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.d(TAG, "onChartDoubleTapped: ");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.d(TAG, "onChartSingleTapped: ");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.d(TAG, "onChartFling: ");
    }

    // onChartScale() will be triggered when you scale the line chart ( scaling means either zoom in or zoom out ).
    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.d(TAG, "onChartScale: X" + scaleX + " Y : " + scaleY);
    }

    // onChartTranslate() will be triggered when you move the line chart.
    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.d(TAG, "onChartTranslate: dX" + dX + " dY" + dY);
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.d(TAG, "onValueSelected: ");
    }

    @Override
    public void onNothingSelected() {
        Log.d(TAG, "onNothingSelected: ");
    }
}