/***************************************************************************************
 *    Title: Android AutocompleteTextView with Database Data as Suggestions
 *    Author: Dalisay, Mike
 *    Date: 2013
 *    Code version: 1.0
 *    Availability: https://www.androidcode.ninja/android-autocompletetextview-example-sqlite-database/
 *
 ***************************************************************************************/

package com.teammystic.merchandiserstockcount;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;

public class CustomAutoCompleteTextChangedListener implements TextWatcher{

    public static final String TAG = "CustomAutoCompleteTextChangedListener.java";
    Context context;

    public CustomAutoCompleteTextChangedListener(Context context){
        this.context = context;
    }

    @Override
    public void afterTextChanged(Editable s) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
        // TODO Auto-generated method stub

    }

    @SuppressLint("LongLogTag")
    @Override
    public void onTextChanged(CharSequence userInput, int start, int before, int count) {

        // if you want to see in the logcat what the user types
        Log.e(TAG, "User input: " + userInput);

        MainActivity mainActivity = ((MainActivity) context);

        // query the database based on the user input
        mainActivity.item = mainActivity.getCustomerNameFromDb(userInput.toString());
        mainActivity.item = mainActivity.getCustomerAccountFromDb(userInput.toString());
        mainActivity.item = mainActivity.getItemNameFromDb(userInput.toString());
        mainActivity.item = mainActivity.getItemBrandFromDb(userInput.toString());
        mainActivity.item = mainActivity.getItemPackSizeFromDb(userInput.toString());
        mainActivity.item = mainActivity.getItemFlavorFromDb(userInput.toString());

        // update the adapater
        mainActivity.CustomerNameAdapter.notifyDataSetChanged();
        mainActivity.CustomerNameAdapter = new ArrayAdapter<String>(mainActivity, android.R.layout.simple_dropdown_item_1line, mainActivity.item);
        mainActivity.CustomerNameAutoComplete.setAdapter(mainActivity.CustomerNameAdapter);

        mainActivity.CustomerAccountAdapter.notifyDataSetChanged();
        mainActivity.CustomerAccountAdapter = new ArrayAdapter<String >(mainActivity, android.R.layout.simple_dropdown_item_1line, mainActivity.item);
        mainActivity.CustomerAccountAutoComplete.setAdapter(mainActivity.CustomerAccountAdapter);

        mainActivity.ItemNameAdapter.notifyDataSetChanged();
        mainActivity.ItemNameAdapter = new ArrayAdapter<String >(mainActivity, android.R.layout.simple_dropdown_item_1line, mainActivity.item);
        mainActivity.ItemNameAutoComplete.setAdapter(mainActivity.ItemNameAdapter);

        mainActivity.ItemBrandAdapter.notifyDataSetChanged();
        mainActivity.ItemBrandAdapter = new ArrayAdapter<String >(mainActivity, android.R.layout.simple_dropdown_item_1line, mainActivity.item);
        mainActivity.ItemBrandAutoComplete.setAdapter(mainActivity.ItemBrandAdapter);

        mainActivity.ItemPackSizeAdapter.notifyDataSetChanged();
        mainActivity.ItemPackSizeAdapter = new ArrayAdapter<String >(mainActivity, android.R.layout.simple_dropdown_item_1line, mainActivity.item);
        mainActivity.ItemPackSizeAutoComplete.setAdapter(mainActivity.ItemPackSizeAdapter);

        mainActivity.ItemFlavorAdapter.notifyDataSetChanged();
        mainActivity.ItemFlavorAdapter = new ArrayAdapter<String >(mainActivity, android.R.layout.simple_dropdown_item_1line, mainActivity.item);
        mainActivity.ItemFlavorAutoComplete.setAdapter(mainActivity.ItemFlavorAdapter);

    }

}