package com.example.kairat.counter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button play, set;

    final int REQUEST_CODE_SETING = 1;
    private int max_number = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = (Button) findViewById(R.id.Button_play);
        set = (Button) findViewById(R.id.Setings);

        play.setOnClickListener(this);
        set.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.Button_play:
                intent = new Intent(this, GameActivity.class);
                startActivity(intent);
                break;
            case R.id.Setings:
                intent = new Intent(this, SetingsActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SETING);
                //Log.e("myLogs", "requestCode = ");
                break;
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            Log.e("myLogs", "requestCode = " + requestCode + ", resultCode = " + resultCode + " data = " +
                    data.getIntExtra("max_number", 10));
            switch (requestCode){
                case REQUEST_CODE_SETING:
                    int color = data.getIntExtra("max_number", 10);
                    max_number = color;
                    break;
            }
        }

    }
}
