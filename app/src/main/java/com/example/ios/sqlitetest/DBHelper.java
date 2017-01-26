package com.example.ios.sqlitetest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by in.lee on 2017. 1. 13..
 */

public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper instance;

    public static DBHelper getInstance(Context context)  {
        if (instance == null)  {
            instance = new DBHelper(context, "MoneyBook.db", null, 1);
        }
        return instance;
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE MONEYBOOK(_id INTEGER PRIMARY KEY AUTOINCREMENT, item TEXT, price INTEGER, create_at TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String create_at, String item, int price)  {
        SQLiteDatabase db = instance.getWritableDatabase();
        db.execSQL("INSERT INTO MONEYBOOK VALUES(null, '" + item + "', " + price + ", '" + create_at + "');");
        db.close();
    }

    public void update(String item, int price)  {
        SQLiteDatabase db = instance.getWritableDatabase();
        db.execSQL(" UPDATE MONEYBOOK SET price=" + price + " WHERE item='" + item + "';");
        db.close();
    }

    public void delete(String item)  {
        SQLiteDatabase db = instance.getWritableDatabase();
        db.execSQL("DELETE FROM MONEYBOOK WHERE item='"+item+"';");
        db.close();
    }

    public String getResult()  {
        SQLiteDatabase db = instance.getWritableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery("SELECT * FROM MONEYBOOK", null);
        while (cursor.moveToNext())  {
            result += cursor.getString(0)
                    + " : "
                    + cursor.getString(1)
                    + " | "
                    + cursor.getString(2)
                    + "Won"
                    + cursor.getString(3)
                    + "\n";
        }
        db.close();
        return result;
    }
}
