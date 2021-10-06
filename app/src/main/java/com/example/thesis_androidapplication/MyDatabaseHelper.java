package com.example.thesis_androidapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AuxiliaryApp.db";
    private static final int DATABASE_VERSION = 1;


    //for contacts database information
    private static final String CONTACTS_TABLE_NAME = "Contacts_Table";
    private static final String CONTACTS_COLUMN_ID = "user_id";
    private static final String CONTACTS_USERNAME = "user_name";
    private static final String CONTACTS_USER_CONTACTS = "user_contact";

    private static final String ENERGY_HISTORY_TABLE = "EnergyHistory_Table";
    private static final String READING_DATE = "reading_date";
    private static final String ENERGY_CONSUMED = "energy_consumed";
    private static final String ENERGY_COST = "energy_cost";
    private static final String BILL_TO_PAY = "bill_to_pay";


    private Context context;

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /*Explanation on why contacts will be saved as text?
        *
        * encountered problem in saving integer because of the datatype memory limit
        * so i made that the contact number will be saved as text and the error trapping
        * will be programmed to detect contact as an integer =irvin.gil=
        * */

        //database for contacts

        /*for database table creation*/
        String[] querries = {
                "CREATE TABLE " + CONTACTS_TABLE_NAME + " (" + CONTACTS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CONTACTS_USERNAME + " TEXT, " + CONTACTS_USER_CONTACTS + " TEXT)",
                "CREATE TABLE " + ENERGY_HISTORY_TABLE + " (" + READING_DATE + " TEXT, " + CONTACTS_USERNAME + " TEXT, " + ENERGY_CONSUMED + " TEXT, " + ENERGY_COST + " TEXT, " + BILL_TO_PAY + " TEXT)"
        };


        /*for loop is used to execute the query on the array above*/
        for(String thisQuery: querries){
            db.execSQL(thisQuery);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        /*for loop is also used to scan and drop the creation of a new table if the tables have already existed
        * this is to avoid the redundant creation of tables in the database*/
        String[] scanTables = {CONTACTS_TABLE_NAME, ENERGY_HISTORY_TABLE};
        for(String executeThis: scanTables){
            db.execSQL("DROP TABLE IF EXISTS " + executeThis);
            onCreate(db);
        }

    }

    //add contact
    void add_thiscontact(String username, String contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();  //initialize content value

        cv. put(CONTACTS_USER_CONTACTS, contact);
        cv. put(CONTACTS_USERNAME, username);

        //insert to db
        long result = db.insert(CONTACTS_TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed to add contact!", Toast.LENGTH_SHORT).show();
        }
       /* else{
            *//*display nothing*//*

        }*/
    }

    /*method to load the search and coordinate with loading the contacts data from
    * the database*/
    Cursor readAllData(){
        String query = "SELECT * FROM " + CONTACTS_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;

    }

    /*method to search for existing contact in the database*/
    Cursor searchDBforExistingContact(String thisContact){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM "+ CONTACTS_TABLE_NAME +" WHERE user_contact = '" + thisContact +"'",
                null);
    }

    /*method for updating the chosen contact (update or edit)*/
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
    /*method for deleting the chosen contact */
    void deleteContact(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(CONTACTS_TABLE_NAME, "user_id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to delete contact!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Contact deleted successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    /*method to search for the contact names and load them into
    * the listview of the update contacts*/
    Cursor loadContactNames_forEnergyUpdate(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT " + CONTACTS_USERNAME + " FROM " + CONTACTS_TABLE_NAME ,  null);
    }

    /*helper method for loading the contact number*/
    Cursor loadUserContactNumber(String user_name){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT user_contact FROM "+ CONTACTS_TABLE_NAME +
                " WHERE " + CONTACTS_USERNAME + " = '" +user_name+"'",  null);
    }

    /*method for updating the data of energy consumption in the database */
    void updateEnergyConsumptionDB(String date, String userName, String energyConsumed, String energyCost, String billToPay){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();  //initialize content value

        cv. put(READING_DATE, date);
        cv. put(CONTACTS_USERNAME, userName);
        cv.put(ENERGY_CONSUMED, energyConsumed);
        cv. put(ENERGY_COST, energyCost);
        cv. put(BILL_TO_PAY, billToPay);

        long result = db.insert(ENERGY_HISTORY_TABLE, null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed update user!", Toast.LENGTH_SHORT).show();

        }
        else{
            /*Toast.makeText(context, "Data save to database", Toast.LENGTH_SHORT).show();*/
            /*do nothing*/
        }


    }


    /*helper method for loading the date of consumption history from the database to the
    * listview*/
    Cursor getHistoryDates(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT DISTINCT " + READING_DATE + " FROM " + ENERGY_HISTORY_TABLE, null);
    }

    /*method for getting the selected date from the energy consumption Table*/
    Cursor getEnergyData(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM "+ ENERGY_HISTORY_TABLE +
                " WHERE " + READING_DATE + " = '" +date+"'",  null);
    }

    /*void deleteContact(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(CONTACTS_TABLE_NAME, "user_id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to delete contact!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Contact deleted successfully!", Toast.LENGTH_SHORT).show();
        }
    }*/

    /*method for deleting the energy consumption data from the database*/
    void deleteEnergyData_withDate(String date){
        SQLiteDatabase db = getWritableDatabase();
        /* db.rawQuery("DELETE FROM " + ENERGY_HISTORY_TABLE + " WHERE " + READING_DATE + " = '"+date+"'", null);*/

        long result = db.delete(ENERGY_HISTORY_TABLE, "reading_date=?", new String[]{date});
        if (result == -1){
            Toast.makeText(context, "Failed to delete contact!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Data history deleted successfully!", Toast.LENGTH_SHORT).show();
        }
db.close();

    }




}
