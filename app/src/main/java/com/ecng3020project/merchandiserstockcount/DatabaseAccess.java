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
    DatabaseOpenHelper databaseOpenHelper;

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
        createTempDataTable();

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

    public Boolean insertReadBarcodeCloudData(String barcode_ID_String, String item_ID_String, String warehouse_String,
                                          String expiry_Date_String, String unit_Of_Measure_String, String batch_Number_String ){

            Long barcode_ID_Long = Long.valueOf(barcode_ID_String);

            ContentValues contentValues = new ContentValues();
            contentValues.put("barcode_ID", barcode_ID_Long);
            contentValues.put("item_ID", item_ID_String);
            contentValues.put("warehouse", warehouse_String);
            contentValues.put("expiry_Date", expiry_Date_String);
            contentValues.put("unit_Of_Measure", unit_Of_Measure_String);
            contentValues.put("batch_No", batch_Number_String);

            Long result = database.insert("Barcode_Info", null, contentValues);

            if(result == -1){
                Log.i(TAG, "insertReadBarcodeCloudData: Unsuccessfully entered barcode data");
                return false;
            }
            else {
                Log.i(TAG, "insertReadBarcodeCloudData: Successfully entered barcode data");
                return true;
            }
    }

    public Boolean insertReadCustomerCloudData(String customer_Account_No_String, String route_No_String, String customer_Name_String ){

        Long customer_Account_No_Long = Long.valueOf(customer_Account_No_String);

        ContentValues contentValues = new ContentValues();
        contentValues.put("customer_Account_No", customer_Account_No_Long);
        contentValues.put("route_No", route_No_String);
        contentValues.put("customer_Name", customer_Name_String);

        Long result = database.insert("Customer_Info", null, contentValues);

        if(result == -1){
            Log.i(TAG, "insertReadCustomerCloudData: Unsuccessfully entered barcode data");
            return false;
        }
        else {
            Log.i(TAG, "insertReadCustomerCloudData: Successfully entered barcode data");
            return true;
        }
    }

    public Boolean insertReadItemCloudData(String item_ID_String, String item_Brand_String, String item_Pack_Size_String,
                                              String flavor_String, String item_Name_String){

        Long item_ID_Long = Long.valueOf(item_ID_String);

        ContentValues contentValues = new ContentValues();
        contentValues.put("item_ID", item_ID_Long);
        contentValues.put("item_Brand", item_Brand_String);
        contentValues.put("item_Pack_Size", item_Pack_Size_String);
        contentValues.put("flavor", flavor_String);
        contentValues.put("item_Name", item_Name_String);

        Long result = database.insert("Item_Info", null, contentValues);

        if(result == -1){
            Log.i(TAG, "insertReadBarcodeItemData: Unsuccessfully entered barcode data");
            return false;
        }
        else {
            Log.i(TAG, "insertReadBarcodeItemData: Successfully entered barcode data");
            return true;
        }
    }

    public Boolean insertReadOrderCloudData(String order_ID_String, String order_Date_String, String customer_Account_No_String) {

        Long order_ID_Long = Long.valueOf(order_ID_String);

        ContentValues contentValues = new ContentValues();
        contentValues.put("order_ID", order_ID_Long);
        contentValues.put("order_Date", order_Date_String);
        contentValues.put("customer_Account_No", customer_Account_No_String);

        Long result = database.insert("Order_Info", null, contentValues);

        if (result == -1) {
            Log.i(TAG, "insertReadBarcodeOrderData: Unsuccessfully entered barcode data");
            return false;
        } else {
            Log.i(TAG, "insertReadBarcodeOrderData: Successfully entered barcode data");
            return true;
        }
    }

    public Boolean insertReadOrderLineCloudData(String order_ID_String, String item_ID_String, String no_Of_Cases_String){

        Long order_ID_Long = Long.valueOf(order_ID_String);
        Long item_ID_Long = Long.valueOf(item_ID_String);
        Long no_Of_Cases_Long = Long.valueOf(no_Of_Cases_String);

        ContentValues contentValues = new ContentValues();
        contentValues.put("order_ID", order_ID_Long);
        contentValues.put("item_ID", item_ID_Long);
        contentValues.put("no_Of_Cases", no_Of_Cases_Long);

        Long result = database.insert("Order_Line", null, contentValues);

        if(result == -1){
            Log.i(TAG, "insertReadOrderLineCloudData: Unsuccessfully entered barcode data");
            return false;
        }
        else {
            Log.i(TAG, "insertReadOrderLineCloudData: Successfully entered barcode data");
            return true;
        }
    }

}
