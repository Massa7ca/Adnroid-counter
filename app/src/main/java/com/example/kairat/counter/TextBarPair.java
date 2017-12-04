package com.example.kairat.counter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

public class TextBarPair extends SetingsActivity implements SeekBar.OnSeekBarChangeListener{
    private final SeekBar bar;
    private final TextView text;
    private final int from;

    protected TextBarPair(SeekBar bar, TextView text, int from, int before) {
        this.bar = bar;
        this.text = text;
        this.bar.setMax(before);
        this.from = from;
        this.bar.setOnSeekBarChangeListener(this);
    }

    public int get(){
        return bar.getProgress()+from;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        text.setText((bar.getProgress())+from);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
