package com.example.kairat.counter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class GameActivity extends AppCompatActivity{
    private Button otv1;
    private Button otv2;
    private Button otv3;
    private Button otv4;
    private ArrayList<Button> Buttons = new ArrayList<>();
    private int max_number = 10;
    private int kolichestvo_chisel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        otv1 = (Button) findViewById(R.id.otvet1);
        otv2 = (Button) findViewById(R.id.otvet2);
        otv3 = (Button) findViewById(R.id.otvet3);
        otv4 = (Button) findViewById(R.id.otvet4);
        Buttons.add(otv1); Buttons.add(otv2); Buttons.add(otv3); Buttons.add(otv4);
        getSeting(getIntent());
        main();

        //SetingsActivity a = SetingsActivity();
        //Log.e("sad", Integer.toString(a.raznicha));
    }

    private void getSeting(Intent intend){
        max_number = Integer.valueOf(intend.getStringExtra("max_number"));
        kolichestvo_chisel = Integer.valueOf(intend.getStringExtra("kolichestvo_chisel"));
    }

    private String getVirozhenie(){
        Randomvirozhenie virozh = new Randomvirozhenie(2, max_number, kolichestvo_chisel, 1, true, true, true, false);
        return virozh.virozhenie();
    }
    private int getOtvet(String str){
        Eval a = new Eval(str);
        return Integer.parseInt(a.otvet());
    }

    private void main(){
        String Primer = getVirozhenie();
        fillTextView(" " + Primer + " =  ");
        int otvet = getOtvet(Primer);
        Button ran_Button;
        for (int i = 0; i <= Buttons.size()-1; i++) {
            ran_Button = Buttons.get(i);
            ran_Button.setTextColor(Color.BLACK);
            ran_Button.setText(String.valueOf(otvet - randint(1, 30)));
        }
        ran_Button = Buttons.get(randint(0, 3));
        ran_Button.setText(String.valueOf(otvet));
        OnClick(String.valueOf(otvet), otv1);
        OnClick(String.valueOf(otvet), otv2);
        OnClick(String.valueOf(otvet), otv3);
        OnClick(String.valueOf(otvet), otv4);

    }
    private void OnClick(String otvett, Button Knopka){
        final String otvet_string = otvett;
        final Button knopka = Knopka;

        knopka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (otvet_string.equals(knopka.getText())){
                    main();
                } else{
                    knopka.setTextColor(Color.RED);

                }
            }
        });
    }

    private void fillTextView (String text) {
        TextView tv = (TextView) findViewById(R.id.primer);
        tv.setText(text);
    }
    private int randint(int min, int max){
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

}
