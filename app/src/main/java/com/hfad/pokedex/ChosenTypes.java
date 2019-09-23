package com.hfad.pokedex;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ChosenTypes {
    public static ArrayList<String> chosen = new ArrayList<String>();

    public static void add(String string) {
        chosen.add(string);
    }

    public static void remove(String string) {
        chosen.remove(string);
    }

    public static String[] getList() {
        String str[] = new String[chosen.size()];
        for (int j = 0; j < chosen.size(); j++) {
            str[j] = chosen.get(j);
        }
        return str;
    }

    public static String[] types = {"Psychic", "Dark", "Ghost", "Steel", "Rock", "Grass", "Ice", "Bug",
        "Flying", "Electric", "Dragon", "Fairy","Poison", "Normal", "Ground", "Water", "Fire", "Fighting"};
}
