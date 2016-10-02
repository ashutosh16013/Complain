package com.ashutosh.iiitd.complain;

/**
 * Created by Ashutosh on 01-10-2016.
 */

import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "complain";
    public static final String CONTACTS_TABLE_NAME = "login";
    public static final String CONTACTS_COLUMN_ID = "First_Name";
    public static final String CONTACTS_COLUMN_NAME = "Last_Name";
    public static final String CONTACTS_COLUMN_EMAIL = "Email";
    public static final String CONTACTS_COLUMN_STREET = "Password";

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table login " +
                        "(First_Name text,Last_Name text,Email text, Password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertContact (String fname, String lname, String email, String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("First_Name", fname);
        contentValues.put("Last_Name", lname);
        contentValues.put("Email", email);
        contentValues.put("Password", pass);
        db.insert("login", null, contentValues);
        return true;
    }

    public Cursor getData(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from login where Email="+email+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public boolean update_user (String fname_old, String fname, String lname)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("First_Name", fname);
        contentValues.put("Last_Name", lname);
        db.update("login", contentValues, "First_Name = ? ", new String[] { fname_old } );
        return true;
    }

    public Integer deleteContact (String fname)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("login",
                "First_Name = ? ",
                new String[] { fname });
    }

    public ArrayList<String> getAllContacts()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select Email from login", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_EMAIL)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAllfirst_name()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select First_Name from login", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_ID)));
            res.moveToNext();
        }
        return array_list;
    }

    //Fetch data for update
    public ArrayList<String> fetch_data(String fname)
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from login where First_Name = '"+fname+"'", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_ID)));
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

    public void del_user(String fname){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("DELETE from login where First_Name = '"+fname+"'",null);
    }

    /*public void update_user(String fname_old, String lname_old, String fname_new,String lname_new){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE record SET no_of_correct_question = ? WHERE no_of_correct_question = " + this.best_no_of_correct,
                new String[] {
                        aa+""
                });
    }*/
}
