package com.teammystic.merchandiserstockcount;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/*Code adapted from https://www.javahelps.com/2015/04/import-and-use-external-database-in.html*/

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelper extends SQLiteAssetHelper{

    private static final String DATABASE_NAME = "SMJOnHandStock.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, null, DATABASE_VERSION);
    }
}