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

    public static String[] types = {"psychic", "dark", "ghost", "steel", "rock", "grass", "ice", "bug",
        "flying", "electric", "dragon", "fairy","poison", "normal", "ground", "water", "fire", "fighting"};
}
