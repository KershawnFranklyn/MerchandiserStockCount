package com.ecng3020project.merchandiserstockcount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Test";
    /***************************************************************************************
 *    Title: Android AutocompleteTextView with Database Data as Suggestions
 *    Author: Dalisay, Mike
 *    Date: 2013
 *    Code version: 1.0
 *    Availability: https://www.androidcode.ninja/android-autocompletetextview-example-sqlite-database/
 *    Code Adapted to fit this project
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
    String[] CustomerNameInputItem = new String[] {"Please search..."};
    String[] CustomerAccountInputItem = new String[] {"Please search..."};
    String[] ItemNameInputItem = new String[]{"Please search..."};
    String[] ItemBrandInputItem = new String[]{"Please search..."};
    String[] ItemPackSizeInputItem = new String[]{"Please search..."};
    String[] ItemFlavorInputItem = new String[]{"Please search..."};

    String routeNumber;
    String custName;
    String custAccount;
    String itemName;
    String itemBrand;
    String itemPackSize;
    String itemFlavor;
    String numOfCases;

    /*End of cited code*/

    private Toolbar toolbar;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_form_ver_2_0);
        toolbar = findViewById(R.id.toolBar);
        setTitle("Enter Information");
        setSupportActionBar(toolbar);
        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);

/***************************************************************************************
 *    Title: Android AutocompleteTextView with Database Data as Suggestions
 *    Author: Dalisay, Mike
 *    Date: 2013
 *    Code version: 1.0
 *    Availability: https://www.androidcode.ninja/android-autocompletetextview-example-sqlite-database/
 *    Code Adapted to fit this project
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
            CustomerNameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, CustomerNameInputItem);
            CustomerNameAutoComplete.setAdapter(CustomerNameAdapter);

            CustomerAccountAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, CustomerAccountInputItem);
            CustomerAccountAutoComplete.setAdapter(CustomerAccountAdapter);

            ItemNameAdapter = new ArrayAdapter<String >(this, android.R.layout.simple_dropdown_item_1line, ItemNameInputItem);
            ItemNameAutoComplete.setAdapter(ItemNameAdapter);

            ItemBrandAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, ItemBrandInputItem);
            ItemBrandAutoComplete.setAdapter(ItemBrandAdapter);

            ItemPackSizeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, ItemPackSizeInputItem);
            ItemPackSizeAutoComplete.setAdapter(ItemPackSizeAdapter);

            ItemFlavorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, ItemFlavorInputItem);
            ItemFlavorAutoComplete.setAdapter(ItemFlavorAdapter);


        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        final Button button = findViewById(R.id.nextButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText routeNum = findViewById(R.id.routeNumberEditText);
                routeNumber = routeNum.getText().toString();
                EditText customerNameEdit = findViewById(R.id.customerNameAutoCompleteTextView);
                custName = customerNameEdit.getText().toString();
                EditText customerAccEdit = findViewById(R.id.customerAccountAutoCompleteTextView);
                custAccount = customerAccEdit.getText().toString();
                EditText itemNameEdit = findViewById(R.id.itemNameAutoCompleteTextView);
                itemName = itemNameEdit.getText().toString();
                EditText itemBrandEdit = findViewById(R.id.itemBrandAutoCompleteTextView);
                itemBrand = itemBrandEdit.getText().toString();
                EditText itemPackSizeEdit = findViewById(R.id.itemPackSizeAutoCompleteTextView);
                itemPackSize = itemPackSizeEdit.getText().toString();
                EditText itemFlavorEdit = findViewById(R.id.itemFlavorAutoCompleteTextView);
                itemFlavor = itemFlavorEdit.getText().toString();
                EditText numOfCasesEdit = findViewById(R.id.numberOfCasesEditTextView);
                numOfCases = numOfCasesEdit.getText().toString();

                Intent intent = new Intent(MainActivity.this, ConfirmationActivity.class);
                intent.putExtra("routeNumberIntent", routeNumber);
                intent.putExtra("customerNameIntent", custName);
                intent.putExtra("customerAccountIntent", custAccount);
                intent.putExtra("itemNameIntent", itemName);
                intent.putExtra("itemBrandIntent", itemBrand);
                intent.putExtra("itemPackSizeIntent", itemPackSize);
                intent.putExtra("itemFlavorIntent", itemFlavor);
                intent.putExtra("numOfCasesIntent", numOfCases);
                startActivity(intent);



///***************************************************************************************
// *    Title: Android SQLite Database Tutorial (Select, Insert, Update, Delete)
// *    Author: Singh, Mithilesh
// *    Date: 2017
// *    Code version: 1.0
// *    Availability: http://www.codebind.com/android-tutorials-and-examples/android-sqlite-tutorial-example/
// *    Code Adapted to fit this project
// ***************************************************************************************/



            }
        });

    }

/***************************************************************************************
 *    Title: Android AutocompleteTextView with Database Data as Suggestions
 *    Author: Dalisay, Mike
 *    Date: 2013
 *    Code version: 1.0
 *    Availability: https://www.androidcode.ninja/android-autocompletetextview-example-sqlite-database/
 *    Code Adapted to fit this project
 ***************************************************************************************/

    // this function is used in CustomAutoCompleteTextChangedListener.java
    public String[] getCustomerNameFromDb(String searchTerm){

        //if(autoTextSection = 1)
        // add items on the array dynamically
        List<com.ecng3020project.merchandiserstockcount.MyObject> customerNameValue = databaseH.CustomerNameRead(searchTerm);
        int rowCount = customerNameValue.size();

        String[] CustomerNameItem = new String[rowCount];
        int x = 0;

        for (com.ecng3020project.merchandiserstockcount.MyObject record : customerNameValue) {

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
        List<com.ecng3020project.merchandiserstockcount.MyObject> customerAccountValue = databaseH.CustomerAccountRead(searchTerm);
        int rowCount = customerAccountValue.size();

        String[] CustomerAccountItem = new String[rowCount];
        int x = 0;

        for (com.ecng3020project.merchandiserstockcount.MyObject record : customerAccountValue) {

            CustomerAccountItem[x] = record.objectName;
            x++;
        }

        return CustomerAccountItem;
    }

    // this function is used in CustomAutoCompleteTextChangedListener.java
    public String[] getItemNameFromDb(String searchTerm){

        //if(autoTextSection = 1)
        // add items on the array dynamically
        List<com.ecng3020project.merchandiserstockcount.MyObject> itemNameValue = databaseH.ItemNameRead(searchTerm);
        int rowCount = itemNameValue.size();

        String[] itemNameItem = new String[rowCount];
        int x = 0;

        for (com.ecng3020project.merchandiserstockcount.MyObject record : itemNameValue) {

            itemNameItem[x] = record.objectName;
            x++;
        }

        return itemNameItem;
    }

    public String[] getItemBrandFromDb(String searchTerm){

        //if(autoTextSection = 1)
        // add items on the array dynamically
        List<com.ecng3020project.merchandiserstockcount.MyObject> itemBrandValue = databaseH.ItemBrandRead(searchTerm);
        int rowCount = itemBrandValue.size();

        String[] itemBrandItem = new String[rowCount];
        int x = 0;

        for (com.ecng3020project.merchandiserstockcount.MyObject record : itemBrandValue) {

            itemBrandItem[x] = record.objectName;
            x++;
        }

        return itemBrandItem;
    }

    public String[] getItemPackSizeFromDb(String searchTerm){

        //if(autoTextSection = 1)
        // add items on the array dynamically
        List<com.ecng3020project.merchandiserstockcount.MyObject> itemPackSizeValue = databaseH.ItemPackSizeRead(searchTerm);
        int rowCount = itemPackSizeValue.size();

        String[] itemPackSizeItem = new String[rowCount];
        int x = 0;

        for (com.ecng3020project.merchandiserstockcount.MyObject record : itemPackSizeValue) {

            itemPackSizeItem[x] = record.objectName;
            x++;
        }

        return itemPackSizeItem;
    }

    public String[] getItemFlavorFromDb(String searchTerm){

        //if(autoTextSection = 1)
        // add items on the array dynamically
        List<com.ecng3020project.merchandiserstockcount.MyObject> itemFlavorValue = databaseH.ItemFlavorRead(searchTerm);
        int rowCount = itemFlavorValue.size();

        Log.i(TAG, "ItemFlavorRead: This Runs");

        String[] itemFlavorItem = new String[rowCount];
        int x = 0;

        for (com.ecng3020project.merchandiserstockcount.MyObject record : itemFlavorValue) {

            itemFlavorItem[x] = record.objectName;
            x++;
        }

        return itemFlavorItem;
    }

}



