package com.example.blackhawk.spistelefonow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "phones.db";

    private static final String TABLE_NAME = "wpis";

    private static final String COLUMN_ID = "id_wpisu";

    private static final String COLUMN_IMIE = "imie";

    private static final String COLUMN_NAZWISKO = "nazwisko";

    private static final String COLUMN_NUMER = "numer";


    SQLiteDatabase database;

    public DbHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_IMIE + " TEXT, "+COLUMN_NAZWISKO+" TEXT, "+COLUMN_NUMER+" TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addContact(String imie, String nazwisko, String numer){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IMIE,imie);
        contentValues.put(COLUMN_NAZWISKO,nazwisko);
        contentValues.put(COLUMN_NUMER,numer);
        long result = database.insert(TABLE_NAME,null,contentValues);
        if (result == 1) {
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getData(){
        Cursor data = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public Integer deleteContact(Integer id){
        return database.delete(TABLE_NAME,"id_wpisu=?",new String[] {id.toString()});
    }


}

