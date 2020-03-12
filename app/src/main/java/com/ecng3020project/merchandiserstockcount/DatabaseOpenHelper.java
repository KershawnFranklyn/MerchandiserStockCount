package com.ecng3020project.merchandiserstockcount;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/*Code adapted from https://www.javahelps.com/2015/04/import-and-use-external-database-in.html*/

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.lang.reflect.Array;
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
    public List<com.ecng3020project.merchandiserstockcount.MyObject> RouteNumberRead(String searchTerm){
        List<com.ecng3020project.merchandiserstockcount.MyObject> recordsList = new ArrayList<MyObject>();

        SQLiteDatabase db = this.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery("SELECT DISTINCT route_No FROM Customer_Info WHERE route_No LIKE '" + searchTerm + "%' ORDER BY route_No ASC", null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String objectName = cursor.getString(cursor.getColumnIndex("route_No"));
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


    public List<com.ecng3020project.merchandiserstockcount.MyObject> CustomerNameRead(String searchTerm) {
        List<com.ecng3020project.merchandiserstockcount.MyObject> recordsList = new ArrayList<com.ecng3020project.merchandiserstockcount.MyObject>();

        SQLiteDatabase db = this.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery("SELECT DISTINCT customer_Name FROM Customer_Info WHERE customer_Name LIKE '" + searchTerm + "%' ORDER BY customer_Name ASC", null);

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
        Cursor cursor = db.rawQuery("SELECT DISTINCT customer_Account_No FROM Customer_Info WHERE customer_Account_No LIKE '" + searchTerm + "%'ORDER BY customer_Account_No ASC", null);

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
        Cursor cursor = db.rawQuery("SELECT DISTINCT item_Name FROM Item_Info WHERE item_Name LIKE '" + searchTerm + "%' ORDER BY item_Name ASC", null);

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
        Cursor cursor = db.rawQuery("SELECT DISTINCT item_Brand FROM Item_Info WHERE item_Brand LIKE '" + searchTerm + "%' ORDER BY item_Brand ASC", null);

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
        Cursor cursor = db.rawQuery("SELECT DISTINCT item_Pack_Size FROM Item_Info WHERE item_Pack_Size LIKE '" + searchTerm + "%' ORDER BY item_Pack_Size ASC", null);

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
        Cursor cursor = db.rawQuery("SELECT DISTINCT flavor FROM Item_Info WHERE flavor LIKE '" + searchTerm + "%' ORDER BY flavor ASC", null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String objectName = cursor.getString(cursor.getColumnIndex("flavor"));
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

    public List<com.ecng3020project.merchandiserstockcount.MyObject> BarcodeIDtoItemID(String barcodeIDString){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT DISTINCT item_ID FROM Barcode_Info WHERE barcode_ID LIKE '"+barcodeIDString+"'",  null);
        //Cursor cursor = db.rawQuery("SELECT DISTINCT item_Name FROM Item_Info WHERE item_ID LIKE '1'",  null);
        List<com.ecng3020project.merchandiserstockcount.MyObject> itemIDList = new ArrayList<com.ecng3020project.merchandiserstockcount.MyObject>();

        if (cursor.moveToFirst()){
            do {
                String objectItemID = cursor.getString(cursor.getColumnIndex("item_ID"));
                com.ecng3020project.merchandiserstockcount.MyObject itemIDObject = new com.ecng3020project.merchandiserstockcount.MyObject(objectItemID);
                itemIDList.add(itemIDObject);

            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return itemIDList;
    }

    public List<com.ecng3020project.merchandiserstockcount.MyObject> ItemNameScannedQuery(String itemIDString){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT DISTINCT item_Name FROM Item_Info WHERE item_ID LIKE '"+itemIDString+"'",  null);
        //Cursor cursor = db.rawQuery("SELECT DISTINCT item_Name FROM Item_Info WHERE item_ID LIKE '1'",  null);
        List<com.ecng3020project.merchandiserstockcount.MyObject> itemNameList = new ArrayList<com.ecng3020project.merchandiserstockcount.MyObject>();

        if (cursor.moveToFirst()){
            do {
                String objectItemName = cursor.getString(cursor.getColumnIndex("item_Name"));
                com.ecng3020project.merchandiserstockcount.MyObject itemNameObject = new com.ecng3020project.merchandiserstockcount.MyObject(objectItemName);
                itemNameList.add(itemNameObject);

            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return itemNameList;
    }

    public List<com.ecng3020project.merchandiserstockcount.MyObject> ItemPackSizeScannedQuery(String itemIDString){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT DISTINCT item_Pack_Size FROM Item_Info WHERE item_ID LIKE '"+itemIDString+"'", null);
        //Cursor cursor = db.rawQuery("SELECT DISTINCT item_Pack_Size FROM Item_Info WHERE item_ID LIKE '1'", null);
        List<com.ecng3020project.merchandiserstockcount.MyObject> itemBrandList = new ArrayList<MyObject>();

        if(cursor.moveToFirst()){
            do{
                String objectItemBrand = cursor.getString(cursor.getColumnIndex("item_Pack_Size"));
                com.ecng3020project.merchandiserstockcount.MyObject itemBrandObject = new com.ecng3020project.merchandiserstockcount.MyObject(objectItemBrand);
                itemBrandList.add(itemBrandObject);
            }
            while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        return itemBrandList;
    }

    public List<com.ecng3020project.merchandiserstockcount.MyObject> ItemBrandScannedQuery(String itemIDString){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT DISTINCT item_Brand FROM Item_Info WHERE item_ID LIKE '"+itemIDString+"'", null);
        //Cursor cursor = db.rawQuery("SELECT DISTINCT item_Brand FROM Item_Info WHERE item_ID LIKE '1'", null);
        List<com.ecng3020project.merchandiserstockcount.MyObject> itemPackSizeList = new ArrayList<MyObject>();

        if(cursor.moveToFirst()){
            do{
                String objectItemPackSize = cursor.getString(cursor.getColumnIndex("item_Brand"));
                com.ecng3020project.merchandiserstockcount.MyObject itemPackSizeObject = new com.ecng3020project.merchandiserstockcount.MyObject(objectItemPackSize);
                itemPackSizeList.add(itemPackSizeObject);
            }
            while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        return itemPackSizeList;
    }

    public List<com.ecng3020project.merchandiserstockcount.MyObject> ItemFlavorScannedQuery(String itemIDString){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT DISTINCT flavor FROM Item_Info WHERE item_ID LIKE '"+itemIDString+"'", null);
        //Cursor cursor = db.rawQuery("SELECT DISTINCT flavor FROM Item_Info WHERE item_ID LIKE '1'", null);
        List<com.ecng3020project.merchandiserstockcount.MyObject> itemFlavorList = new ArrayList<MyObject>();

        if(cursor.moveToFirst()){
            do{
                String objectItemFlavor = cursor.getString(cursor.getColumnIndex("flavor"));
                com.ecng3020project.merchandiserstockcount.MyObject itemPackSizeObject = new com.ecng3020project.merchandiserstockcount.MyObject(objectItemFlavor);
                itemFlavorList.add(itemPackSizeObject);
            }
            while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        return itemFlavorList;
    }

    public List<com.ecng3020project.merchandiserstockcount.MyObject> CustomerNameTypedQuery(String customerAccountNo){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT DISTINCT customer_Name FROM Customer_Info WHERE customer_Account_No LIKE '"+customerAccountNo+"'",  null);
        List<com.ecng3020project.merchandiserstockcount.MyObject> customerNameList = new ArrayList<com.ecng3020project.merchandiserstockcount.MyObject>();

        if (cursor.moveToFirst()){
            do {
                String objectItemName = cursor.getString(cursor.getColumnIndex("customer_Name"));
                com.ecng3020project.merchandiserstockcount.MyObject customerNameObject = new com.ecng3020project.merchandiserstockcount.MyObject(objectItemName);
                customerNameList.add(customerNameObject);

            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return customerNameList;
    }

    //Function used to query for result using the chosen customer name. This is for the autocomplete funciton
    public List<com.ecng3020project.merchandiserstockcount.MyObject> CustomerAccountTypedQuery(String customerName){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT DISTINCT customer_Account_No FROM Customer_Info WHERE customer_Name LIKE '"+customerName+"'",  null);
        List<com.ecng3020project.merchandiserstockcount.MyObject> customerAccountNoList = new ArrayList<com.ecng3020project.merchandiserstockcount.MyObject>();

        if (cursor.moveToFirst()){
            do {
                String objectItemName = cursor.getString(cursor.getColumnIndex("customer_Account_No"));
                com.ecng3020project.merchandiserstockcount.MyObject customerAccountNoObject = new com.ecng3020project.merchandiserstockcount.MyObject(objectItemName);
                customerAccountNoList.add(customerAccountNoObject);

            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return customerAccountNoList;
    }

    public List<com.ecng3020project.merchandiserstockcount.MyObject> ItemBrandTypedQuery(String itemName){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT DISTINCT item_Brand FROM Item_Info WHERE item_Name LIKE '"+itemName+"'",  null);
        List<com.ecng3020project.merchandiserstockcount.MyObject> itemBrandList = new ArrayList<com.ecng3020project.merchandiserstockcount.MyObject>();

        if (cursor.moveToFirst()){
            do {
                String objectItemBrand = cursor.getString(cursor.getColumnIndex("item_Brand"));
                com.ecng3020project.merchandiserstockcount.MyObject itemBrandObject = new com.ecng3020project.merchandiserstockcount.MyObject(objectItemBrand);
                itemBrandList.add(itemBrandObject);

            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return itemBrandList;
    }

    public List<com.ecng3020project.merchandiserstockcount.MyObject> ItemPackSizeTypedQuery(String itemName){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT DISTINCT item_Pack_Size FROM Item_Info WHERE item_Name LIKE '"+itemName+"'",  null);
        List<com.ecng3020project.merchandiserstockcount.MyObject> itemPackSizeList = new ArrayList<com.ecng3020project.merchandiserstockcount.MyObject>();

        if (cursor.moveToFirst()){
            do {
                String objectItemPackSize = cursor.getString(cursor.getColumnIndex("item_Pack_Size"));
                com.ecng3020project.merchandiserstockcount.MyObject itemPackSizeObject = new com.ecng3020project.merchandiserstockcount.MyObject(objectItemPackSize);
                itemPackSizeList.add(itemPackSizeObject);

            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return itemPackSizeList;
    }

    public List<com.ecng3020project.merchandiserstockcount.MyObject> ItemFlavorTypedQuery(String itemName){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT DISTINCT flavor FROM Item_Info WHERE item_Name LIKE '"+itemName+"'",  null);
        List<com.ecng3020project.merchandiserstockcount.MyObject> itemFlavorList = new ArrayList<com.ecng3020project.merchandiserstockcount.MyObject>();

        if (cursor.moveToFirst()){
            do {
                String objectItemFlavor = cursor.getString(cursor.getColumnIndex("flavor"));
                com.ecng3020project.merchandiserstockcount.MyObject itemFlavorObject = new com.ecng3020project.merchandiserstockcount.MyObject(objectItemFlavor);
                itemFlavorList.add(itemFlavorObject);

            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return itemFlavorList;
    }

    public List<com.ecng3020project.merchandiserstockcount.MyObject> listing_ItemNames(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT item_Name FROM TempInputData", null);
        List<com.ecng3020project.merchandiserstockcount.MyObject> itemNameList = new ArrayList<MyObject>();

        if(cursor.moveToFirst()){
            do{
                String objectItemName = cursor.getString(cursor.getColumnIndex("item_Name"));
                com.ecng3020project.merchandiserstockcount.MyObject itemNameObject = new com.ecng3020project.merchandiserstockcount.MyObject(objectItemName);
                itemNameList.add(itemNameObject);
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return itemNameList;
    }

}