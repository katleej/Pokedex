package com.hfad.pokedex;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.hfad.pokedex.Model;
import com.hfad.pokedex.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    Context c;
    ArrayList<Model> models;

    public MyAdapter(Context c, ArrayList<Model> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.box_listview, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        String name = models.get(i).getName();
        String lower_name = name.toLowerCase();
        myHolder.name.setText(name);
        myHolder.type.setText(models.get(i).getType());
        Glide.with(c).load("http://img.pokemondb.net/artwork/" + lower_name + ".jpg");
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

}
