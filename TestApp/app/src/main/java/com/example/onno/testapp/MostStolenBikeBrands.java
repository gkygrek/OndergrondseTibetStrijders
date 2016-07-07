package com.example.onno.testapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.onno.testapp.Iterator.ArrayIterator;
import com.example.onno.testapp.Iterator.ListIterator;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MostStolenBikeBrands extends AppCompatActivity {
    private RelativeLayout mainLayout;
    private PieChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechartmain);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Count all brands and put the values into the hashmap
        Map<String, Float> merken = new HashMap<>();
        ListIterator<String> listIterator = new ListIterator<>(DataLists.merkList);
        while (listIterator.hasNext()) {
            String merk = listIterator.getNext();
            if (!(merken.containsKey(merk))) {
                merken.put(merk, 0f);
            }
            Float amount = merken.get(merk);
            if (amount != null) {
                Float newAmount = amount + 1;
                merken.put(merk, newAmount);
            }
        }

        HashMap<String, Float> maxMerken = new HashMap<>();
        //Get the brands with the (10) highest values and put the into the maxMerken map
        for (int i = 0; i < 10; i++) {
            Float maxValue = 0f;
            String merksoorten = "null";
            for (Map.Entry<String, Float> entry : merken.entrySet()) {
                String key = entry.getKey();
                Float value = entry.getValue();
                if (value > maxValue) {
                    maxValue = value;
                    merksoorten = key;
                }
            }
            maxMerken.put(merksoorten, maxValue);
            merken.remove(merksoorten);
        }


        ArrayList<Float> yData = new ArrayList();

        ArrayList<String> uniqueList = new ArrayList<String>();
        for (Map.Entry<String, Float> entry : maxMerken.entrySet()) {
            uniqueList.add(entry.getKey());
            yData.add(entry.getValue());
        }

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

        //Set a chart value selected listener
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                //Display msg when value selected
                if (e == null)
                    return;
                //Show a message when a value is selected showing the amount of the selected area
                Toast.makeText(MostStolenBikeBrands.this,
                        legendValues[e.getXIndex()] + " = " + e.getVal() + "= total amount", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected() { }
        });

        addData(pieChartValues, legendValues);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);
    }

    private void addData(Float[] pieChartValues,String[] legendValues){
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        ArrayIterator<Float> arrayIterator = new ArrayIterator(pieChartValues);
        while (arrayIterator.hasNext())
            yVals1.add(new Entry(arrayIterator.getNext(), arrayIterator.getIndex()));

        ArrayList<String> xVals = new ArrayList<String>();

        ArrayIterator<String> arrayIterator1 = new ArrayIterator<>(legendValues);
        while (arrayIterator1.hasNext())
            xVals.add(arrayIterator1.getNext());

        PieDataSet dataSet = new PieDataSet(yVals1, "Brands");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        ArrayList<Integer> colors = new ArrayList<Integer>();
        //Add different colors to the piechart
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
