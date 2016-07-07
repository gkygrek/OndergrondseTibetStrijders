package com.example.onno.testapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.onno.testapp.Iterator.ListIterator;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NeighbourhoodsWithMostBikeContainers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoodbarchart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createBarChart(getMaxDeelgem(countDeelgemeenten()));
    }

    private Map<String, Integer> countDeelgemeenten() {
        Map<String, Integer> deelGemeenten = new HashMap<>();
        //Count the "deelgemeenten" and put it into the deelGemeenten map
        ListIterator<String> listIterator = new ListIterator<>(DataLists.deelgemList);
        while (listIterator.hasNext()) {
            String listData = listIterator.getNext();
            if (!(deelGemeenten.containsKey(listData))) {
                deelGemeenten.put(listData, 0);
            }
            Integer amount = deelGemeenten.get(listData);
            if (amount != null) {
                Integer newAmount = amount + 1;
                deelGemeenten.put(listData, newAmount);
            }
        }
        return deelGemeenten;
    }

    private HashMap<String, Integer> getMaxDeelgem(Map<String, Integer> deelGemeenten) {
        HashMap<String, Integer> maxDeelgem = new HashMap<>();
        //Get the 5 deelgemeenten with the highest values and put it in the maxDeelgem map
        for (int i = 0; i < 5; i++) {
            Integer maxValue = 0;
            String deelgem = "null";
            for (Map.Entry<String, Integer> entry : deelGemeenten.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                if (value > maxValue) {
                    maxValue = value;
                    deelgem = key;
                }
            }
            maxDeelgem.put(deelgem, maxValue);
            deelGemeenten.remove(deelgem);
        }
        return maxDeelgem;
    }

    private ArrayList<BarEntry> createEntries(HashMap<String, Integer> maxDeelgem) {
        ArrayList<BarEntry> entries = new ArrayList<>();

        Integer index = 0;
        for (Map.Entry<String, Integer> entry : maxDeelgem.entrySet()) {
            entries.add(new BarEntry(entry.getValue(), index));
            index++;
        }
        return entries;
    }

    private ArrayList<String> addLabels(HashMap<String, Integer> maxDeelgem) {
        ArrayList<String> labels = new ArrayList<String>();

        for (Map.Entry<String, Integer> entry : maxDeelgem.entrySet())
            labels.add(entry.getKey());

        return labels;
    }

    private void createBarChart(HashMap<String, Integer> maxDeelgem) {
        BarDataSet dataset = new BarDataSet(createEntries(maxDeelgem), "Number of bike containers");
        BarChart barChart = (BarChart) findViewById(R.id.chart);

        BarData data = new BarData(addLabels(maxDeelgem), dataset);
        data.setValueTextSize(10);

        barChart.setData(data); //Set the data and list of lables into chart
        barChart.setDescription("A barchart showing the neighbourhoods with the most bike containers.");  //Set the description
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        barChart.animateY(1500); //Add a simple animation to the chart

        barChart.setVisibleXRange(2,2); //Make the visible range smaller so  the months are visible and the graph becomes scrollable
    }
}
