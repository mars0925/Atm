package com.mars.atm.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ExpenseHelp extends SQLiteOpenHelper {
    private String createTable = "CREATE TABLE expense (_id INTEGER PRIMARY KEY NOT NULL,cdate VARCHAR NOT NULL, info VARCHAR,amount INTERGER)";

    public ExpenseHelp(Context context){
        this(context,"atm.db",null,1);
    }

    private ExpenseHelp( Context context, String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
