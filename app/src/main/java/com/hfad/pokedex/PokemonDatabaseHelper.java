package com.hfad.pokedex;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Key;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class PokemonDatabaseHelper extends SQLiteOpenHelper {
    private Resources mResources;
    private static final String DATABASE_NAME = "pokemon.db";
    private static final int DATABASE_VERSION = 1;
    Context context;
    public static SQLiteDatabase db;


    public PokemonDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        mResources = context.getResources();
//        db = this.getWritableDatabase();
        try {
           update();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String create = "CREATE TABLE " + PokemonData.Pokemon.DB_NAME + " (" +
                PokemonData.Pokemon._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                PokemonData.Pokemon.COLUMN_NAME + " TEXT UNIQUE NOT NULL, " +
                PokemonData.Pokemon.COLUMN_DEFENSE + " INTEGER NOT NULL, " +
                PokemonData.Pokemon.COLUMN_FLAVOR + " TEXT NOT NULL, " +
                PokemonData.Pokemon.COLUMN_HEALTH + " INTEGER NOT NULL, " +
                PokemonData.Pokemon.COLUMN_SPECIAL_ATTACK + " INTEGER NOT NULL, " +
                PokemonData.Pokemon.COLUMN_SPECIAL_DEFENSE + " INTEGER NOT NULL, " +
                PokemonData.Pokemon.COLUMN_SPECIES + " TEXT NOT NULL, " +
                PokemonData.Pokemon.COLUMN_SPEED + " INTEGER NOT NULL, " +
                PokemonData.Pokemon.COLUMN_TOTAL + " INTEGER NOT NULL, " +
                PokemonData.Pokemon.COLUMN_TYPE1 + " TEXT NOT NULL, " +
                PokemonData.Pokemon.COLUMN_TYPE2 + " TEXT NOT NULL " + " );";
        db.execSQL(create);


        try {
            readDataToDb(db);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void update() throws IOException, JSONException{
        try {
            String jsonDataString = readJsonDataFromFile();
            JSONObject pokemonObject = new JSONObject(jsonDataString);
            Iterator iterator = pokemonObject.keys();

            while (iterator.hasNext()) {
                //fetch data from individual pokemon
                String key =  (String) iterator.next();
                JSONObject value = pokemonObject.getJSONObject(key);
                String attack = value.getString("Attack");
                String defense = value.getString("Defense");
                String flavor = value.getString("FlavorText");
                String health = value.getString("HP");
                String special_attack = value.getString("Sp. Atk");
                String special_defense = value.getString("Sp. Def");
                String species = value.getString("Species");
                String speed = value.getString("Speed");
                String total = value.getString("Total");
                String type1 = value.getJSONArray("Type").get(0).toString();
                String type2 = "";
                if (value.getJSONArray("Type").length() > 1) {
                    type2 = value.getJSONArray("Type").get(1).toString();
                }


                ContentValues pokemonValues = new ContentValues();

                //insert into db
                pokemonValues.put(PokemonData.Pokemon.COLUMN_NAME, key);
                pokemonValues.put(PokemonData.Pokemon.COLUMN_DEFENSE, defense);
                pokemonValues.put(PokemonData.Pokemon.COLUMN_FLAVOR, flavor);
                pokemonValues.put(PokemonData.Pokemon.COLUMN_HEALTH, health);
                pokemonValues.put(PokemonData.Pokemon.COLUMN_ATTACK, attack);
                pokemonValues.put(PokemonData.Pokemon.COLUMN_SPECIAL_ATTACK, special_attack);
                pokemonValues.put(PokemonData.Pokemon.COLUMN_SPECIAL_DEFENSE, special_defense);
                pokemonValues.put(PokemonData.Pokemon.COLUMN_SPECIES, species);
                pokemonValues.put(PokemonData.Pokemon.COLUMN_SPEED, speed);
                pokemonValues.put(PokemonData.Pokemon.COLUMN_TOTAL, total);
                pokemonValues.put(PokemonData.Pokemon.COLUMN_TYPE1, type1);
                pokemonValues.put(PokemonData.Pokemon.COLUMN_TYPE2, type2);

                db.insert(PokemonData.Pokemon.DB_NAME, null, pokemonValues);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void readDataToDb(SQLiteDatabase db) throws IOException, JSONException {

        try {
            String jsonDataString = readJsonDataFromFile();
            JSONObject pokemonObject = new JSONObject(jsonDataString);

            while (pokemonObject.keys().hasNext()) {
                //fetch data from individual pokemon
                String key = pokemonObject.keys().next();
                JSONObject value = pokemonObject.getJSONObject(key);
                String attack = value.getString("Attack");
                String defense = value.getString("Defense");
                String flavor = value.getString("FlavorText");
                String health = value.getString("HP");
                String special_attack = value.getString("Sp.Atk");
                String special_defense = value.getString("Sp.Def");
                String species = value.getString("Species");
                String speed = value.getString("Speed");
                String total = value.getString("Total");
                String type1 = value.getJSONArray("Type").get(0).toString();
                String type2 = "-";
                if (value.getJSONArray("Type").length() > 1) {
                    type2 = value.getJSONArray("Type").get(1).toString();
                }


                ContentValues pokemonValues = new ContentValues();

                //insert into db
                pokemonValues.put(PokemonData.Pokemon.COLUMN_NAME, key);
                pokemonValues.put(PokemonData.Pokemon.COLUMN_DEFENSE, defense);
                pokemonValues.put(PokemonData.Pokemon.COLUMN_FLAVOR, flavor);
                pokemonValues.put(PokemonData.Pokemon.COLUMN_HEALTH, health);
                pokemonValues.put(PokemonData.Pokemon.COLUMN_ATTACK, attack);
                pokemonValues.put(PokemonData.Pokemon.COLUMN_SPECIAL_ATTACK, special_attack);
                pokemonValues.put(PokemonData.Pokemon.COLUMN_SPECIAL_DEFENSE, special_defense);
                pokemonValues.put(PokemonData.Pokemon.COLUMN_SPECIES, species);
                pokemonValues.put(PokemonData.Pokemon.COLUMN_SPEED, speed);
                pokemonValues.put(PokemonData.Pokemon.COLUMN_TOTAL, total);
                pokemonValues.put(PokemonData.Pokemon.COLUMN_TYPE1, type1);
                pokemonValues.put(PokemonData.Pokemon.COLUMN_TYPE2, type2);

                db.insert(PokemonData.Pokemon.DB_NAME, null, pokemonValues);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private String readJsonDataFromFile() throws IOException {
        InputStream inputStream = null;
        String data = "";

        try {
            inputStream = mResources.openRawResource(R.raw.pokedata);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));
            data = bufferedReader.readLine();

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return data;
    }
}
