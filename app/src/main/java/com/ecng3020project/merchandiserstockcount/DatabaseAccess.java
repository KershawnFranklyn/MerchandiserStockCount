package com.ecng3020project.merchandiserstockcount;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

/*Code adapted from https://www.javahelps.com/2015/04/import-and-use-external-database-in.html*/

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }

    static DatabaseAccess getInstance(Context context){
        if(instance == null){
           instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open(){
        Log.i(TAG, "open: The Database is opened");
        this.database = openHelper.getWritableDatabase();
    }

    public void close(){
        if(database != null) {
            Log.i(TAG, "close: The Database is closed");
            this.database.close();
        }
    }

    public void createTable(){
        Log.i(TAG, "createTable: Testing that the Table would be created");

        database.execSQL("CREATE TABLE IF NOT EXISTS SavedInputData (Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "Route TEXT NOT NULL,CustAcct TEXT NOT NULL, [Customer Name] TEXT    NOT NULL," +
                "ITEMNAME TEXT NOT NULL, ItemBrand TEXT NOT NULL, ItemPackSize TEXT NOT NULL, " +
                "Flavor TEXT NOT NULL, NumberOfCases TEXT NOT NULL )");
    }

    /***************************************************************************************
     *    Title: Android SQLite Database Tutorial (Select, Insert, Update, Delete)
     *    Author: Singh, Mithilesh
     *    Date: 2017
     *    Code version: 1.0
     *    Availability: http://www.codebind.com/android-tutorials-and-examples/android-sqlite-tutorial-example/
     *    Code Adapted to fit this project
     ***************************************************************************************/

    public Boolean insertData(String routeNoVar, String customerNoVar, String custNameVar,
                              String itemNameVar, String itemBrandNameVar, String itemPackSizeVar,
                              String flavorVar, String numOfCasesVar){
        Log.i(TAG, "insertData: Testing that the data is inputted into the database");
        createTable();

        ContentValues contextValues = new ContentValues();
        contextValues.put("Route", routeNoVar);
        contextValues.put("CustAcct", customerNoVar);
        contextValues.put("[Customer Name]", custNameVar);
        contextValues.put("ITEMNAME", itemNameVar);
        contextValues.put("ItemBrand", itemBrandNameVar);
        contextValues.put("ItemPackSize", itemPackSizeVar);
        contextValues.put("Flavor", flavorVar);
        contextValues.put("NumberOfCases", numOfCasesVar);

        Long result = database.insert("SavedInputData", null, contextValues );


        if(result == -1)
        {
            return false;
        }
        else {
            return true;
        }

    }

}
