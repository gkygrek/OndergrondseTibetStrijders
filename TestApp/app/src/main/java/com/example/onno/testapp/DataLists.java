package com.example.onno.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class DataLists extends AppCompatActivity {

    public static List<String> voorvalNummerList = new ArrayList<String>();
    public static List<String> kennisNameList = new ArrayList<String>();
    public static List<String> MKList = new ArrayList<String>();
    public static List<String> MKOmschrijvingList = new ArrayList<String>();
    public static List<String> pogingList = new ArrayList<String>();
    public static List<String> districtList = new ArrayList<String>();
    public static List<String> werkgebiedList = new ArrayList<String>();
    public static List<String> plaatsList = new ArrayList<String>();
    public static List<String> buurtList = new ArrayList<String>();
    public static List<String> straatFietsList = new ArrayList<String>();
    public static List<String> beginDagsoortList = new ArrayList<String>();
    public static List<String> beginDatumList = new ArrayList<String>();
    public static List<String> beginTijdList = new ArrayList<String>();
    public static List<String> eindDagsoortList = new ArrayList<String>();
    public static List<String> eindDatumList = new ArrayList<String>();
    public static List<String> eindTijd = new ArrayList<String>();
    public static List<String> gemiddeldeJaarList = new ArrayList<String>();
    public static List<String> gemiddeldeMaandList = new ArrayList<String>();
    public static List<String> gemiddeldeDagsoortList = new ArrayList<String>();
    public static List<String> gemiddeldeDagdeelList = new ArrayList<String>();
    public static List<String> TrefwoordList = new ArrayList<String>();
    public static List<String> objectList = new ArrayList<String>();
    public static List<String> merkList = new ArrayList<String>();
    public static List<String> typeList = new ArrayList<String>();
    public static List<String> kleurList = new ArrayList<String>();

    public static List[] diefstalLists = {voorvalNummerList, kennisNameList, MKList, MKOmschrijvingList, pogingList,
            districtList, werkgebiedList, plaatsList, buurtList, straatFietsList, beginDagsoortList, beginDatumList,
            beginTijdList, eindDagsoortList, eindDatumList, eindTijd, gemiddeldeJaarList, gemiddeldeMaandList, gemiddeldeDagsoortList,
            gemiddeldeDagdeelList, TrefwoordList, objectList, merkList, typeList, kleurList
    };

    public static List<String> inventarisNrList = new ArrayList<String>();
    public static List<String> invSrtList = new ArrayList<String>();
    public static List<String> omschrijvingList = new ArrayList<String>();
    public static List<String> straatTrommelList = new ArrayList<String>();
    public static List<String> thvList = new ArrayList<String>();
    public static List<String> deelgemList = new ArrayList<String>();
    public static List<String> datumList = new ArrayList<String>();


    public static List[] trommelLists = {inventarisNrList, invSrtList, omschrijvingList, straatTrommelList, thvList, deelgemList, datumList
    };
}