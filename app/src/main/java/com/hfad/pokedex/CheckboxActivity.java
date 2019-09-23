package com.hfad.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.TextView;

public class CheckboxActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkbox);
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()){
            case R.id.psychic:
                ChosenTypes.add("psychic");
                break;

            case R.id.dark:
                ChosenTypes.add("dark");
                break;

            case R.id.ghost:
                ChosenTypes.add("ghost");
                break;

            case R.id.steel:
                ChosenTypes.add("steel");
                break;

            case R.id.rock:
                ChosenTypes.add("rock");
                break;

            case R.id.grass:
                ChosenTypes.add("grass");
                break;

            case R.id.ice:
                ChosenTypes.add("ice");
                break;


            case R.id.bug:
                ChosenTypes.add("bug");
                break;


            case R.id.flying:
                ChosenTypes.add("flying");
                break;


            case R.id.electric:
                ChosenTypes.add("electric");
                break;


            case R.id.dragon:
                ChosenTypes.add("dragon");
                break;


            case R.id.fairy:
                ChosenTypes.add("fairy");
                break;


            case R.id.poison:
                ChosenTypes.add("poison");
                break;


            case R.id.normal:
                ChosenTypes.add("normal");
                break;


            case R.id.ground:
                ChosenTypes.add("ground");
                break;


            case R.id.water:
                ChosenTypes.add("water");
                break;


            case R.id.fire:
                ChosenTypes.add("fire");
                break;


            case R.id.fighting:
                ChosenTypes.add("fighting");
                break;

        }
    }
}
