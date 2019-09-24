package com.hfad.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ClickProfileActivity extends AppCompatActivity {

    TextView name, type, attack, defense, health, spatk, spdef, sphp;
    TextView species, flavor, total;
    ImageView image;
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
        sphp = findViewById(R.id.sphp);
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
        type.setText("Type: " + intent.getStringExtra("type"));
        defense.setText("Defense Points: " + intent.getStringExtra("defense"));
        attack.setText("Attack Points: " + intent.getStringExtra("attack"));
        health.setText("Health Points: " + intent.getStringExtra("health"));
        Glide.with(image.getContext()).load("https://img.pokemondb.net/artwork/"+ lower_name + ".jpg").into(image);
    }
}
