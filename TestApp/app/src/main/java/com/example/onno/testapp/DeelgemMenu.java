package com.example.onno.testapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DeelgemMenu extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deelgem_menu);
        //Create buttons
        Button buttonCentrum = (Button) findViewById(R.id.centrum);
        Button buttonDelfshaven = (Button) findViewById(R.id.delfshaven);
        Button buttonOverschie = (Button) findViewById(R.id.overschie);
        Button buttonNoord = (Button) findViewById(R.id.noord);
        Button buttonHillegersberg = (Button) findViewById(R.id.hillegersberg);
        Button buttonKralingen = (Button) findViewById(R.id.kralingen);
        Button buttonFeijenoord = (Button) findViewById(R.id.feijenoord);
        Button buttonIjsselmonde = (Button) findViewById(R.id.ijsselmonde);
        Button buttonCharlois = (Button) findViewById(R.id.charlois);
        Button buttonHoogvliet = (Button) findViewById(R.id.hoogvliet);
        //Set actions to the buttons
        setAction("centrum", buttonCentrum);
        setAction("delfshaven", buttonDelfshaven);
        setAction("overschie", buttonOverschie);
        setAction("noord", buttonNoord);
        setAction("hillegersberg", buttonHillegersberg);
        setAction("kralingen", buttonKralingen);
        setAction("feijenoord", buttonFeijenoord);
        setAction("ijsselmonde", buttonIjsselmonde);
        setAction("charlois", buttonCharlois);
        setAction("hoogvliet", buttonHoogvliet);

        context = this.getApplicationContext();
    }

    private void setAction(final String gem, Button button) {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StolenBicyclesAndBikeContainersPerMonth.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                StolenBicyclesAndBikeContainersPerMonth.currentDeelgem = gem; //currentDeelgem string changes so only the data from the chosen button will be displayed
                context.startActivity(intent);
            }
        });
    }

}
