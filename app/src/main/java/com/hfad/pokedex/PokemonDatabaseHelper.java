package com.hfad.pokedex;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

public class PokemonDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = PokemonDatabaseHelper.class.getSimpleName();

    private Resources mResources;
    private static final String DATABASE_NAME = "menu.db";
    private static final int DATABASE_VERSION = 1;
    Context context;
    SQLiteDatabase db;


    public PokemonDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mResources = context.getResources();
        db = this.getReadableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        final String create = "CREATE TABLE " + PokemonData.Pokemon.DB_NAME + " (" +
                PokemonData.Pokemon._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                PokemonData.Pokemon.COLUMN_NAME + " TEXT UNIQUE NOT NULL, " +
                PokemonData.Pokemon.COLUMN_NUM + " INTEGER NOT NULL, " +
                PokemonData.Pokemon.COLUMN_DEFENSE + " INTEGER NOT NULL, " +
                PokemonData.Pokemon.COLUMN_FLAVOR + " TEXT NOT NULL, " +
                PokemonData.Pokemon.COLUMN_HEALTH + " INTEGER NOT NULL, " +
                PokemonData.Pokemon.COLUMN_SPECIAL_ATTACK + " INTEGER NOT NULL, " +
                PokemonData.Pokemon.COLUMN_SPECIAL_DEFENSE + " INTEGER NOT NULL, " +
                PokemonData.Pokemon.COLUMN_SPECIES + " TEXT NOT NULL, " +
                PokemonData.Pokemon.COLUMN_SPEED + " INTEGER NOT NULL, " +
                PokemonData.Pokemon.COLUMN_TOTAL + " INTEGER NOT NULL, " +
                PokemonData.Pokemon.COLUMN_TYPE1 + " TEXT NOT NULL, " +
                PokemonData.Pokemon.COLUMN_TYPE2 + "TEXT NOT NULL " + " );";
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

    private void readDataToDb(SQLiteDatabase db) throws IOException, JSONException {

        try {
            String jsonDataString = readJsonDataFromFile();
            JSONArray pokemonJsonArray = new JSONArray(jsonDataString);

            for (int i = 0; i < pokemonJsonArray.length(); ++i) {

                JSONObject PokemonObject = pokemonJsonArray.getJSONObject(i);
                String name = PokemonObject.keys().next();
                String attack = PokemonObject.getString("Attack");
                String defense = PokemonObject.getString("Defense");
                String flavor = PokemonObject.getString("FlavorText");
                String health = PokemonObject.getString("HP");
                String special_attack = PokemonObject.getString("Sp.Atk");
                String special_defense = PokemonObject.getString("SP.Def");
                String species = PokemonObject.getString("Species");
                String speed = PokemonObject.getString("Speed");
                String total = PokemonObject.getString("Total");
                String type1 = PokemonObject.getJSONArray("Type").get(0).toString();
                String type2 = "-";
                if (PokemonObject.getJSONArray("Type").length() > 1) {
                    type2 = PokemonObject.getJSONArray("Type").get(1).toString();
                }


                ContentValues pokemonValues = new ContentValues();

                pokemonValues.put(PokemonData.Pokemon.COLUMN_NAME, name);
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


                Log.d(TAG, "Inserted Successfully " + pokemonValues);
            }


        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
            e.printStackTrace();
        }

    }

    private String readJsonDataFromFile() throws IOException {
//        FileReader file = new FileReader(R.raw.pokedata);
//        jsonObject = new JSONObject(contents.trim());
//        Iterator<String> keys = jsonObject.keys();
//
//        while(keys.hasNext()) {
//            String key = keys.next();
//            if (jsonObject.get(key) instanceof JSONObject) {
//                // do something with jsonObject here
//            }
//        }

        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {
            String jsonDataString = null;
            inputStream = mResources.openRawResource(R.raw.pokedata);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));
            while ((jsonDataString = bufferedReader.readLine()) != null) {
                builder.append(jsonDataString);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return new String(builder);
    }
}
