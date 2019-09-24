package com.hfad.pokedex;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hfad.pokedex.Model;
import com.hfad.pokedex.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    Context c;
    ArrayList<Model> models;
    View view;
    ViewGroup viewGroup;
    String lower_name;

    public MyAdapter(Context c, ArrayList<Model> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.box_listview, null);
        this.viewGroup = viewGroup;
        return new MyHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, int i) {
        String name = models.get(i).getName();
        lower_name = name.toLowerCase();
        if (lower_name.contains("(")) {
            lower_name = lower_name.substring(0, lower_name.indexOf("(") - 1);
            lower_name = lower_name + "-mega";
        }
        myHolder.name.setText(name);
        myHolder.type.setText("type: " + models.get(i).getType1() + " " + models.get(i).getType2());
        myHolder.attack.setText("Atk: " + models.get(i).getAttack());
        myHolder.defense.setText("Dfn: " + models.get(i).getDefense());
        myHolder.health.setText("HP: " + models.get(i).getHealth());
        Glide.with(myHolder.image.getContext()).load("https://img.pokemondb.net/artwork/"+ lower_name + ".jpg").into(myHolder.image);
        if (name.equals("Aegislash ( Blade  Forme )")) {
            Glide.with(myHolder.image.getContext()).load("https://img.pokemondb.net/artwork/aegislash-blade.jpg").into(myHolder.image);
        } else if (name.equals("Aegislash ( Shield  Forme )")) {
            Glide.with(myHolder.image.getContext()).load("https://img.pokemondb.net/artwork/aegislash-shield.jpg").into(myHolder.image);
        }

        myHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                String _name = models.get(position).getName();
                String _type = models.get(position).getType1() + " " + models.get(position).getType2();
                String _attack = models.get(position).getAttack();
                String _defense = models.get(position).getDefense();
                String _health = models.get(position).getHealth();

                Intent intent = new Intent(c, ClickProfileActivity.class);
                intent.putExtra("name", _name);
                intent.putExtra("type", _type);
                intent.putExtra("defense", _defense);
                intent.putExtra("attack", _attack);
                intent.putExtra("health", _health);

                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

}
