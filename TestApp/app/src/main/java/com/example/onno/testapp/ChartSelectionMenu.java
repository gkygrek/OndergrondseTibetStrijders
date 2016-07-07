package com.example.onno.testapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


import com.example.onno.testapp.Iterator.ArrayIterator;
import com.example.onno.testapp.Iterator.ListIterator;

import java.io.InputStream;
import java.util.List;


public class ChartSelectionMenu extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chartmenu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this.getApplicationContext();
        createButtons();
        fillDataLists();
    }

    private void createButtons() {
        //Create buttons
        Button buttonColorChart = (Button) findViewById(R.id.colorPieChart);
        Button buttonPiechart = (Button) findViewById(R.id.button);
        Button buttonLineChart = (Button) findViewById(R.id.buttonLineChart);
        Button buttonBarChart = (Button) findViewById(R.id.buttonBarChart);
        Button buttonNeighbourhoudChart = (Button) findViewById(R.id.buttonNeighbouurhoudChart);

        //Add actions to the buttons
        buttonPiechart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MostStolenBikeBrands.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        buttonColorChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MostStolenBikeColors.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        buttonLineChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StolenBicyclesPerMonth.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        buttonBarChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DeelgemMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        buttonNeighbourhoudChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NeighbourhoodsWithMostBikeContainers.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    private void fillDataLists() {
        if (DataLists.voorvalNummerList.isEmpty()) {
            InputStream inputStream = getResources().openRawResource(R.raw.fietsdiefstaldata);
            CSVReader csv = new CSVReader(inputStream);
            List<String[]> dataList = csv.read();

            ListIterator<String[]> listIterator = new ListIterator<>(dataList);
            //Iterate the dataList and add the data to the DataLists lists.
            while (listIterator.hasNext()) {
                String[] data = listIterator.getNext();
                ArrayIterator arrayIterator = new ArrayIterator(data);
                while (arrayIterator.hasNext()) {
                    arrayIterator.getNext();
                    if (arrayIterator.getIndex() <= 24) {
                        DataLists.diefstalLists[arrayIterator.getIndex()].add(data[arrayIterator.getIndex()]);
                    }
                }
                arrayIterator.reset();
            }
        }

        if (DataLists.inventarisNrList.isEmpty()) {
            InputStream inputStream = getResources().openRawResource(R.raw.fietstrommelsdata);
            CSVReader csv = new CSVReader(inputStream);
            List<String[]> dataList = csv.read();

            ListIterator<String[]> listIterator = new ListIterator<>(dataList);

            while (listIterator.hasNext()) {
                String[] data = listIterator.getNext();
                ArrayIterator arrayIterator = new ArrayIterator(data);
                while (arrayIterator.hasNext()) {
                    arrayIterator.getNext();
                    if (arrayIterator.getIndex() <= 24) {
                        DataLists.trommelLists[arrayIterator.getIndex()].add(data[arrayIterator.getIndex()]);
                    }
                }
                arrayIterator.reset();
            }
        }
    }
}




