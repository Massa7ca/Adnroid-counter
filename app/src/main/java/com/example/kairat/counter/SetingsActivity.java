package com.example.kairat.counter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class SetingsActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener{
    Button save;
    private TextView max_number_text;
    private SeekBar max_number_Bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setings);
        save = (Button) findViewById(R.id.save_button);
        save.setOnClickListener(this);
        max_number_text = (TextView)findViewById(R.id.max_number_text);
        max_number_Bar = (SeekBar)findViewById(R.id.max_number_seekBar);
        max_number_Bar.setOnSeekBarChangeListener(this);
        get_seting(getIntent());

        //SetingsActivity a = SetingsActivity();
        //Log.e("sad", Integer.toString(a.raznicha));
    }

    private void get_seting(Intent intend){
        String max = intend.getStringExtra("max_number");
        max_number_text.setText(max);
        max_number_Bar.setProgress(Integer.valueOf(max));

    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.save_button:
                //String m = max_num.getText().toString();
                intent.putExtra("max_number", max_number_Bar.getProgress());
                break;


        }
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onProgressChanged(SeekBar max_number_Bar, int progress, boolean fromUser) {
        max_number_text.setText(String.valueOf(max_number_Bar.getProgress()));
    }

    @Override
    public void onStartTrackingTouch(SeekBar max_number_Bar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar max_number_Bar) {

    }
}
