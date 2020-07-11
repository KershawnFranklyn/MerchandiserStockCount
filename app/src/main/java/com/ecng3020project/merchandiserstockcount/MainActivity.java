package com.ecng3020project.merchandiserstockcount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.camera.core.Preview;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

    CustomAutoCompleteView RouteNumberAutoComplete;
    CustomAutoCompleteView CustomerNameAutoComplete;
    CustomAutoCompleteView CustomerAccountAutoComplete;
    CustomAutoCompleteView ItemNameAutoComplete;
    CustomAutoCompleteView ItemBrandAutoComplete;
    CustomAutoCompleteView ItemPackSizeAutoComplete;
    CustomAutoCompleteView ItemFlavorAutoComplete;

    // adapter for auto-complete
    ArrayAdapter<String> RouteNumberAdapter;
    ArrayAdapter<String> CustomerNameAdapter;
    ArrayAdapter<String> CustomerAccountAdapter;
    ArrayAdapter<String> ItemNameAdapter;
    ArrayAdapter<String> ItemBrandAdapter;
    ArrayAdapter<String> ItemPackSizeAdapter;
    ArrayAdapter<String> ItemFlavorAdapter;

    // for database operations
    DatabaseOpenHelper databaseH;

    // just to add some initial value
    String[] RouteNumberInputItem = new String[] {"Please search..."};
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
        dataFromCameraActivity();
        dataFromConfirmationActivity();



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

            RouteNumberAutoComplete = (CustomAutoCompleteView) findViewById(R.id.routeNumberAutoCompleteTextView);
            CustomerNameAutoComplete = (CustomAutoCompleteView) findViewById(R.id.customerNameAutoCompleteTextView);
            CustomerAccountAutoComplete = (CustomAutoCompleteView) findViewById(R.id.customerAccountAutoCompleteTextView);
            ItemNameAutoComplete = (CustomAutoCompleteView) findViewById(R.id.itemNameAutoCompleteTextView);
            ItemBrandAutoComplete = (CustomAutoCompleteView) findViewById(R.id.itemBrandAutoCompleteTextView);
            ItemPackSizeAutoComplete = (CustomAutoCompleteView) findViewById(R.id.itemPackSizeAutoCompleteTextView);
            ItemFlavorAutoComplete = (CustomAutoCompleteView) findViewById(R.id.itemFlavorAutoCompleteTextView);

            onClickListenerFunction();

            // add the listener so it will tries to suggest while the user types
            RouteNumberAutoComplete.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));
            CustomerNameAutoComplete.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));
            CustomerAccountAutoComplete.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));
            ItemNameAutoComplete.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));
            ItemBrandAutoComplete.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));
            ItemPackSizeAutoComplete.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));
            ItemFlavorAutoComplete.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));

            // set our adapter
            RouteNumberAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, RouteNumberInputItem);
            RouteNumberAutoComplete.setAdapter(RouteNumberAdapter);

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

                EditText routeNum = findViewById(R.id.routeNumberAutoCompleteTextView);
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


            }
        });

        final Button scanButton = findViewById(R.id.cameraScanButton);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouteNumberAutoComplete= (CustomAutoCompleteView) findViewById(R.id.routeNumberAutoCompleteTextView);
                String routeNumber = RouteNumberAutoComplete.getText().toString();
                CustomerNameAutoComplete = (CustomAutoCompleteView) findViewById(R.id.customerNameAutoCompleteTextView);
                String customerName = CustomerNameAutoComplete.getText().toString();
                CustomerAccountAutoComplete = (CustomAutoCompleteView) findViewById(R.id.customerAccountAutoCompleteTextView);
                String customerAccount = CustomerAccountAutoComplete.getText().toString();

                Intent intent = new Intent(MainActivity.this, CameraMainActivity.class);
                intent.putExtra("routeNumberIntent", routeNumber);
                intent.putExtra("customerNameIntent", customerName);
                intent.putExtra("customerAccountIntent", customerAccount);

                startActivity(intent);
            }
        });

    }

    //Display Autocomplete Suggestions Function
    private void onClickListenerFunction(){
        //ONClickListener used to check when an item is clicked. After it is clicked, the necessary data in other text fields are autofilled
        //Customer Name OnClickListener
        CustomerNameAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Autofill Customer Account Using Customer Name
                List<com.ecng3020project.merchandiserstockcount.MyObject> CustomerAccountQuery = databaseH.CustomerAccountTypedQuery(CustomerNameAutoComplete.getText().toString());
                int CustomerAccountNoRowCount = CustomerAccountQuery.size();
                String[] CustomerAccountStringArray = new String[CustomerAccountNoRowCount];
                int x = 0;

                for (com.ecng3020project.merchandiserstockcount.MyObject record : CustomerAccountQuery) {

                    CustomerAccountStringArray[x] = record.objectName;
                    x++;
                }
                CustomerAccountAutoComplete = (CustomAutoCompleteView) findViewById(R.id.customerAccountAutoCompleteTextView);
                CustomerAccountAutoComplete.setText(CustomerAccountStringArray[0]);

                //Autofill Route No Using Customer Name
                List<com.ecng3020project.merchandiserstockcount.MyObject> RouteNoQuery = databaseH.RouteNumberTypedQuery(CustomerNameAutoComplete.getText().toString());
                int RouteNoRowCount = RouteNoQuery.size();
                String[] RouteNoStringArray = new String[RouteNoRowCount];
                int routeNoVar = 0;

                for (com.ecng3020project.merchandiserstockcount.MyObject record : RouteNoQuery) {

                    RouteNoStringArray[routeNoVar] = record.objectName;
                    routeNoVar++;
                }
                RouteNumberAutoComplete = (CustomAutoCompleteView) findViewById(R.id.routeNumberAutoCompleteTextView);
                RouteNumberAutoComplete.setText(RouteNoStringArray[0]);

            }
        });

        //Customer Account OnClickListener
        CustomerAccountAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Autofill Customer Name Using Customer Account Number
                List<com.ecng3020project.merchandiserstockcount.MyObject> CustomerNameQuery = databaseH.CustomerNameTypedQuery(CustomerAccountAutoComplete.getText().toString());
                int CustomerNameRowCount = CustomerNameQuery.size();
                String[] CustomerNameStringArray = new String[CustomerNameRowCount];
                int x = 0;

                for (com.ecng3020project.merchandiserstockcount.MyObject record : CustomerNameQuery) {

                    CustomerNameStringArray[x] = record.objectName;
                    x++;
                }
                CustomerNameAutoComplete = (CustomAutoCompleteView) findViewById(R.id.customerNameAutoCompleteTextView);
                CustomerNameAutoComplete.setText(CustomerNameStringArray[0]);

                //Autofill Route No Using Customer Account Number
                List<com.ecng3020project.merchandiserstockcount.MyObject> RouteNoQuery = databaseH.RouteNoTypedQuery(CustomerAccountAutoComplete.getText().toString());
                int RouteNoRowCount = RouteNoQuery.size();
                String[] RouteNoStringArray = new String[RouteNoRowCount];
                int routeNoVar = 0;

                for (com.ecng3020project.merchandiserstockcount.MyObject record : RouteNoQuery) {

                    RouteNoStringArray[routeNoVar] = record.objectName;
                    routeNoVar++;
                }
                RouteNumberAutoComplete = (CustomAutoCompleteView) findViewById(R.id.routeNumberAutoCompleteTextView);
                RouteNumberAutoComplete.setText(RouteNoStringArray[0]);

            }
        });

        //Item Name OnClick Listener
        ItemNameAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Autofill Item Brand Using Item Name
                List<com.ecng3020project.merchandiserstockcount.MyObject> ItemBrandQuery = databaseH.ItemBrandTypedQuery(ItemNameAutoComplete.getText().toString());
                int ItemBrandRowCount = ItemBrandQuery.size();
                String[] ItemBrandStringArray = new String[ItemBrandRowCount];
                int itemBrandVar = 0;

                for (com.ecng3020project.merchandiserstockcount.MyObject record : ItemBrandQuery) {

                    ItemBrandStringArray[itemBrandVar] = record.objectName;
                    itemBrandVar++;
                }
                ItemBrandAutoComplete = (CustomAutoCompleteView) findViewById(R.id.itemBrandAutoCompleteTextView);
                ItemBrandAutoComplete.setText(ItemBrandStringArray[0]);

                //Autofill Item Pack Size Using Item Name
                List<com.ecng3020project.merchandiserstockcount.MyObject> ItemPackSizeQuery = databaseH.ItemPackSizeTypedQuery(ItemNameAutoComplete.getText().toString());
                int ItemPackSizeRowCount = ItemPackSizeQuery.size();
                String[] ItemPackSizeStringArray = new String[ItemPackSizeRowCount];
                int itemPackSizeVar= 0;

                for (com.ecng3020project.merchandiserstockcount.MyObject record : ItemPackSizeQuery) {

                    ItemPackSizeStringArray[itemPackSizeVar] = record.objectName;
                    itemPackSizeVar++;
                }
                ItemPackSizeAutoComplete = (CustomAutoCompleteView) findViewById(R.id.itemPackSizeAutoCompleteTextView);
                ItemPackSizeAutoComplete.setText(ItemPackSizeStringArray[0]);

                //Autofill Item Flavor Using Item Name
                List<com.ecng3020project.merchandiserstockcount.MyObject> ItemFlavorQuery = databaseH.ItemFlavorTypedQuery(ItemNameAutoComplete.getText().toString());
                int ItemFlavorRowCount = ItemFlavorQuery.size();
                String[] ItemFlavorStringArray = new String[ItemFlavorRowCount];
                int itemFlavorVar= 0;

                for (com.ecng3020project.merchandiserstockcount.MyObject record : ItemFlavorQuery) {

                    ItemFlavorStringArray[itemFlavorVar] = record.objectName;
                    itemFlavorVar++;
                }
                ItemFlavorAutoComplete = (CustomAutoCompleteView) findViewById(R.id.itemFlavorAutoCompleteTextView);
                ItemFlavorAutoComplete.setText(ItemFlavorStringArray[0]);
            }
        });

    }

    private void dataFromCameraActivity() {
        Intent intent = getIntent();

        String customer_accountString = intent.getStringExtra("CustomerAccountIntent");
        String item_nameString = intent.getStringExtra("ItemNameIntent");
        String item_brandString = intent.getStringExtra("ItemBrandIntent");
        String item_packsizeString = intent.getStringExtra("ItemPackSizeIntent");
        String item_flavorString = intent.getStringExtra("ItemFlavorIntent");
        String route_numberString = intent.getStringExtra("RouteNumberIntent");
        String customer_nameString = intent.getStringExtra("CustomerNameIntent");

        RouteNumberAutoComplete = (CustomAutoCompleteView) findViewById(R.id.routeNumberAutoCompleteTextView);
        RouteNumberAutoComplete.setText(route_numberString);
        CustomerNameAutoComplete = (CustomAutoCompleteView) findViewById(R.id.customerNameAutoCompleteTextView);
        CustomerNameAutoComplete.setText(customer_nameString);
        CustomerAccountAutoComplete = (CustomAutoCompleteView) findViewById(R.id.customerAccountAutoCompleteTextView);
        CustomerAccountAutoComplete.setText(customer_accountString);
        ItemNameAutoComplete = (CustomAutoCompleteView) findViewById(R.id.itemNameAutoCompleteTextView);
        ItemNameAutoComplete.setText(item_nameString);
        ItemBrandAutoComplete = (CustomAutoCompleteView) findViewById(R.id.itemBrandAutoCompleteTextView);
        ItemBrandAutoComplete.setText(item_brandString);
        ItemPackSizeAutoComplete = (CustomAutoCompleteView) findViewById(R.id.itemPackSizeAutoCompleteTextView);
        ItemPackSizeAutoComplete.setText(item_packsizeString);
        ItemFlavorAutoComplete = (CustomAutoCompleteView) findViewById(R.id.itemFlavorAutoCompleteTextView);
        ItemFlavorAutoComplete.setText(item_flavorString);

    }

    private void dataFromConfirmationActivity() {
        Intent intent = getIntent();

        String route_numberConfirmationString = intent.getStringExtra("RouteNumberIntent");
        String customer_accountConfirmationString = intent.getStringExtra("CustomerAccountIntent");
        String customer_nameConfirmationString = intent.getStringExtra("CustomerNameIntent");

        RouteNumberAutoComplete = (CustomAutoCompleteView) findViewById(R.id.routeNumberAutoCompleteTextView);
        RouteNumberAutoComplete.setText(route_numberConfirmationString);
        CustomerNameAutoComplete = (CustomAutoCompleteView) findViewById(R.id.customerNameAutoCompleteTextView);
        CustomerNameAutoComplete.setText(customer_nameConfirmationString);
        CustomerAccountAutoComplete = (CustomAutoCompleteView) findViewById(R.id.customerAccountAutoCompleteTextView);
        CustomerAccountAutoComplete.setText(customer_accountConfirmationString);
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
    public String[] getRouteNumberFromDb(String searchTerm){
        List<com.ecng3020project.merchandiserstockcount.MyObject> routeNumberValue = databaseH.RouteNumberRead(searchTerm);
        int rowCount = routeNumberValue.size();

        String[] RouteNumberItem = new String[rowCount];
        int x =0;

        for (com.ecng3020project.merchandiserstockcount.MyObject record : routeNumberValue){

            RouteNumberItem[x] = record.objectName;
            x++;
        }

        return RouteNumberItem;
    }


    public String[] getCustomerNameFromDb(String searchTerm){

        if(searchTerm.contains("'")){
            searchTerm.replace("'", "''");
        }

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
