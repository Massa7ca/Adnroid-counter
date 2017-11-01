package com.example.kairat.counter;

import java.util.ArrayList;
import java.util.Random;

public class Randomvirozhenie{
    private final int kolichestvoChisel;
    private final int parSkobock;
    private final boolean plus;
    private final boolean minuss;
    private final boolean umnozhenie;
    private final boolean delenie;
    private final int[] ot_do = new int[2];
    private static String[] znaki = {" + ", " - ", " * ", " / "};
    private ArrayList<Pair<Integer, Integer>> listSkobok = new ArrayList<>();
    private int dlinna_lista;
    public Randomvirozhenie(){
        this.plus = true;
        this.minuss = true;
        this.umnozhenie = false;
        this.delenie = false;
        this.kolichestvoChisel = 2;
        this.parSkobock = 0;
        this.ot_do[0] = 1;
        this.ot_do[1] = 100;
    }

    public Randomvirozhenie(int ot, int Do){
        this.plus = true;
        this.minuss = true;
        this.umnozhenie = false;
        this.delenie = false;
        this.kolichestvoChisel = 2;
        this.parSkobock = 0;
        this.ot_do[0] = ot;
        this.ot_do[1] = Do;
    }

    public Randomvirozhenie(int ot, int Do, int kolichestvoChisel, int parSkobock){
        this.plus = true;
        this.minuss = true;
        this.umnozhenie = false;
        this.delenie = false;
        this.kolichestvoChisel = kolichestvoChisel;
        this.parSkobock = parSkobock;
        this.ot_do[0] = ot;
        this.ot_do[1] = Do;
    }

    public Randomvirozhenie(int ot, int Do, int kolichestvoChisel, int parSkobock,
                            boolean plus, boolean minuss, boolean umnozhenie, boolean delenie){
        this.plus = plus;
        this.minuss = minuss;
        this.umnozhenie = umnozhenie;
        this.delenie = delenie;
        this.kolichestvoChisel = kolichestvoChisel;
        this.parSkobock = parSkobock;
        this.ot_do[0] = ot;
        this.ot_do[1] = Do;
    }

    private String getRandomZnack(){
        String znack = "";
        if(plus && !minuss && !umnozhenie && !delenie){
            znack = znaki[randint(0,0)];
        } else if(plus && minuss && !umnozhenie && !delenie){
            znack = znaki[randint(0,1)];
        } else if(plus && minuss && umnozhenie && !delenie){
            znack = znaki[randint(0,2)];
        } else if(plus && minuss && umnozhenie){
            znack = znaki[randint(0,3)];
        }
        return znack;
    }

    private boolean biliSkobki(int a, int b){// Проверяет были ли у нас уже скобки на этих кординатах(странна функция)
        boolean otvet = false;
        if (a == 0 && b == dlinna_lista){
            otvet = true;
        }
        for(int i = 0; i != listSkobok.size(); i++){
            int left = listSkobok.get(i).getLeft();// Открываюшееся
            int right = listSkobok.get(i).getRight();// Закрываюшейся
            if(left == b || right == a){
                otvet = true;
                break;
            } else if(left == a && right == b){
                otvet = true;
                break;
            }
        }
        return otvet;
    }

    private Pair<Integer, Integer> getIndexPariSkobok(){
        // Даёт кординаты открытой и закрытой скобки
        int per, vtor;
        while(true) {
            per = randint(0, kolichestvoChisel*2-1);
            vtor = randint(0, kolichestvoChisel*2-1);
            if(!biliSkobki(per, vtor)) {
                if (per != vtor && vtor - per >= 2 && vtor > per) {
                    if (per % 2 == 0 && vtor % 2 == 0) { // Что-бы скобка не встала перед знаком и до знака 6(-7+18-)1
                        break;
                    }
                }
            }
        }

        listSkobok.add(new Pair<>(per, vtor));
        //System.out.println(listSkobok);
        return new Pair<>(per, vtor);
    }

    private ArrayList<String> listVirozhenie(){
        ArrayList<String> now = new ArrayList<>();
        for(int i = 0; i != kolichestvoChisel; i++){
            now.add(getChislo());
            if(i + 1 != kolichestvoChisel) {
                now.add(getRandomZnack());
            }
        }
        dlinna_lista = now.size()-1;
        return now;
    }

    private ArrayList<String> vstaviSkobki(ArrayList<String> list){
        for(int j = 0; j != parSkobock; j++){
            ArrayList<String> now = new ArrayList<>();
            Pair<Integer, Integer> skobIndex =  getIndexPariSkobok();
            for(int i = 0; i != list.size(); i++){ // Ствит одну пару скобок
                String eliment = list.get(i);

                if (i == skobIndex.getLeft()){
                    eliment = "(" + eliment;
                }
                if (i == skobIndex.getRight()){
                    eliment =  eliment + ")";
                }
                now.add(eliment);
            }
            if (list.size() == skobIndex.getRight()){
                String last = now.get(now.size()-1);
                now.remove(now.size()-1);
                now.add(last + ")");
            }
            list = (ArrayList<String>) now.clone();;
            //System.out.println(list);
        }
        return list;
    }

    private String getChislo(){
        int chislo;
        while (true) {
            chislo = randint(ot_do[0], ot_do[1]);
            if (chislo != 0){
                break;
            }
        }
        return String.valueOf(chislo);
    }

    private int randint(int min, int max){
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public String virozhenie(){
        StringBuilder now = new StringBuilder("");
        ArrayList<String> listVir = listVirozhenie();
        //System.out.println(listVir);
        listVir = vstaviSkobki(listVir);
        //System.out.println(listVir);
        for(int i = 0; i != listVir.size(); i++){
            now.append(listVir.get(i));
        }
        return now.toString();
    }
}

