package com.hfad.pokedex;

import android.content.ClipData;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfad.pokedex.R;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    ImageView image;
    TextView name, type, attack, health, defense;
    ItemClickListener itemClickListener;


    public MyHolder(@NonNull View itemView) {
        super(itemView);

        this.image = itemView.findViewById(R.id.image);
        this.name = itemView.findViewById(R.id.name);
        this.type = itemView.findViewById(R.id.type);
        this.attack = itemView.findViewById(R.id.attack);
        this.health = itemView.findViewById(R.id.health);
        this.defense = itemView.findViewById(R.id.defense);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClickListener(view, getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListener listener) {
        this.itemClickListener = listener;
    }
}
