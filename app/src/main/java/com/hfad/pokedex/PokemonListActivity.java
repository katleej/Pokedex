package com.hfad.pokedex;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
        adapter = new MyAdapter(this, getList());
        recyclerView.setAdapter(adapter);
    }

    public ArrayList<Model> getList() {
        String min_attack = intent.getStringExtra("min attack pts");
        String min_defense = intent.getStringExtra("min defense pts");
        String min_health = intent.getStringExtra("min health pts");
        String[] chosen_list = ChosenTypes.getList();

        databaseHelper = new PokemonDatabaseHelper(this);
        db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("pokemon", new String[]{"name", "type1", "type2"},
                "type1 = ? OR type2 =?", chosen_list, null, null, null);
        ArrayList<Model> models = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                Model pokemon = new Model();
                pokemon.setName(cursor.getString(0));
                pokemon.setType(cursor.getString(1) + ", " + cursor.getString(2));
                models.add(pokemon);
            }
        } finally {
            cursor.close();
        }

//        ArrayList<Model> models = new ArrayList<>();
//        Model pokemon = new Model();
//        pokemon.setName("Poke");
//        pokemon.setType("mon");
//        models.add(pokemon);
        return models;
    }
}
