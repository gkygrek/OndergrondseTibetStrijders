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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

        Map<String, Float> kleuren = new HashMap<>();

        for (int i = 0; i < DataLists.kleurList.size(); i++) {
            if (!(kleuren.containsKey(DataLists.kleurList.get(i)))) {
                kleuren.put(DataLists.kleurList.get(i), 0f);
            }
            Float amount = kleuren.get(DataLists.kleurList.get(i));
            if (amount != null) {
                Float newAmount = amount + 1;
                kleuren.put(DataLists.kleurList.get(i), newAmount);
            }
        }

        HashMap<String, Float> maxKleuren = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            Float maxValue = 0f;
            String kleursoorten = "null";
            for (Map.Entry<String, Float> entry : kleuren.entrySet()) {
                String key = entry.getKey();
                Float value = entry.getValue();
                if (value > maxValue) {
                    maxValue = value;
                    kleursoorten = key;
                }
            }
            maxKleuren.put(kleursoorten, maxValue);
            kleuren.remove(kleursoorten);
        }


        ArrayList<Float> yData = new ArrayList();
        //Set set = new HashSet(DataLists.kleurlist);
        ArrayList<String> uniqueList = new ArrayList<String>();
        for (Map.Entry<String, Float> entry : maxKleuren.entrySet()) {
            uniqueList.add(entry.getKey());
            yData.add(entry.getValue());
        }

        Float x = 0F;

        String[] xData = new String[uniqueList.size()];
        xData = uniqueList.toArray(xData);
        final String[] legendValues = xData;

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

        PieDataSet dataSet = new PieDataSet(yVals1, "Kleuren");
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