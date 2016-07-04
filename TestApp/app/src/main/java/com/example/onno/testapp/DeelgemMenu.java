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

        buttonCentrum = setAction("centrum", buttonCentrum);
        buttonDelfshaven = setAction("delfshaven", buttonDelfshaven);
        buttonOverschie = setAction("overschie", buttonOverschie);
        buttonNoord = setAction("noord", buttonNoord);
        buttonHillegersberg = setAction("hillegersberg", buttonHillegersberg);
        buttonKralingen = setAction("kralingen", buttonKralingen);
        buttonFeijenoord = setAction("feijenoord", buttonFeijenoord);
        buttonIjsselmonde = setAction("ijsselmonde", buttonIjsselmonde);
        buttonCharlois = setAction("charlois", buttonCharlois);
        buttonHoogvliet = setAction("hoogvliet", buttonHoogvliet);


        context = this.getApplicationContext();
    }

    private Button setAction(final String gem, Button button) {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StolenBicyclesAndBikeContainersPerMonth.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                StolenBicyclesAndBikeContainersPerMonth.currentDeelgem = gem;
                context.startActivity(intent);
            }
        });
        return button;
    }

}
