package com.example.thesis_androidapplication;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;


public class theDatabase extends SQLiteOpenHelper {

    public static final String DatabaseName = "Database Information";
    public static final String TableName = "TBL_Information";

    public static final String Col_1 = "User ID";
    public static final String Col_2 = "KWH";
    public static final String Col_3 = "AMOUNT";

    public theDatabase(Context context) {
        super(context, DatabaseName, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TableName + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, KWH TEXT, AMOUNT TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);

    }

    // inserting
    public boolean insertingInfo(String kwh, String amount) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2, kwh);
        contentValues.put(Col_3, amount);
        long result = sqLiteDatabase.insert(TableName, null, contentValues);
        sqLiteDatabase.close();

        // check kung na insert bag data
        return result != -1;

    }

    // Reading
    public Cursor getAllInfo(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery (" Select * from " + TableName, null);

    }
    // Pag update
    public boolean updateInfo (String user, String kwh, String amount){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(Col_2, kwh);
    contentValues.put(Col_3, amount);
    int result = db.update(TableName, contentValues, "USER=?", new String[]{user});
    return result > 0;

    }
    // delete
    public Integer deleteInfo(String user){
    SQLiteDatabase db = this.getWritableDatabase();
    return db.delete(TableName, "USER=?", new String[]{user});
    }
}



