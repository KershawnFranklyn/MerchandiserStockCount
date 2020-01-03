package com.ecng3020project.merchandiserstockcount;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*Code adapted from https://www.javahelps.com/2015/04/import-and-use-external-database-in.html*/

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseOpenHelper extends SQLiteAssetHelper{

    private static final String DATABASE_NAME = "SMJOnHandStock.db";
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
        Cursor cursor = db.rawQuery("SELECT DISTINCT [Customer Name] FROM SalesData WHERE [Customer Name] LIKE '" + searchTerm + "%' ORDER BY [Customer Name] ASC", null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String objectName = cursor.getString(cursor.getColumnIndex("Customer Name"));
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
        Cursor cursor = db.rawQuery("SELECT DISTINCT CustAcct FROM SalesData WHERE CustAcct LIKE '" + searchTerm + "%'ORDER BY CustAcct ASC", null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String objectName = cursor.getString(cursor.getColumnIndex("CustAcct"));
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
        Cursor cursor = db.rawQuery("SELECT DISTINCT ITEMNAME FROM SalesData WHERE ITEMNAME LIKE '" + searchTerm + "%' ORDER BY ITEMNAME ASC", null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String objectName = cursor.getString(cursor.getColumnIndex("ITEMNAME"));
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
        Cursor cursor = db.rawQuery("SELECT DISTINCT ItemBrand FROM SalesData WHERE ItemBrand LIKE '" + searchTerm + "%' ORDER BY ItemBrand ASC", null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String objectName = cursor.getString(cursor.getColumnIndex("ItemBrand"));
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
        Cursor cursor = db.rawQuery("SELECT DISTINCT ItemPackSize FROM SalesData WHERE ItemPackSize LIKE '" + searchTerm + "%' ORDER BY ItemPackSize ASC", null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String objectName = cursor.getString(cursor.getColumnIndex("ItemPackSize"));
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
        Cursor cursor = db.rawQuery("SELECT DISTINCT Flavor FROM SalesData WHERE Flavor LIKE '" + searchTerm + "%' ORDER BY Flavor ASC", null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String objectName = cursor.getString(cursor.getColumnIndex("Flavor"));
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