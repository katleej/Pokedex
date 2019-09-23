package com.hfad.pokedex;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class PokemonListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter adapter;
    Intent intent;
    SQLiteDatabase db;
    PokemonDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);
        intent = getIntent();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        databaseHelper = new PokemonDatabaseHelper(this);
        adapter = new MyAdapter(this, getList());
    }

    public ArrayList<Model> getList() {
        String min_attack = intent.getStringExtra("min attack pts");
        String min_defense = intent.getStringExtra("min defense pts");
        String min_health = intent.getStringExtra("min health pts");
        String[] chosen_list = ChosenTypes.getList();

        databaseHelper = new PokemonDatabaseHelper(this);
        Cursor cursor = databaseHelper.db.query("pokemon", new String[]{"name", "type1"},
                "type1 = ?",
                 new String[] {"Grass"}, null, null, null);

        ArrayList<Model> models = new ArrayList<>();
        try {
            while (cursor.moveToFirst()) {
                Model pokemon = new Model();
                pokemon.setName(cursor.getString(0));
                pokemon.setType(cursor.getString(1) + ", " + cursor.getString(2));
                models.add(pokemon);
            }
        } finally {
            cursor.close();
        }

        return models;
    }


}
