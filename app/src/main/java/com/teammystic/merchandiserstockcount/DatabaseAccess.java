package com.teammystic.merchandiserstockcount;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.provider.ContactsContract;
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

    public List<String> getCustomer(){
        List<String> list = new ArrayList<>();
        Log.i(TAG, "getCustomer: Customer Query about to start");
        Cursor cursor = database.rawQuery("SELECT DISTINCT [Customer Name] FROM SalesData", null);
        Log.i(TAG, "getCustomer: Customer Query was done successfully");
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(cursor.getString(cursor.getColumnIndexOrThrow("Customer Name")));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<String> getName(){
        List<String> list = new ArrayList<>();
        Log.i(TAG, "getName: Item Name Query about to start");
        Cursor cursor = database.rawQuery("SELECT DISTINCT ITEMNAME FROM SalesData", null);
        Log.i(TAG, "getName: Item Name Query was done successfully");
        cursor.moveToNext();
        while(!cursor.isAfterLast()){
            list.add(cursor.getString(cursor.getColumnIndexOrThrow("ITEMNAME")));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<String> getBrand(){
        List<String> list = new ArrayList<>();
        Log.i(TAG, "getBrand: Item Brand Query about to start");
        Cursor cursor = database.rawQuery("SELECT DISTINCT ItemBrand FROM SalesData", null);
        Log.i(TAG, "getName: Item Brand Query was done successfully");
        cursor.moveToNext();
        while(!cursor.isAfterLast()){
            list.add(cursor.getString(cursor.getColumnIndexOrThrow("ItemBrand")));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<String> getPack(){
        List<String> list = new ArrayList<>();
        Log.i(TAG, "getPack: Item Pack Size Query about to start");
        Cursor cursor = database.rawQuery("SELECT DISTINCT ItemPackSize FROM SalesData", null);
        Log.i(TAG, "getPack: Item Pack Size Query was done successfully");
        cursor.moveToNext();
        while(!cursor.isAfterLast()){
            list.add(cursor.getString(cursor.getColumnIndexOrThrow("ItemPackSize")));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<String> getFlavor(){
        List<String> list = new ArrayList<>();
        Log.i(TAG, "getFlavor: Item Flavor Query about to start");
        Cursor cursor = database.rawQuery("SELECT DISTINCT ITEMNAME FROM SalesData", null);
        Log.i(TAG, "getFlavor: Item Flavor Query was done successfully");
        cursor.moveToNext();
        while(!cursor.isAfterLast()){
            list.add(cursor.getString(cursor.getColumnIndexOrThrow("Flavor")));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public void insertData(){
        Log.i(TAG, "insertData: Testing that the data is inputed into the database");
        database.rawQuery("INSERT INTO [StockCountSavedData] (Route, CustAcct, " +
                "[Customer Name], ITEMID, ITEMNAME, ItemBrand, ItemPackSize, Flavor) VALUES" +
                "('133', '1005000', 'Test', '55550000', 'Test', 'Test Brand', '300ml', 'test flavor')", null);
    }
}
