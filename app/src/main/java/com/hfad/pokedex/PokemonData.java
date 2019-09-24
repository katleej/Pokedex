package com.hfad.pokedex;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class PokemonData {
    public static final class Pokemon implements BaseColumns {
        public static final String DB_NAME = "pokemon";
        public static final String COLUMN_NAME ="name";
        public static final String COLUMN_DEFENSE = "defense";
        public static final String COLUMN_ATTACK = "attack";
        public static final String COLUMN_FLAVOR = "flavor";
        public static final String COLUMN_HEALTH = "health";
        public static final String COLUMN_SPECIAL_ATTACK = "spatk";
        public static final String COLUMN_SPECIAL_DEFENSE = "spdef";
        public static final String COLUMN_SPECIES = "species";
        public static final String COLUMN_SPEED = "speed";
        public static final String COLUMN_TOTAL = "total";
        public static final String COLUMN_TYPE1 = "type1";
        public static final String COLUMN_TYPE2 = "type2";
        public static final String COLUMN_PHOTO = "photo";
    }
}
