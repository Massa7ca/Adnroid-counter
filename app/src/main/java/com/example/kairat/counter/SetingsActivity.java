package com.example.kairat.counter;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class SetingsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView max_number_text;
    private TextView kolichestvo_chisel_text;
    private SeekBar max_number_Bar;
    private SeekBar kolichestvo_chisel_Bar;
    private TextBarPair max_number;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setings);
        Button save = (Button) findViewById(R.id.save_button);
        save.setOnClickListener(this);
        max_number_text = (TextView)findViewById(R.id.max_number_text);
        max_number_Bar = (SeekBar)findViewById(R.id.max_number_seekBar);
        max_number = new TextBarPair(max_number_Bar, max_number_text, 10, 200);
        //max_number.onCreate(savedInstanceState);
        //max_number_Bar.setOnSeekBarChangeListener(this);
        ///////////////////////////////////////////////////////////
        kolichestvo_chisel_text = (TextView)findViewById(R.id.kolichestvo_chisel_text);
        kolichestvo_chisel_Bar = (SeekBar)findViewById(R.id.kolichestvo_chisel_seekBar);
        //kolichestvo_chisel_Bar.setOnSeekBarChangeListener(this);
        getSeting(getIntent());

        //SetingsActivity a = SetingsActivity();
        //Log.e("sad", Integer.toString(a.raznicha));
    }

    private void getSeting(Intent intend){
        String max = intend.getStringExtra("max_number");
        max_number_text.setText(max);
        max_number_Bar.setProgress(Integer.valueOf(max));

        String kolichestvo_chisel = intend.getStringExtra("kolichestvo_chisel");
        kolichestvo_chisel_text.setText(kolichestvo_chisel);
        kolichestvo_chisel_Bar.setProgress(Integer.valueOf(kolichestvo_chisel));


    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.save_button:
                //String m = max_num.getText().toString();
                intent.putExtra("max_number", max_number_Bar.getProgress());
                intent.putExtra("kolichestvo_chisel", kolichestvo_chisel_Bar.getProgress());
                break;
        }
        setResult(RESULT_OK, intent);
        finish();
    }

}
