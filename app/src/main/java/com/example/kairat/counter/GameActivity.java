package com.example.kairat.counter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Random;


public class GameActivity extends AppCompatActivity{
    private Button otv1, otv2, otv3, otv4, ran_Button ;
    private ArrayList<Button> Buttons = new ArrayList<Button>();
    private int max_number = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        otv1 = (Button) findViewById(R.id.otvet1);
        otv2 = (Button) findViewById(R.id.otvet2);
        otv3 = (Button) findViewById(R.id.otvet3);
        otv4 = (Button) findViewById(R.id.otvet4);
        Buttons.add(otv1); Buttons.add(otv2); Buttons.add(otv3); Buttons.add(otv4);
        get_seting(getIntent());
        main();

        //SetingsActivity a = SetingsActivity();
        //Log.e("sad", Integer.toString(a.raznicha));
    }

    private void get_seting(Intent intend){
        max_number = Integer.valueOf(intend.getStringExtra("max_number"));

    }

    private String primer (){
        int chislo = randint(0, max_number);
        int chislo1 = randint(0, max_number);
        int chislo2 = randint(0, max_number);
        String strochka = Integer.toString(chislo) + "+" + Integer.toString(chislo1) +  "-" + Integer.toString(chislo2) + "=";

        fillTextView(Integer.toString(chislo) + " + " + Integer.toString(chislo1) +  " - " + Integer.toString(chislo2) + " = ");
        return strochka;
    }
    private int get_otvet(String str){
        ArrayList<String> list = new ArrayList<String>();
        String now = "";
        for (int i = 0; i <= str.length()-1; i++) {
            String element = String.valueOf(str.charAt(i));
            if (element.equals("+") || element.equals("-") || element.equals("=")) {
                list.add(now);
                list.add(element);
                //list.()
                now = "";
            } else{
                now += element;
            }
        }

        int otvet = Integer.valueOf(list.get(0));
        for (int i = 1; i <= list.size()-2; i+=2) {
            String znack = list.get(i);
            String chislo = list.get(i+1);
            //Log.e(znack, chislo);
            if (znack.equals("+")) {
                otvet += Integer.valueOf(chislo);
            } else if (znack.equals("-")) {
                otvet -= Integer.valueOf(chislo);
            }

        }

        //Log.e(list.toString(), str);
        return otvet;
    }
    private void main(){
        String Primer = primer();
        int otvet = get_otvet(Primer);
        for (int i = 0; i <= Buttons.size()-1; i++) {
            ran_Button = Buttons.get(i);
            ran_Button.setTextColor(Color.BLACK);
            ran_Button.setText(String.valueOf(otvet - randint(1, 30)));
        }
        ran_Button = Buttons.get(randint(0, 3));
        ran_Button.setText(String.valueOf(otvet));
        On_Click(String.valueOf(otvet), otv1);
        On_Click(String.valueOf(otvet), otv2);
        On_Click(String.valueOf(otvet), otv3);
        On_Click(String.valueOf(otvet), otv4);

    }
    private void On_Click(String otvett, Button Knopka){
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
