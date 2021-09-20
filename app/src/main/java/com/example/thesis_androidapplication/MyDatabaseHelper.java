package com.example.thesis_androidapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AuxiliaryApp.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CONTACTS_TABLE_NAME = "user_contacts";
    private static final String CONTACTS_COLUMN_ID = "user_id";
    private static final String CONTACTS_COLUMN_USERNAME = "user_name";
    private static final String CONTACTS_USER_CONTACTS = "user_contact";



    private Context context;

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + CONTACTS_TABLE_NAME +
                " (" + CONTACTS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CONTACTS_COLUMN_USERNAME + " TEXT, " + CONTACTS_USER_CONTACTS + " INTEGER)";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CONTACTS_TABLE_NAME);
        onCreate(db);
    }

    //add contact
    void add_thiscontact(String username, Integer contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();  //initialize content value

        cv. put(CONTACTS_COLUMN_ID, username);
        cv. put(CONTACTS_USER_CONTACTS, contact);
//        Toast.makeText(context, cv.describeContents(), Toast.LENGTH_SHORT).show();

        //insert to db
        long result = db.insert(CONTACTS_TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed to add contact!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Contact added successfully", Toast.LENGTH_SHORT).show();

        }



    }
}
