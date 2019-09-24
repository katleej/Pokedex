package com.hfad.pokedex;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import static com.hfad.pokedex.ChosenTypes.chosen;

public class FilterActivity extends AppCompatActivity{
    Intent received;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        received = getIntent();
    }

    public void onClickType(View view) {
        select();
    }

    public void onClickSelect(View view) {
        Intent intent = new Intent(this, PokemonListActivity.class);
        EditText attack_pts = (EditText) findViewById(R.id.min_attack_value);
        EditText defense_pts = (EditText) findViewById(R.id.min_defense_value);
        EditText health_pts = (EditText) findViewById(R.id.min_health_value);
        intent.putExtra("min attack pts", attack_pts.getText().toString());
        intent.putExtra("min defense pts", defense_pts.getText().toString());
        intent.putExtra("min health pts", health_pts.getText().toString());
        ArrayList<String> copy = new ArrayList<>();
        for (int i = 0; i < chosen.size(); i++) {
            copy.add(chosen.get(i));
        }
        intent.putStringArrayListExtra("chosen", copy);
        ChosenTypes.empty();
        this.startActivity(intent);
    }

    public void select() {
        final ArrayList<Integer> selectedItems = new ArrayList();  // Where we track the selected items
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("choose your type")
                .setMultiChoiceItems(ChosenTypes.types, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked) {
                                    selectedItems.add(which);
                                    String type = ChosenTypes.types[which];
                                    ChosenTypes.add(type);
                                } else if (selectedItems.contains(which)) {
                                    selectedItems.remove(Integer.valueOf(which));
                                    String type = ChosenTypes.types[which];
                                    ChosenTypes.remove(type);
                                }
                            }
                        })
                .setPositiveButton(
                        "Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                .setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}

