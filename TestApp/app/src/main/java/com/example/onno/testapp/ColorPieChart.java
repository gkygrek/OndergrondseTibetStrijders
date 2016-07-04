package com.example.onno.testapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ColorPieChart extends AppCompatActivity {

    private RelativeLayout mainLayout;
    private PieChart mChart;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_pie_chart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<Float> yData = new ArrayList();
        Set set = new HashSet(DataLists.kleurList);
        ArrayList<String> uniqueList = new ArrayList(set);
//        for (int i = 0; i < uniqueList.size(); i++)
//        {
//            uniqueList.set(i, uniqueList.get(i).replaceAll(" ", ""));
//
//        }
//        for (int i = 0; i < uniqueList.size(); i++)
//        {
//            System.out.println(uniqueList.get(i));
//            if (uniqueList.get(i).length() < 3)
//            {
//                System.out.println(uniqueList.get(i));
//                uniqueList.remove(uniqueList.get(i));
//            }
//
//        }
        Float x = 0F;

        String[] xData = new String[uniqueList.size()];
        xData = uniqueList.toArray(xData);
        final String[] legendValues = xData;

        for (int g = 0; g < uniqueList.size(); g++)
        {
            for (int i = 1; i < DataLists.kleurList.size(); i++)
            {
                if (uniqueList.get(g).equals(DataLists.kleurList.get(i)))
                {
                    x = x + 1;
                }
            }
            yData.add(x);
            x = 0F;
        }

        Float[] pieChartValues = new Float[yData.size()];
        pieChartValues = yData.toArray(pieChartValues);

        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
        mChart = (PieChart) findViewById(R.id.chart);

        mChart.setUsePercentValues(true);
        mChart.setDescription("Stolen bikes sorted by brand");

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleRadius(7);
        mChart.setTransparentCircleRadius(10);

        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);

        // set a chart value selected listener
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                // display msg when value selected
                if (e == null)
                    return;

                Toast.makeText(ColorPieChart.this,
                        //legendValues[e.getXIndex()] + " = " + e.getVal() + "%", Toast.LENGTH_SHORT).show();
                        legendValues[e.getXIndex()] + " = " + e.getVal() + "= total amount", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        addData(pieChartValues, legendValues);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);
    }

    private void addData(Float[] pieChartValues,String[] legendValues){
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < pieChartValues.length; i++)
            yVals1.add(new Entry(pieChartValues[i], i));

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < legendValues.length; i++)
            xVals.add(legendValues[i]);

        PieDataSet dataSet = new PieDataSet(yVals1, "Market Share");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(0f);
        data.setValueTextColor(Color.GRAY);

        mChart.setData(data);

        mChart.highlightValues(null);

        mChart.invalidate();
    }

}