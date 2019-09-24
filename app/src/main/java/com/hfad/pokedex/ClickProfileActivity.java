package com.hfad.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class ClickProfileActivity extends AppCompatActivity {

    TextView name, type, attack, defense, health, spatk, spdef, sphp;
    TextView species, flavor, total;
    ImageView image;
    String _spatk, _spdef, _species, _flavor, _total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_profile);

        name = findViewById(R.id.name);
        type = findViewById(R.id.type);
        defense = findViewById(R.id.defense);
        attack = findViewById(R.id.attack);
        health = findViewById(R.id.health);
        spatk = findViewById(R.id.spatk);
        spdef = findViewById(R.id.spdef);
        species = findViewById(R.id.species);
        flavor = findViewById(R.id.flavor);
        total = findViewById(R.id.total);
        image = findViewById(R.id.image_view);

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        String namey = intent.getStringExtra("name");
        String lower_name = namey.toLowerCase();
        if (lower_name.contains("(")) {
            lower_name = lower_name.substring(0, lower_name.indexOf("(") - 1);
        }

        try {
            String json = PokemonListActivity.json;
            JSONObject pokemonObject = new JSONObject(json);
            String key = namey;
            JSONObject value = pokemonObject.getJSONObject(key);
            _spatk = value.getString("Sp. Atk");
            _spdef = value.getString("Sp. Def");
            _species = value.getString("Species");
            _flavor = value.getString("FlavorText");
            _total = value.getString("Total");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        type.setText("Type: " + intent.getStringExtra("type"));
        defense.setText("Defense Points: " + intent.getStringExtra("defense"));
        attack.setText("Attack Points: " + intent.getStringExtra("attack"));
        health.setText("Health Points: " + intent.getStringExtra("health"));
        spatk.setText("Special Attack Points: " + _spatk);
        spdef.setText("Special Defense Points: " + _spdef);
        species.setText("Species: " + _species);
        flavor.setText("Flavor: " + _flavor);
        total.setText("Total: " + _total);

        Glide.with(image.getContext()).load("https://img.pokemondb.net/artwork/"+ lower_name + ".jpg").into(image);
    }
}
