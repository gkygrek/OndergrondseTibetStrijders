package com.example.onno.testapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import android.widget.Toast;

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

public class MostStolenBikeColors extends AppCompatActivity {
    private RelativeLayout mainLayout;
    private PieChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechartmain);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Count all colors and put the values into the hashmap
        Map<String, Float> kleuren = new HashMap<>();
        ListIterator<String> listIterator = new ListIterator<>(DataLists.kleurList);
        while (listIterator.hasNext()) {
            String kleur = listIterator.getNext();
            if (!(kleuren.containsKey(kleur))) {
                kleuren.put(kleur, 0f);
            }
            Float amount = kleuren.get(kleur);
            if (amount != null) {
                Float newAmount = amount + 1;
                kleuren.put(kleur, newAmount);
            }
        }

        //Get the 10 colors with the highest values and put it in the maxKleuren map
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
        ArrayList<String> uniqueList = new ArrayList<>();

        for (Map.Entry<String, Float> entry : maxKleuren.entrySet()) {
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
        mChart.setDescription("Stolen bikes sorted by color");

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
                //Show a message when a value is selected showing the amount of the selected area
                Toast.makeText(MostStolenBikeColors.this,
                        legendValues[e.getXIndex()] + " = " + e.getVal() + " = total amount", Toast.LENGTH_SHORT).show();
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
        ArrayList<Entry> yVals1 = new ArrayList<>();
        ArrayList<String> xVals = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();
        ArrayList<int[]> fixedColors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int i = 0; i < pieChartValues.length; i++)
            yVals1.add(new Entry(pieChartValues[i], i));

        for (String value: legendValues) {
            xVals.add(value);
            if (getColor(value) != null)
                fixedColors.add(getColor(value));
            else {
                int [] color = {colors.get(0)};
                colors.remove(0);
                fixedColors.add(color);
            }
        }

        PieDataSet dataSet = new PieDataSet(yVals1, "Colors");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        colors.clear();
        for (int i = 0; i < fixedColors.size(); i++)
            colors.add(fixedColors.get(i)[0]);

        dataSet.setColors(colors);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(0f);
        data.setValueTextColor(Color.GRAY);

        mChart.setData(data);

        mChart.highlightValues(null);

        mChart.invalidate();
    }

    private int[] getColor(String legendValue) {
        legendValue = legendValue.toLowerCase();

        if (legendValue.contains("grijs")) {
            int[] color =  {ColorTemplate.rgb("#7e7e7e")};
            return color;
        }
        if (legendValue.contains("paars")) {
            int[] color =  {ColorTemplate.rgb("#730073")};
            return color;
        }
        if (legendValue.contains("rood")) {
            int[] color =  {ColorTemplate.rgb("#e50000")};
            return color;
        }
        if (legendValue.contains("zilver")) {
            int[] color =  {ColorTemplate.rgb("#c0c0c0")};
            return color;
        }
        if (legendValue.contains("groen")) {
            int[] color =  {ColorTemplate.rgb("#008000")};
            return color;
        }
        if (legendValue.contains("wit")) {
            int[] color =  {ColorTemplate.rgb("#ffffff")};
            return color;
        }
        if (legendValue.contains("zwart")) {
            int[] color =  {ColorTemplate.rgb("#000000")};
            return color;
        }
        if (legendValue.contains("blauw")) {
            int[] color =  {ColorTemplate.rgb("#0000ff")};
            return color;
        }
        return null;
    }
}


