package com.example.onno.testapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.onno.testapp.Iterator.ArrayIterator;
import com.example.onno.testapp.Iterator.ListIterator;
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
        //Check if the data contains the filterWord and add that index to the numbers list
        List<Integer> numbers = new ArrayList<>();
        filterWord = filterWord.toLowerCase();

        ListIterator<String> listIterator = new ListIterator<>(DataLists.MKOmschrijvingList);
        while (listIterator.hasNext()) {
            if (listIterator.getNext().toLowerCase().contains(filterWord))
                numbers.add(listIterator.getIndex());
        }
        return numbers;
    }

    private Integer[] countMonths(List<Integer> numbers) {
        //Count the amounts and add it to the amounts array
        Integer[] amounts = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        ListIterator<Integer> listIterator = new ListIterator<>(numbers);
        while (listIterator.hasNext()) {
            Integer maandNr = Integer.parseInt(DataLists.gemiddeldeMaandList.get(listIterator.getNext()));
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
        LineDataSet dataset = new LineDataSet(createEntries(amounts), "Stolen bicycles per month"); //Create the dataset with the entries
        dataset.setCircleRadius(5);
        dataset.setValueTextSize(10);
        dataset.setDrawFilled(true); //Color the area below the line
        dataset.setLineWidth(2);

        LineData data = new LineData(addLabels(), dataset);
        lineChart.setData(data);
        lineChart.setDescription("Years: 2010-2013");
        lineChart.setVisibleXRange(3, 3);
    }
}