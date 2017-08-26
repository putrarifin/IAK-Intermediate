package com.arifsaputra.iak_intermediate.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Chyrus on 8/26/17.
 * Muh Arif Saputra (Android Developer)
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHandler";

    //database version
    private static final int DATABASE_VERSION = 1;

    //database name
    private static final String DATABASE_NAME = "moviesManager";

    //Movies table name
    private static final String TABLE_MOVIES = "movies";

    //movies table column names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_FAVORITE = "favorite";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MOVIES_TABLE = "CREATE TABLE "+TABLE_MOVIES+" " +
                "("+KEY_ID+" INTEGER PRIMARY KEY, "+KEY_TITLE+" TEXT,"+KEY_FAVORITE+" TEXT ) ";

//        String SQL = "CREATE TABLE _____  ( _____  INTEGER PRIMARY KEY ,      )   ";

        Log.d(TAG, "onCreate: table movie create:" +CREATE_MOVIES_TABLE);
        db.execSQL(CREATE_MOVIES_TABLE);
    }



    //upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //hapus table lama jika ada
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_MOVIES);
        //buat kembali table
        onCreate(db);
    }
}
