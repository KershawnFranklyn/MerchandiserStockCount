package com.ecng3020project.merchandiserstockcount;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/*Code adapted from https://www.javahelps.com/2015/04/import-and-use-external-database-in.html*/

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DatabaseOpenHelper extends SQLiteAssetHelper{

    private static final String DATABASE_NAME = "Project_Data.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, null, DATABASE_VERSION);
    }

/***************************************************************************************
 *    Citation
 *    Title: Android AutocompleteTextView with Database Data as Suggestions
 *    Author: Dalisay, Mike
 *    Date: 2013
 *    Code version: 1.0
 *    Availability: https://www.androidcode.ninja/android-autocompletetextview-example-sqlite-database/
 *    Code Adapted to fit this project
 ***************************************************************************************/

    public List<com.ecng3020project.merchandiserstockcount.MyObject> CustomerNameRead(String searchTerm) {
        List<com.ecng3020project.merchandiserstockcount.MyObject> recordsList = new ArrayList<com.ecng3020project.merchandiserstockcount.MyObject>();

        SQLiteDatabase db = this.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery("SELECT DISTINCT customer_Name FROM Order_Info WHERE customer_Name LIKE '" + searchTerm + "%' ORDER BY customer_Name ASC", null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String objectName = cursor.getString(cursor.getColumnIndex("customer_Name"));
                com.ecng3020project.merchandiserstockcount.MyObject myObject = new com.ecng3020project.merchandiserstockcount.MyObject(objectName);

                // add to list
                recordsList.add(myObject);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // return the list of records
        return recordsList;
    }

    public List<com.ecng3020project.merchandiserstockcount.MyObject> CustomerAccountRead(String searchTerm) {
        List<com.ecng3020project.merchandiserstockcount.MyObject> recordsList = new ArrayList<com.ecng3020project.merchandiserstockcount.MyObject>();

        SQLiteDatabase db = this.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery("SELECT DISTINCT customer_Account_No FROM Order_Info WHERE customer_Account_No LIKE '" + searchTerm + "%'ORDER BY customer_Account_No ASC", null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String objectName = cursor.getString(cursor.getColumnIndex("customer_Account_No"));
                com.ecng3020project.merchandiserstockcount.MyObject myObject = new com.ecng3020project.merchandiserstockcount.MyObject(objectName);

                // add to list
                recordsList.add(myObject);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // return the list of records
        return recordsList;
    }

    public List<com.ecng3020project.merchandiserstockcount.MyObject> ItemNameRead(String searchTerm) {
        List<com.ecng3020project.merchandiserstockcount.MyObject> recordsList = new ArrayList<com.ecng3020project.merchandiserstockcount.MyObject>();

        SQLiteDatabase db = this.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery("SELECT DISTINCT item_Name FROM Order_Info WHERE item_Name LIKE '" + searchTerm + "%' ORDER BY item_Name ASC", null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String objectName = cursor.getString(cursor.getColumnIndex("item_Name"));
                com.ecng3020project.merchandiserstockcount.MyObject myObject = new com.ecng3020project.merchandiserstockcount.MyObject(objectName);

                // add to list
                recordsList.add(myObject);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // return the list of records
        return recordsList;
    }

    public List<com.ecng3020project.merchandiserstockcount.MyObject> ItemBrandRead(String searchTerm) {
        List<com.ecng3020project.merchandiserstockcount.MyObject> recordsList = new ArrayList<com.ecng3020project.merchandiserstockcount.MyObject>();

        SQLiteDatabase db = this.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery("SELECT DISTINCT item_Brand FROM Order_Info WHERE item_Brand LIKE '" + searchTerm + "%' ORDER BY item_Brand ASC", null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String objectName = cursor.getString(cursor.getColumnIndex("item_Brand"));
                com.ecng3020project.merchandiserstockcount.MyObject myObject = new com.ecng3020project.merchandiserstockcount.MyObject(objectName);

                // add to list
                recordsList.add(myObject);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // return the list of records
        return recordsList;
    }

    public List<com.ecng3020project.merchandiserstockcount.MyObject> ItemPackSizeRead(String searchTerm) {
        List<com.ecng3020project.merchandiserstockcount.MyObject> recordsList = new ArrayList<com.ecng3020project.merchandiserstockcount.MyObject>();

        SQLiteDatabase db = this.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery("SELECT DISTINCT item_Pack_Size FROM Order_Info WHERE item_Pack_Size LIKE '" + searchTerm + "%' ORDER BY item_Pack_Size ASC", null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String objectName = cursor.getString(cursor.getColumnIndex("item_Pack_Size"));
                com.ecng3020project.merchandiserstockcount.MyObject myObject = new com.ecng3020project.merchandiserstockcount.MyObject(objectName);

                // add to list
                recordsList.add(myObject);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // return the list of records
        return recordsList;
    }

    public List<com.ecng3020project.merchandiserstockcount.MyObject> ItemFlavorRead(String searchTerm) {
        List<com.ecng3020project.merchandiserstockcount.MyObject> recordsList = new ArrayList<com.ecng3020project.merchandiserstockcount.MyObject>();

        
        SQLiteDatabase db = this.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery("SELECT DISTINCT item_Flavor FROM Order_Info WHERE item_Flavor LIKE '" + searchTerm + "%' ORDER BY item_Flavor ASC", null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String objectName = cursor.getString(cursor.getColumnIndex("item_Flavor"));
                com.ecng3020project.merchandiserstockcount.MyObject myObject = new com.ecng3020project.merchandiserstockcount.MyObject(objectName);

                // add to list
                recordsList.add(myObject);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // return the list of records
        return recordsList;
    }



}