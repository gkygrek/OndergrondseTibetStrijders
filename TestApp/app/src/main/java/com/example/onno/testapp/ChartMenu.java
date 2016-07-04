package com.example.onno.testapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


import java.io.InputStream;
import java.util.List;


public class ChartMenu extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button buttonPieChart = (Button) findViewById(R.id.buttonPieChart);
        Button buttonLineChart = (Button) findViewById(R.id.buttonLineChart);
        Button buttonBarChart = (Button) findViewById(R.id.buttonBarChart);
        Button buttonNeighbourhoudChart = (Button) findViewById(R.id.buttonNeighbouurhoudChart);
        Button buttonColorChart = (Button) findViewById(R.id.buttonColorChart);
        context = this.getApplicationContext();

        buttonPieChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PieChartMain.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        buttonLineChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LineChartMain.class);
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
                Intent intent = new Intent(context, HoodBarChart.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        buttonColorChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ColorPieChart.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        if (DataLists.voorvalNummerList.isEmpty()) {
            InputStream inputStream = getResources().openRawResource(R.raw.fietsdiefstaldata);
            CSVReader csv = new CSVReader(inputStream);
            List<String[]> dataList = csv.read();

            Integer index = 0;
            for (Integer i = 0; i < dataList.size(); i++) {
                String[] data = dataList.get(i);
                for (Integer y = 0; y < data.length; y++) {
                    if (index <= 24)
                        DataLists.diefstalLists[index].add(data[y]);
                    index++;
                }
                index = 0;
            }
        }

        if (DataLists.inventarisNrList.isEmpty()) {
            InputStream inputStream = getResources().openRawResource(R.raw.fietstrommelsdata);
            CSVReader csv = new CSVReader(inputStream);
            List<String[]> dataList = csv.read();

            Integer index = 0;
            for (Integer i = 0; i < dataList.size(); i++) {
                String[] data = dataList.get(i);
                for (Integer y = 0; y < data.length; y++) {
                    if (index <= 6)
                        DataLists.trommelLists[index].add(data[y]);
                    index++;
                }
                index = 0;
            }
        }
        for (int i = 0; i < DataLists.deelgemList.size(); i++) {
            System.out.println(DataLists.deelgemList.get(i));
        }
    }
}




