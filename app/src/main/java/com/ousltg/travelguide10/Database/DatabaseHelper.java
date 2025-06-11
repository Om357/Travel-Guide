package com.ousltg.travelguide10.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//Define database name, table names and column names
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="Todolist.db";
    public static final String TABLE_NAME ="list_table";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="TITLE";
    public static final String COL_3 ="LINE2";

    //DatabaseHelper constructor
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //onCreate() method with SQL query
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"TITLE TEXT, LINE2 TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
    //insertData() method
    public boolean insertData(String title,String line2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contextValues = new ContentValues();
        contextValues.put(COL_2,title);
        contextValues.put(COL_3,line2);
        long results = db.insert(TABLE_NAME,null,contextValues);
        if (results == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("select * from "+ TABLE_NAME,null);
        return results;
    }

    //Implement updateData() method

    public boolean updateData(String id, String title, String line2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,title);
        contentValues.put(COL_3,line2);
        db.update(TABLE_NAME,contentValues,"id = ?",new String[]{id});
        return true;
    }

    ////Implement deleteData() method
    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[]{id});
    }
}
