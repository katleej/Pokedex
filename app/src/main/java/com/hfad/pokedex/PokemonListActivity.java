package com.hfad.pokedex;

import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class PokemonListActivity extends AppCompatActivity {

    private Resources mResources;
    public static String json;
    int min_attack, min_defense, min_health = 0;
    RecyclerView recyclerView;
    MyAdapter adapter;
    Intent intent;
    private String user_input = "";
    private TextWatcher text = null;
    ArrayList<Model> models = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);
        intent = getIntent();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final EditText search = (EditText) findViewById(R.id.search);
        getList();
        text = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                user_input = search.getText().toString();
                for (int i = 0; i < models.size(); i++) {
                    if (!models.get(i).getName().contains(s)) {
                        models.remove(i);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        search.addTextChangedListener(text);
        adapter = new MyAdapter(this, models);
        recyclerView.setAdapter(adapter);
    }


    public void getList() {
        getNumbers();
        ArrayList<String> chosen_list = intent.getStringArrayListExtra("chosen");

        String name, type1, type2, defense, health, attack = "";

        if (chosen_list.size() < 3) {
            try {
                String jsonDataString = readJsonDataFromFile();
                JSONObject pokemonObject = new JSONObject(jsonDataString);
                Iterator iterator = pokemonObject.keys();
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    JSONObject value = pokemonObject.getJSONObject(key);
                    name = key;
                    type1 = value.getJSONArray("Type").get(0).toString();
                    type2 = " ";
                    if (value.getJSONArray("Type").length() > 1) {
                        type2 = value.getJSONArray("Type").get(1).toString();
                    }
                    attack = value.getString("Attack");
                    defense = value.getString("Defense");
                    health = value.getString("HP");

                    if (Integer.parseInt(attack) >= min_attack && Integer.parseInt(defense) >= min_defense
                            && Integer.parseInt(health) >= min_health) {
                        if ((chosen_list.size() == 2 && chosen_list.contains(type1) && chosen_list.contains(type2))) {
                            Model pokemon = new Model();
                            pokemon.setName(name);
                            pokemon.setType1(type1);
                            pokemon.setType2(type2);
                            pokemon.setAttack(attack);
                            pokemon.setDefense(defense);
                            pokemon.setHealth(health);
                            models.add(pokemon);
                        } else if ((chosen_list.size() == 1 && (chosen_list.contains(type1) || chosen_list.contains(type2)))) {
                            Model pokemon = new Model();
                            pokemon.setName(name);
                            pokemon.setType1(type1);
                            pokemon.setType2(type2);
                            pokemon.setAttack(attack);
                            pokemon.setDefense(defense);
                            pokemon.setHealth(health);
                            models.add(pokemon);
                        } else if ((chosen_list.isEmpty())){
                            Model pokemon = new Model();
                            pokemon.setName(name);
                            pokemon.setType1(type1);
                            pokemon.setType2(type2);
                            pokemon.setAttack(attack);
                            pokemon.setDefense(defense);
                            pokemon.setHealth(health);
                            models.add(pokemon);
                        }
                    } else {
                        continue;
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private String readJsonDataFromFile() throws IOException {
        InputStream inputStream = null;
        String data = "";

        try {
            inputStream = getResources().openRawResource(R.raw.pokedata);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));
            data = bufferedReader.readLine();

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        json = data;
        return data;
    }

    private void getNumbers() {
        try {
            min_attack = Integer.parseInt(intent.getStringExtra("min attack pts"));
        } catch (NumberFormatException e) {
            min_attack = 0;
        }

        try {
            min_defense = Integer.parseInt(intent.getStringExtra("min defense pts"));
        } catch (NumberFormatException e) {
            min_defense = 0;
        }

        try {
            min_health = Integer.parseInt(intent.getStringExtra("min defense pts"));
        } catch (NumberFormatException e) {
            min_health = 0;
        }
    }
}
