package com.teammystic.merchandiserstockcount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.net.Inet4Address;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Test";
    /***************************************************************************************
 *    Title: Android AutocompleteTextView with Database Data as Suggestions
 *    Author: Dalisay, Mike
 *    Date: 2013
 *    Code version: 1.0
 *    Availability: https://www.androidcode.ninja/android-autocompletetextview-example-sqlite-database/
 *
 ***************************************************************************************/

    int autoTextSection = 0;

    CustomAutoCompleteView CustomerNameAutoComplete;
    CustomAutoCompleteView CustomerAccountAutoComplete;
    CustomAutoCompleteView ItemNameAutoComplete;
    CustomAutoCompleteView ItemBrandAutoComplete;
    CustomAutoCompleteView ItemPackSizeAutoComplete;
    CustomAutoCompleteView ItemFlavorAutoComplete;


    // adapter for auto-complete
    ArrayAdapter<String> CustomerNameAdapter;
    ArrayAdapter<String> CustomerAccountAdapter;
    ArrayAdapter<String> ItemNameAdapter;
    ArrayAdapter<String> ItemBrandAdapter;
    ArrayAdapter<String> ItemPackSizeAdapter;
    ArrayAdapter<String> ItemFlavorAdapter;

    // for database operations
    DatabaseOpenHelper databaseH;

    // just to add some initial value
    String[] item = new String[] {"Please search..."};


    /*End of cited code*/

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_form_ver_2_0);
        toolbar = findViewById(R.id.toolBar);
        setTitle("Enter Information");
        setSupportActionBar(toolbar);

/***************************************************************************************
 *    Title: Android AutocompleteTextView with Database Data as Suggestions
 *    Author: Dalisay, Mike
 *    Date: 2013
 *    Code version: 1.0
 *    Availability: https://www.androidcode.ninja/android-autocompletetextview-example-sqlite-database/
 *
 ***************************************************************************************/

        try{

            // instantiate database handler
            databaseH = new DatabaseOpenHelper(MainActivity.this);

            CustomerNameAutoComplete = (CustomAutoCompleteView) findViewById(R.id.customerNameAutoCompleteTextView);
            CustomerAccountAutoComplete = (CustomAutoCompleteView) findViewById(R.id.customerAccountAutoCompleteTextView);
            ItemNameAutoComplete = (CustomAutoCompleteView) findViewById(R.id.itemNameAutoCompleteTextView);
            ItemBrandAutoComplete = (CustomAutoCompleteView) findViewById(R.id.itemBrandAutoCompleteTextView);
            ItemPackSizeAutoComplete = (CustomAutoCompleteView) findViewById(R.id.itemPackSizeAutoCompleteTextView);
            ItemFlavorAutoComplete = (CustomAutoCompleteView) findViewById(R.id.itemFlavorAutoCompleteTextView);

            // add the listener so it will tries to suggest while the user types
            CustomerNameAutoComplete.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));
            CustomerAccountAutoComplete.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));
            ItemNameAutoComplete.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));
            ItemBrandAutoComplete.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));
            ItemPackSizeAutoComplete.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));
            ItemFlavorAutoComplete.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));

            // set our adapter
            CustomerNameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, item);
            CustomerNameAutoComplete.setAdapter(CustomerNameAdapter);

            CustomerAccountAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, item);
            CustomerAccountAutoComplete.setAdapter(CustomerAccountAdapter);

            ItemNameAdapter = new ArrayAdapter<String >(this, android.R.layout.simple_dropdown_item_1line, item);
            ItemNameAutoComplete.setAdapter(ItemNameAdapter);

            ItemBrandAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, item);
            ItemBrandAutoComplete.setAdapter(ItemBrandAdapter);

            ItemPackSizeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, item);
            ItemPackSizeAutoComplete.setAdapter(ItemPackSizeAdapter);

            ItemFlavorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, item);
            ItemFlavorAutoComplete.setAdapter(ItemFlavorAdapter);

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

/***************************************************************************************
 *    Title: Android AutocompleteTextView with Database Data as Suggestions
 *    Author: Dalisay, Mike
 *    Date: 2013
 *    Code version: 1.0
 *    Availability: https://www.androidcode.ninja/android-autocompletetextview-example-sqlite-database/
 *
 ***************************************************************************************/

    // this function is used in CustomAutoCompleteTextChangedListener.java
    public String[] getCustomerNameFromDb(String searchTerm){

        //if(autoTextSection = 1)
        // add items on the array dynamically
        List<com.teammystic.merchandiserstockcount.MyObject> customerNameValue = databaseH.CustomerNameRead(searchTerm);
        int rowCount = customerNameValue.size();

        String[] CustomerNameItem = new String[rowCount];
        int x = 0;

        for (com.teammystic.merchandiserstockcount.MyObject record : customerNameValue) {

            CustomerNameItem[x] = record.objectName;
            x++;
        }
        Log.i(TAG, "getCustomerNameFromDb: Test");
        return CustomerNameItem;
    }

    // this function is used in CustomAutoCompleteTextChangedListener.java
    public String[] getCustomerAccountFromDb(String searchTerm){

        //if(autoTextSection = 1)
        // add items on the array dynamically
        List<com.teammystic.merchandiserstockcount.MyObject> customerAccountValue = databaseH.CustomerAccountRead(searchTerm);
        int rowCount = customerAccountValue.size();

        String[] CustomerAccountItem = new String[rowCount];
        int x = 0;

        for (com.teammystic.merchandiserstockcount.MyObject record : customerAccountValue) {

            CustomerAccountItem[x] = record.objectName;
            x++;
        }

        return CustomerAccountItem;
    }

    // this function is used in CustomAutoCompleteTextChangedListener.java
    public String[] getItemNameFromDb(String searchTerm){

        //if(autoTextSection = 1)
        // add items on the array dynamically
        List<com.teammystic.merchandiserstockcount.MyObject> itemNameValue = databaseH.ItemNameRead(searchTerm);
        int rowCount = itemNameValue.size();

        String[] itemNameItem = new String[rowCount];
        int x = 0;

        for (com.teammystic.merchandiserstockcount.MyObject record : itemNameValue) {

            itemNameItem[x] = record.objectName;
            x++;
        }

        return itemNameItem;
    }

    public String[] getItemBrandFromDb(String searchTerm){

        //if(autoTextSection = 1)
        // add items on the array dynamically
        List<com.teammystic.merchandiserstockcount.MyObject> itemBrandValue = databaseH.ItemBrandRead(searchTerm);
        int rowCount = itemBrandValue.size();

        String[] itemBrandItem = new String[rowCount];
        int x = 0;

        for (com.teammystic.merchandiserstockcount.MyObject record : itemBrandValue) {

            itemBrandItem[x] = record.objectName;
            x++;
        }

        return itemBrandItem;
    }

    public String[] getItemPackSizeFromDb(String searchTerm){

        //if(autoTextSection = 1)
        // add items on the array dynamically
        List<com.teammystic.merchandiserstockcount.MyObject> itemPackSizeValue = databaseH.ItemPackSizeRead(searchTerm);
        int rowCount = itemPackSizeValue.size();

        String[] itemPackSizeItem = new String[rowCount];
        int x = 0;

        for (com.teammystic.merchandiserstockcount.MyObject record : itemPackSizeValue) {

            itemPackSizeItem[x] = record.objectName;
            x++;
        }

        return itemPackSizeItem;
    }

    public String[] getItemFlavorFromDb(String searchTerm){

        //if(autoTextSection = 1)
        // add items on the array dynamically
        List<com.teammystic.merchandiserstockcount.MyObject> itemFlavorValue = databaseH.ItemFlavorRead(searchTerm);
        int rowCount = itemFlavorValue.size();

        Log.i(TAG, "ItemFlavorRead: This Runs");

        String[] itemFlavorItem = new String[rowCount];
        int x = 0;

        for (com.teammystic.merchandiserstockcount.MyObject record : itemFlavorValue) {

            itemFlavorItem[x] = record.objectName;
            x++;
        }

        return itemFlavorItem;
    }
}



