//package com.hfad.pokedex;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//
//public class PokemonResource {
//
//    public ArrayList<Pokemon> entireList;
//
//    public void setEntireList() {
//        ArrayList<Pokemon> pokemon_list = new ArrayList<>();
//
//        try {
//            String jsonDataString = readJsonDataFromFile();
//            JSONObject pokemonObject = new JSONObject(jsonDataString);
//
//            while (pokemonObject.keys().hasNext()) {
//                //fetch data from individual pokemon
//                String key = pokemonObject.keys().next();
//                JSONObject value = pokemonObject.getJSONObject(key);
//                String attack = value.getString("Attack");
//                String defense = value.getString("Defense");
//                String flavor = value.getString("FlavorText");
//                String health = value.getString("HP");
//                String special_attack = value.getString("Sp. Atk");
//                String special_defense = value.getString("Sp. Def");
//                String species = value.getString("Species");
//                String speed = value.getString("Speed");
//                String total = value.getString("Total");
//                String type1 = value.getJSONArray("Type").get(0).toString();
//                String type2 = "-";
//                if (value.getJSONArray("Type").length() > 1) {
//                    type2 = value.getJSONArray("Type").get(1).toString();
//                }
//
//                Pokemon pokemon = new Pokemon();
//
//                pokemon.setName(key);
//                pokemon.setDefense(Integer.parseInt(defense));
//                pokemon.setFlavor(flavor);
//                pokemon.setAttack(Integer.parseInt(attack));
//                pokemon.setHealth(Integer.parseInt(health));
//                pokemon.setSpatk(Integer.parseInt(special_attack));
//                pokemon.setSpdef(Integer.parseInt(special_defense));
//                pokemon.setSpecies(species);
//                pokemon.setType1(type1);
//                pokemon.setType2(type2);
//                pokemon.setTotal(Integer.parseInt(total));
//
//                pokemon_list.add(pokemon);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        entireList = pokemon_list;
//    }
//
//    public ArrayList<Pokemon> getFilteredList(int min_defense, int min_attack, int min_health, ArrayList<String> chosen_list) {
//        ArrayList<Pokemon> filteredList = new ArrayList<>();
//        for (int i = 0; i < entireList.size(); i++) {
//            Pokemon cursor = entireList.get(i);
//            if (chosen_list.contains(cursor.getType1()) || chosen_list.contains(cursor.getType2())) {
//                if (cursor.getDefense() >= min_defense && cursor.getAttack() >= min_attack && cursor.getHealth() >= min_health) {
//                    Pokemon pokemon = new Model();
//                    pokemon.setName(cursor.getName());
//                    pokemon.setType(cursor.getType1() + " / " + cursor.getType2());
//                    filteredList.add(pokemon);
//                }
//            } else {
//                continue;
//            }
//        }
//
//        return filteredList;
//    }
//
//    public ArrayList<Pokemon> getEntireList() {
//        return entireList;
//    }
//    private String readJsonDataFromFile() throws IOException {
//        InputStream inputStream = null;
//        String data = "";
//
//        try {
//            inputStream = getopenRawResource(R.raw.pokedata);
//            BufferedReader bufferedReader = new BufferedReader(
//                    new InputStreamReader(inputStream, "UTF-8"));
//            data = bufferedReader.readLine();
//
//        } finally {
//            if (inputStream != null) {
//                inputStream.close();
//            }
//        }
//        return data;
//    }
//}
