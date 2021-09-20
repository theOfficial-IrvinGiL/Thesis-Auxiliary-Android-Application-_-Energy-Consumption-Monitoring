package com.example.thesis_androidapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AuxiliaryApp.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CONTACTS_TABLE_NAME = "user_contacts";
    private static final String CONTACTS_COLUMN_ID = "user_id";
    private static final String CONTACTS_USERNAME = "user_name";
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
                + CONTACTS_USERNAME + " TEXT, " + CONTACTS_USER_CONTACTS + " INTEGER)";

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

        cv. put(CONTACTS_USER_CONTACTS, contact);
        cv. put(CONTACTS_USERNAME, username);

        //insert to db
        long result = db.insert(CONTACTS_TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed to add contact!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Contact added successfully!", Toast.LENGTH_SHORT).show();

        }
    }


    Cursor readAllData(){
        String query = "SELECT * FROM " + CONTACTS_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;

    }

    void updateData (String row_id, String name, String contactNumber){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();  //initialize content value

        cv. put(CONTACTS_USERNAME, name);
        cv. put(CONTACTS_USER_CONTACTS, contactNumber);

        long result = db.update(CONTACTS_TABLE_NAME, cv, "user_id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to update contact!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Contact updated successfully!", Toast.LENGTH_SHORT).show();
        }


    }
    void deleteContact(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(CONTACTS_TABLE_NAME, "user_id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to delete contact!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Contact deleted successfully!", Toast.LENGTH_SHORT).show();
        }
    }


}
