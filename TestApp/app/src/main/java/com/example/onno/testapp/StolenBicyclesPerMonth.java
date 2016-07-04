package com.example.onno.testapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class StolenBicyclesPerMonth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linechartmain);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createLineChart(countMonths(getIndexList("fiets")));
    }

    private List<Integer> getIndexList(String filterWord) {
        List<Integer> numbers = new ArrayList<>();
        filterWord = filterWord.toLowerCase();
        for (int i = 0; i < DataLists.MKOmschrijvingList.size() ; i++) {
            if (DataLists.MKOmschrijvingList.get(i).toLowerCase().contains(filterWord))
                numbers.add(i);
        }
        return numbers;
    }

    private Integer[] countMonths(List<Integer> numbers) {
        Integer[] amounts = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < numbers.size() ; i++) {
            Integer maandNr = Integer.parseInt(DataLists.gemiddeldeMaandList.get(numbers.get(i)));
            amounts[maandNr] = amounts[maandNr] + 1;
        }
        return amounts;
    }

    private ArrayList<Entry> createEntries(Integer[] amounts) {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 1; i < amounts.length ; i++)
            entries.add(new Entry(amounts[i], i - 1));
        return entries;
    }

    private String[] addLabels() {
        String[] labels = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return labels;
    }

    private void createLineChart(Integer [] amounts) {
        LineChart lineChart = (LineChart) findViewById(R.id.chart);
        LineDataSet dataset = new LineDataSet(createEntries(amounts), "Stolen bicycles per month");
        dataset.setCircleRadius(5);
        dataset.setValueTextSize(10);
        dataset.setDrawFilled(true);
        dataset.setLineWidth(2);

        LineData data = new LineData(addLabels(), dataset);
        lineChart.setData(data);
        lineChart.setDescription("Years 2011-2013");
        lineChart.setVisibleXRange(3, 3);
    }
}