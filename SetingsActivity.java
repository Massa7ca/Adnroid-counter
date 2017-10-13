package com.example.kairat.counter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SetingsActivity extends AppCompatActivity {
    public int ot = 5;
    public int i_do = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setings);
    }
    public void raznicha(){
        ot = 10;
        i_do = 100;
    }
}
