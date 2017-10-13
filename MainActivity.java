package com.example.kairat.counter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent game = new Intent(this, gameActivity.class);
        Button play = (Button) findViewById(R.id.Button_play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(game);
            }
        });

        final Intent seting = new Intent(this, SetingsActivity.class);
        Button set = (Button) findViewById(R.id.Setings);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(seting);
            }
        });
    }


}
