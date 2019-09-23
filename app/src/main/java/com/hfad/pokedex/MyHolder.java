package com.hfad.pokedex;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfad.pokedex.R;

public class MyHolder extends RecyclerView.ViewHolder{

    ImageView image;
    TextView name, type;


    public MyHolder(@NonNull View itemView) {
        super(itemView);

        this.image = itemView.findViewById(R.id.image);
        this.name = itemView.findViewById(R.id.name);
        this.type = itemView.findViewById(R.id.type);
    }
}
