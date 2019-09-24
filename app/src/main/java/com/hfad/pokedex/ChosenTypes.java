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

    public static ArrayList<String> getList() {
        return chosen;
    }

    public static void empty() {
        chosen.clear();
    }

    public static String[] types = {"Psychic", "Dark", "Ghost", "Steel", "Rock", "Grass", "Ice", "Bug",
        "Flying", "Electric", "Dragon", "Fairy","Poison", "Normal", "Ground", "Water", "Fire", "Fighting"};
}
