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
                "route_No TEXT NOT NULL,customer_Account_No TEXT NOT NULL, customer_Name TEXT    NOT NULL," +
                "item_Name TEXT NOT NULL, item_Brand TEXT NOT NULL, item_Pack_Size TEXT NOT NULL, " +
                "flavor TEXT NOT NULL, number_Of_Cases TEXT NOT NULL )");
    }

    public void createTempDataTable(){
        Log.i(TAG, "createTempDataTableTable: Testing that the Table would be created");

        database.execSQL("CREATE TABLE IF NOT EXISTS TempInputData (Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "route_No TEXT NOT NULL,customer_Account_No TEXT NOT NULL, customer_Name TEXT    NOT NULL," +
                "item_Name TEXT NOT NULL, item_Brand TEXT NOT NULL, item_Pack_Size TEXT NOT NULL, " +
                "flavor TEXT NOT NULL, number_Of_Cases TEXT NOT NULL )");
    }

    //Need to add delete table when finish display results
    public void dropTempTable(){
        database.execSQL("DROP TABLE TempInputData");
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
        contextValues.put("route_No", routeNoVar);
        contextValues.put("customer_Account_No", customerNoVar);
        contextValues.put("customer_Name", custNameVar);
        contextValues.put("item_Name", itemNameVar);
        contextValues.put("item_Brand", itemBrandNameVar);
        contextValues.put("item_Pack_Size", itemPackSizeVar);
        contextValues.put("flavor", flavorVar);
        contextValues.put("number_Of_Cases", numOfCasesVar);

        Long result = database.insert("SavedInputData", null, contextValues );

        if(result == -1)
        {
            return false;
        }
        else {
            return true;
        }

    }

    public Boolean insertTempData(String routeNoVar, String customerNoVar, String custNameVar,
                              String itemNameVar, String itemBrandNameVar, String itemPackSizeVar,
                              String flavorVar, String numOfCasesVar){
        Log.i(TAG, "insertData: Testing that the data is inputted into the database");
        createTable();

        ContentValues contextValues = new ContentValues();
        contextValues.put("route_No", routeNoVar);
        contextValues.put("customer_Account_No", customerNoVar);
        contextValues.put("customer_Name", custNameVar);
        contextValues.put("item_Name", itemNameVar);
        contextValues.put("item_Brand", itemBrandNameVar);
        contextValues.put("item_Pack_Size", itemPackSizeVar);
        contextValues.put("flavor", flavorVar);
        contextValues.put("number_Of_Cases", numOfCasesVar);

        Long result = database.insert("TempInputData", null, contextValues );

        if(result == -1)
        {
            return false;
        }
        else {
            return true;
        }

    }


}
