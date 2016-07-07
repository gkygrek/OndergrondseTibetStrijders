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
import java.util.List;

public class StolenBicyclesAndBikeContainersPerMonth extends AppCompatActivity {
    public static String currentDeelgem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barchartmain);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Create the barchart by calling other methods
        createBarChart(createEntries(countMonthsGroup1(getIndexList("fiets"))), createEntries(countMonthsGroup2(getIndexList("fiets"))));
    }

    private List<Integer> getIndexList(String filterWord) {
        List<Integer> numbers = new ArrayList<>();
        filterWord = filterWord.toLowerCase();

        //Check if the data contains the filterWord and add that index to the numbers list
        ListIterator<String> listIterator = new ListIterator(DataLists.MKOmschrijvingList);
        while (listIterator.hasNext()) {
            listIterator.getNext();
            if (DataLists.MKOmschrijvingList.get(listIterator.getIndex()).toLowerCase().contains(filterWord))
                numbers.add(listIterator.getIndex());
        }
        return numbers;
    }

    private Integer[] countMonthsGroup1(List<Integer> numbers) {
        //Count the amounts and add it to the amounts array
        Integer[] amounts = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < numbers.size() ; i++) {
            Integer maandNr = Integer.parseInt(DataLists.gemiddeldeMaandList.get(numbers.get(i)));
            if (getDeelGem(DataLists.werkgebiedList.get(i)).toLowerCase().contains(currentDeelgem.toLowerCase()))
                amounts[maandNr] = amounts[maandNr] + 1;
        }
        return amounts;
    }

    private Integer[] countMonthsGroup2(List<Integer> numbers) {
        //Count the amounts and add it to the amounts2 array
        Integer[] amounts2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        ListIterator<String> listIterator = new ListIterator<>(DataLists.datumList);
        while (listIterator.hasNext()) {
            listIterator.getNext();
            if (DataLists.deelgemList.get(listIterator.getIndex()).toLowerCase().contains(currentDeelgem.toLowerCase())) {
                String datum = DataLists.datumList.get(listIterator.getIndex());
                datum = datum.substring(datum.indexOf("-") + 1); //Adapt the datum string so you only get the month
                datum = datum.substring(0, datum.indexOf("-"));

                Integer maandNr = Integer.parseInt(datum);
                amounts2[maandNr] = amounts2[maandNr] + 1;
            }
        }
        return amounts2;
    }

    private ArrayList<BarEntry> createEntries(Integer[] amounts) {
        //Add entries to the group list
        ArrayList<BarEntry> group = new ArrayList<>();
        for (int i = 1; i < amounts.length ; i++)
            group.add(new BarEntry(amounts[i], i - 1));
        return group;
    }

    private void createBarChart(ArrayList<BarEntry> group1,  ArrayList<BarEntry> group2) {
        BarDataSet barDataSet1 = new BarDataSet(group1, "Bicycle theft amount"); //Create datasets
        BarDataSet barDataSet2 = new BarDataSet(group2, "Installed bicycle containers amount");
        int[] redColor = {ColorTemplate.rgb("#ff0000")};
        barDataSet2.setColors(redColor);

        BarChart barChart = (BarChart) findViewById(R.id.chart);

        String[] labels = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        BarData data = new BarData(labels, barDataSet1);
        data.setValueTextSize(10);
        data.addDataSet(barDataSet2); //Add datasets to the data
        barChart.setDescription("Years: 2009-2013");
        barChart.animateY(3000); //Add a simple animation
        barChart.setData(data); //Set data to the barchart
        barChart.setVisibleXRange(0, 10);
    }

    private String getDeelGem(String wijk) {
        //This method gets a "wijk" string and returns the corresponding deelgem
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
