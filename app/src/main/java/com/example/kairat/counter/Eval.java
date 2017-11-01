package com.example.kairat.counter;

import java.util.ArrayList;

public class Eval {
    private String main_str;
    private static final String ZAMENA_MINUSA = "m";

    public Eval(String str) {
        str = str.replaceAll(" ", "");
        this.main_str = str;
    }

    //////--------------------new_source--------------------------------------//////

    private boolean vseLiChifri(String stroka) {
        // Возврашяет True если в строка чисто (не работате с если строка такого вида 10.05 или 10.0)
        if (String.valueOf(stroka.charAt(0)).equals("-")) {
            stroka = stroka.substring(1);
        }
        for (int i = 0; i != stroka.length(); i++) {
            if (!Character.isDigit(stroka.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private Pair<Integer, Integer> getChislo(String str, int index) {
        StringBuilder now = new StringBuilder();
        for (int i = index; i != str.length(); i++) {
            String eliment = String.valueOf(str.charAt(i));
            if (eliment.equals("+") || eliment.equals("*") || eliment.equals("/") || eliment.equals(ZAMENA_MINUSA)) {
                index = i;
                break;
            } else {
                now.append(eliment);
            }

        }
        return new Pair<>(Integer.valueOf(now.toString()), index);
    }

    private Pair<String, Integer> getZnack(String str, int index) {
        str = str.substring(index, str.length());
        String a = String.valueOf(str.charAt(0));
        return new Pair<>(a, index);
    }

    private Integer poshitaySlagaemoye(ArrayList<Pair<Integer, String>> ListPair) {
        if (ListPair.size() == 1) {
            return ListPair.get(0).getLeft();
        }

        Integer otvet = ListPair.get(0).getLeft();
        String znakc = ListPair.get(0).getRight();
        for (int i = 1; i != ListPair.size(); i++) {
            Integer chislo = ListPair.get(i).getLeft();

            if (znakc.equals("*")) {
                otvet *= chislo;
            } else if (znakc.equals("/")) {
                otvet /= chislo;
            }
            znakc = ListPair.get(i).getRight();
        }
        return otvet;
    }

    private Pair<Integer, Integer> getSlagaemoye(String str, int index) {
        boolean umnozhali = false;
        ArrayList<Pair<Integer, String>> ListPair = new ArrayList<>();
        Pair<Integer, String> PairChisloZnack = new Pair<>();
        while (index < str.length()) {
            Pair<Integer, Integer> PairChislo = getChislo(str, index);
            Integer chislo = PairChislo.getLeft();
            index = PairChislo.getRight();

            Pair<String, Integer> PairZnack = getZnack(str, index);
            String znak = PairZnack.getLeft();
            index = PairZnack.getRight() + 1;
            //System.out.println(chislo + " " + znak);

            if (znak.equals("+") || znak.equals(ZAMENA_MINUSA)) {
                if (!umnozhali) {
                    PairChisloZnack.set(chislo, "");
                    ListPair.add(PairChisloZnack.clone());
                } else {
                    PairChisloZnack.set(chislo, "");
                    ListPair.add(PairChisloZnack.clone());
                }
                break;

            } else if (znak.equals("*") || znak.equals("/")) {
                PairChisloZnack.set(chislo, znak);
                ListPair.add(PairChisloZnack.clone());
                umnozhali = true;

            }
        }
        //System.out.println(ListPair + "otvet = " + poshitaySlagaemoye(ListPair));

        return new Pair<>(poshitaySlagaemoye(ListPair), index);
    }

    private Integer getOtvetNaSkobku(String str) {
        // если параметор был 10+-7+3*8*7+2*3 -> [[10], [7], [3*8*7], [2*3]]
        // -10 - -5
        // getSlagaemoe() -> -10
        // getZnak() -> -
        // getSlagaemoe() -> -5
        // otvet -= -5
        // 10+7+3*8*7+2*3"
        str = izmeniStroku(str) + "+"; // Что-бы getSlagaemoye работала нормально
        //System.out.println(str);
        int index = 0;
        Integer suma = getSlagaemoye(str, index).getLeft();
        String znackk;
        while (index < str.length()) {
            Pair<Integer, Integer> Slagaemoye = getSlagaemoye(str, index);
            index = Slagaemoye.getRight();
            znackk = String.valueOf(str.charAt(index - 1));

            if (index == str.length()) {
                break;
            }

            Pair<Integer, Integer> Slagaemoye2 = getSlagaemoye(str, index);
            Integer chislo = Slagaemoye2.getLeft();

            if (znackk.equals("+")) {
                suma += chislo;
                //System.out.println(" + " + chislo);
            } else if (znackk.equals(ZAMENA_MINUSA)) {
                suma -= chislo;
                //System.out.println(" - " + chislo);
            }
        }

        return suma;
    }

    private String izmeniStroku(String str) {
        StringBuilder new_string = new StringBuilder("");
        String eliment1, eliment2 = "", perviy_eliment;
        if (String.valueOf(str.charAt(0)).equals("-")) {
            perviy_eliment = String.valueOf(str.charAt(0));
            str = str.substring(1);
        } else {
            perviy_eliment = "";
        }
        //System.out.println(str);
        for (int i = 0; i != str.length(); i++) {
            eliment1 = String.valueOf(str.charAt(i));
            if (i == str.length() - 1) {
                eliment2 = "";
            } else {
                eliment2 = String.valueOf(str.charAt(i + 1));
            }
            //System.out.println(eliment1);
            //System.out.println(eliment1 + " " + eliment2);
            //System.out.println(i + " ");
            if (eliment1.equals("-") && eliment2.equals("-")) {
                new_string.append(ZAMENA_MINUSA).append("-");
                i += 1;
            } else if (eliment1.equals("+") && eliment2.equals("-")) {
                new_string.append("+").append("-");
                i += 1;
            } else if (eliment1.equals("*") && eliment2.equals("-")) {
                new_string.append("*").append("-");
                i += 1;
            } else if (eliment1.equals("/") && eliment2.equals("-")) {
                new_string.append("/").append("-");
                i += 1;
            } else if (eliment1.equals("-")) {
                new_string.append(ZAMENA_MINUSA);
            } else {
                new_string.append(eliment1);
            }

        }
        //new_string.append(eliment1);
        return perviy_eliment + new_string.toString() + eliment2;
    }

    //////-----------------end_new_source---------------------------------------//////

    private boolean elimentIliSkobka(String eliment) {
        if (eliment.length() == 1) {
            if (eliment.equals("0") || eliment.equals("1") || eliment.equals("2") || eliment.equals("3") ||
                    eliment.equals("4") || eliment.equals("5") || eliment.equals("6") || eliment.equals("7") ||
                    eliment.equals("8") || eliment.equals("9") || eliment.equals("*") || eliment.equals("/")
                    || eliment.equals("+") || eliment.equals("-")){
                return true;
            }
        } else {
            System.out.println("WAT");
        }

        return false;
    }

    private ArrayList<String> naidiPervuyouSkobku(String stroka) {
        //
        //stroka = stroka.replaceAll(" ", "");
        StringBuilder now = new StringBuilder();
        String per = "";
        String vtor;
        ArrayList<String> full = new ArrayList<>();
        for (int i = 0; i != stroka.length(); i++) {
            String a = String.valueOf(stroka.charAt(i));
            if (a.equals("(")) {
                per = String.valueOf(i);
                now = new StringBuilder();
            } else if (elimentIliSkobka(a)) {
                now.append(a);
            } else if (a.equals(")")) {
                vtor = String.valueOf(i + 1);
                full.add(now.toString());
                full.add(per);
                full.add(vtor);
                return full;
            }
        }
        full.add(stroka);
        full.add("0");
        full.add(String.valueOf(stroka.length()));
        return full;
    }

    private String dayNovuyuStroku(ArrayList<String> list, String stroka, String sredina) {
        int per_index = Integer.valueOf(list.get(1));
        int vtor_index = Integer.valueOf(list.get(2));
        return stroka.substring(0, per_index) + sredina + stroka.substring(vtor_index, stroka.length());

    }

    private String shitaetOtvet(String str) {
        ArrayList<String> full = naidiPervuyouSkobku(str);
        //System.out.println("full " + full);
        String otvet_na_stroku = String.valueOf(getOtvetNaSkobku(full.get(0)));
        //System.out.println("otvet_na_stroku " + otvet_na_stroku);
        String now = dayNovuyuStroku(full, str, otvet_na_stroku);
        //System.out.println("now " + now);
        if (vseLiChifri(now)) {
            return now;
        }
        return shitaetOtvet(now);
    }

    public String otvet() {
        //System.out.println(izmeniStroku("-1"));
        //return "";
        return shitaetOtvet(main_str);

    }

}