package com.example.onno.testapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class BarChartMain extends AppCompatActivity {
    public static String currentDeelgem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchartmain);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createBarChart(createEntries(countMonthsGroup1(getIndexList("fiets"))), createEntries(countMonthsGroup2(getIndexList("fiets"))));
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

    private Integer[] countMonthsGroup1(List<Integer> numbers) {
        Integer[] amounts = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < numbers.size() ; i++) {
            Integer maandNr = Integer.parseInt(DataLists.gemiddeldeMaandList.get(numbers.get(i)));
            if (getDeelGem(DataLists.werkgebiedList.get(i)).toLowerCase().contains(currentDeelgem.toLowerCase()))
                amounts[maandNr] = amounts[maandNr] + 1;
        }
        return amounts;
    }

    private Integer[] countMonthsGroup2(List<Integer> numbers) {
        Integer[] amounts2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < DataLists.datumList.size() ; i++) {
            if (DataLists.deelgemList.get(i).toLowerCase().contains(currentDeelgem.toLowerCase())) {
                String datum = DataLists.datumList.get(i);
                datum = datum.substring(datum.indexOf("-") + 1);
                datum = datum.substring(0, datum.indexOf("-"));

                Integer maandNr = Integer.parseInt(datum);
                amounts2[maandNr] = amounts2[maandNr] + 1;
            }
        }
        return amounts2;
    }

    private ArrayList<BarEntry> createEntries(Integer[] amounts) {
        ArrayList<BarEntry> group = new ArrayList<>();
        for (int i = 1; i < amounts.length ; i++)
            group.add(new BarEntry(amounts[i], i - 1));
        return group;
    }

    private void createBarChart(ArrayList<BarEntry> group1,  ArrayList<BarEntry> group2) {
        BarDataSet barDataSet1 = new BarDataSet(group1, "Hoeveelheid fietsiefstal");
        BarDataSet barDataSet2 = new BarDataSet(group2, "Hoeveelheid fietstrommels");
        int[] redColor =  {ColorTemplate.rgb("#ff0000")};
        barDataSet2.setColors(redColor);

        com.github.mikephil.charting.charts.BarChart barChart = (com.github.mikephil.charting.charts.BarChart) findViewById(R.id.chart);

        String[] labels = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        BarData data = new BarData(labels, barDataSet1);
        data.addDataSet(barDataSet2);
        barChart.setData(data);
        barChart.setVisibleXRange(0, 10);
    }

    private String getDeelGem(String wijk) {
        wijk = wijk.toLowerCase();
        if (wijk.contains("stadsdriehoek") || wijk.contains("cool") || wijk.contains("cs-kwartier") || wijk.contains("dijkzigt") || wijk.contains("nieuwe werk") || wijk.contains("scheepvaartkwartier"))
            return "centrum";
        if (wijk.contains("delfshaven") || wijk.contains("bospolder") || wijk.contains("tussendijken") || wijk.contains("spangen") || wijk.contains("nieuwe westen") || wijk.contains("middelland") || wijk.contains("oud-mathenesse") || wijk.contains("witte dorp") || wijk.contains("schiemond"))
            return "delfshaven";
        if (wijk.contains("overschie") || wijk.contains("kleinpolder") || wijk.contains("noord-kethel") || wijk.contains("schieveen") || wijk.contains("zestienhoven") || wijk.contains("landzicht"))
            return "overschie";
        if (wijk.contains("agniesebuurt") || wijk.contains("provenierswijk") || wijk.contains("bergpolder") || wijk.contains("blijdorp") || wijk.contains("liskwartier") || wijk.contains("oude noorden") || wijk.contains("blijdorpse polder"))
            return "noord";
        if (wijk.contains("schiebroek") || wijk.contains("hillegersberg-noord") || wijk.contains("110-morgen") || wijk.contains("hillegersberg-zuid ") || wijk.contains("terbregge") || wijk.contains("molenlaankwartier") || wijk.contains("kleiwegkwartier"))
            return "hillegersberg";
        if (wijk.contains("rubroek") || wijk.contains("crooswijk") || wijk.contains("kralingen") || wijk.contains("kralingse") || wijk.contains("de esch") || wijk.contains("struisenburg"))
            return "kralingen";
        if (wijk.contains("feijenoord") || wijk.contains("noordereiland") || wijk.contains("vreewijk") || wijk.contains("bloemhof") || wijk.contains("hillesluis") || wijk.contains("katendrecht") || wijk.contains("afrikaanderwijk") || wijk.contains("kop van zuid"))
            return "feijenoord";
        if (wijk.contains("ijsselmonde") || wijk.contains("lombardijen") || wijk.contains("groenenhagen") || wijk.contains("hordijkerveld") || wijk.contains("kreekhuizen") || wijk.contains("reyeroord") || wijk.contains("sportdorp") || wijk.contains("veranda") || wijk.contains("zomerland") || wijk.contains("beverwaard") || wijk.contains("pernis") || wijk.contains("rozenburg") || wijk.contains("noordzeeweg"))
            return "ijsselmonde";
        if (wijk.contains("tarwewijk") || wijk.contains("carnisse") || wijk.contains("zuidwijk") || wijk.contains("charlois") || wijk.contains("wielewaal") || wijk.contains("zuidplein") || wijk.contains("pendrecht") || wijk.contains("zuiderpark") || wijk.contains("heijplaat"))
            return "charlois";
        if (wijk.contains("oudeland") || wijk.contains("hoogvliet") || wijk.contains("oudeland") || wijk.contains("nieuw engeland") || wijk.contains("tussenwater") || wijk.contains("westpunt") || wijk.contains("middengebied") || wijk.contains("meeuwenplaat") || wijk.contains("zalmplaat") || wijk.contains("boomgaardshoek") || wijk.contains("hoek van holland"))
            return "hoogvliet";
        return "null";
    }

}
