package com.ecng3020project.merchandiserstockcount;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

import java.util.List;

public class ResultsActivity extends AppCompatActivity {
    DatabaseOpenHelper databaseH;
    Toolbar toolbar;
    TextView CustomerNameResultTextView;
    String customer_nameConfirmationString;
    String customer_AccountNoString;
    String[] ItemNameStringArray;
    String[] NumberOfCasesStringArray;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_screen_ver_1_0);
        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(ResultsActivity.this);
        databaseAccess.open();
        databaseAccess.createTable();
        databaseAccess.createTempDataTable();
        databaseAccess.close();
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        setTitle("Suggested Orders");

        Intent intent = getIntent();
        customer_nameConfirmationString = intent.getStringExtra("CustomerNameIntent");
        CustomerNameResultTextView = (TextView) findViewById(R.id.customerNameResult);
        CustomerNameResultTextView.setText(customer_nameConfirmationString);
        customer_AccountNoString = intent.getStringExtra("CustomerAccountNoIntent");

        databaseH = new DatabaseOpenHelper(ResultsActivity.this);

        LinearLayout verticalLinearLayout = (LinearLayout) findViewById(R.id.dynamicLinearLayout);          //Linear Layout containing all dynamically added text
        LinearLayout.LayoutParams itemTxtParams = new LinearLayout.LayoutParams(
                407,        //Equivalent to 155sp width with 420dpi
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        LinearLayout.LayoutParams horizontalSpaceParams = new LinearLayout.LayoutParams(
                8,         //Equivalent to 3sp width with 420 dpi
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        LinearLayout.LayoutParams verticalSpaceParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                6          //Equivalent to 3sp width with 420 dpi
        );
        LinearLayout.LayoutParams orderTxtParams = new LinearLayout.LayoutParams(
                276,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        List<MyObject> DisplayItemNameList = databaseH.listing_ItemNames();
        int ItemNameRowCount = DisplayItemNameList.size();
        ItemNameStringArray = new String[ItemNameRowCount];
        int itemNameVar= 0;

        for (com.ecng3020project.merchandiserstockcount.MyObject record : DisplayItemNameList) {

            ItemNameStringArray[itemNameVar] = record.objectName;
            itemNameVar++;
        }

        List<MyObject> ResultsNumberOfCasesList = databaseH.listing_NumberOfCases();
        int NumberOfCasesRowCount = ResultsNumberOfCasesList.size();
        NumberOfCasesStringArray = new String[NumberOfCasesRowCount];
        int numberOfCasesVar= 0;

        for (com.ecng3020project.merchandiserstockcount.MyObject record : ResultsNumberOfCasesList) {

            NumberOfCasesStringArray[numberOfCasesVar] = record.objectName;
            numberOfCasesVar++;
        }

        for (int i =0; i < ItemNameRowCount; i++){
            TextView itemTextView = new TextView(this);
            TextView orderResultTextView = new TextView(this);
            LinearLayout horizontalLinearLayout = new LinearLayout(this);
            horizontalLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

            Space horizontalSpace1 = new Space(this);
            horizontalSpace1.setLayoutParams(horizontalSpaceParams);
            Space horizontalSpace2 = new Space(this);
            horizontalSpace2.setLayoutParams(horizontalSpaceParams);
            Space verticalSpace = new Space(this);
            verticalSpace.setLayoutParams(verticalSpaceParams);

            itemTextView.setText(ItemNameStringArray[i]);
            itemTextView.setLayoutParams(itemTxtParams);
            itemTextView.setBackgroundColor(ContextCompat.getColor(this, R.color.GhostWhite));
            itemTextView.setTextColor(ContextCompat.getColor(this, R.color.SMJTextBlue));          //Sets text color to: SMJTextBlue
            itemTextView.setTextSize(20);
            itemTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            itemTextView.setPadding(8, itemTextView.getPaddingTop(), 8, itemTextView.getPaddingBottom());

            orderResultTextView.setText(numOfCasesResult( NumberOfCasesStringArray[i], simpleAvg(ItemNameStringArray[i], customer_AccountNoString),  movingAvg(ItemNameStringArray[i], customer_AccountNoString)));
            orderResultTextView.setLayoutParams(orderTxtParams);
            orderResultTextView.setTextColor(ContextCompat.getColor(this, R.color.SMJTextBlue));
            orderResultTextView.setTextSize(20);
            orderResultTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            orderResultTextView.setBackgroundColor(ContextCompat.getColor(this, R.color.GhostWhite));

            horizontalLinearLayout.addView(itemTextView);
            horizontalLinearLayout.addView(horizontalSpace1);
            horizontalLinearLayout.addView(orderResultTextView);
            verticalLinearLayout.addView(horizontalLinearLayout);
            verticalLinearLayout.addView(verticalSpace);
        }

        final Button finishBtn = findViewById(R.id.finishBtn);
        finishBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                databaseAccess.open();
                databaseAccess.dropTempTable();
                databaseAccess.close();
                Intent intent = new Intent(ResultsActivity.this, HomePageActivity.class);
                startActivity(intent);
            }

        });
    }

    public String simpleAvg(String itemNameSearchString, String customerAccountSearchTerm) {
        List<com.ecng3020project.merchandiserstockcount.MyObject> simpleAvgResultValue = databaseH.simpleAverage(itemNameSearchString, customer_AccountNoString);
        int rowCount = simpleAvgResultValue.size();

        String[] simpleAvgResultItem = new String[rowCount];
        int x = 0;

        for (com.ecng3020project.merchandiserstockcount.MyObject record : simpleAvgResultValue) {

            simpleAvgResultItem[x] = record.objectName;
            x++;
        }

        List<com.ecng3020project.merchandiserstockcount.MyObject> noResultAvgValue = databaseH.noResultAverage(itemNameSearchString, customer_AccountNoString);
        int noResultAvgRowCount = noResultAvgValue.size();

        String[] noResultAvgItemValue = new String[noResultAvgRowCount];
        int noResultVar = 0;

        for (com.ecng3020project.merchandiserstockcount.MyObject record : noResultAvgValue) {

            noResultAvgItemValue[noResultVar] = record.objectName;
            noResultVar++;
        }

        if(simpleAvgResultItem[0] == null){
            Log.i(" No result", "The result of the average from other routes is: "+ noResultAvgItemValue[0]);
            return noResultAvgItemValue[0];
        }
        else{
            Log.i(" Simple Testing result", "The result of the average is: "+ simpleAvgResultItem[0]);
            return simpleAvgResultItem[0];
        }
    }

    public String movingAvg(String itemNameSearchString, String customerAccountSearchTerm){
        String average = "";
        List<com.ecng3020project.merchandiserstockcount.MyObject> movingAvgResultValue = databaseH.movingAverage(itemNameSearchString, customer_AccountNoString);
        int rowCount = movingAvgResultValue.size();


        String[] movingAvgResultItem = new String[rowCount];
        int x = 0;

        for (com.ecng3020project.merchandiserstockcount.MyObject record : movingAvgResultValue) {

            movingAvgResultItem[x] = record.objectName;
            x++;
        }

        List<com.ecng3020project.merchandiserstockcount.MyObject> movingAvgResultValue4 = databaseH.movingAverageValue4(itemNameSearchString, customer_AccountNoString);
        int rowCount4 = movingAvgResultValue.size();

        String[] movingAvgResultItemValue4 = new String[rowCount4];
        int x4 = 0;

        for (com.ecng3020project.merchandiserstockcount.MyObject record : movingAvgResultValue4) {

            movingAvgResultItemValue4[x4] = record.objectName;
            x4++;
        }

        List<com.ecng3020project.merchandiserstockcount.MyObject> movingAvgResultValue3 = databaseH.movingAverageValue4(itemNameSearchString, customer_AccountNoString);
        int rowCount3 = movingAvgResultValue.size();

        String[] movingAvgResultItemValue3 = new String[rowCount3];
        int x3 = 0;

        for (com.ecng3020project.merchandiserstockcount.MyObject record : movingAvgResultValue3) {

            movingAvgResultItemValue3[x3] = record.objectName;
            x3++;
        }

        List<com.ecng3020project.merchandiserstockcount.MyObject> movingAvgResultValue2 = databaseH.movingAverageValue4(itemNameSearchString, customer_AccountNoString);
        int rowCount2 = movingAvgResultValue.size();

        String[] movingAvgResultItemValue2 = new String[rowCount2];
        int x2 = 0;

        for (com.ecng3020project.merchandiserstockcount.MyObject record : movingAvgResultValue2) {

            movingAvgResultItemValue2[x2] = record.objectName;
            x2++;
        }

        List<com.ecng3020project.merchandiserstockcount.MyObject> movingAvgResultValue1 = databaseH.movingAverageValue4(itemNameSearchString, customer_AccountNoString);
        int rowCount1 = movingAvgResultValue.size();

        String[] movingAvgResultItemValue1 = new String[rowCount1];
        int x1 = 0;

        for (com.ecng3020project.merchandiserstockcount.MyObject record : movingAvgResultValue1) {

            movingAvgResultItemValue1[x1] = record.objectName;
            x1++;
        }

        average = movingAvgResultItem[0];
        Log.i("Testing Moving Avg", "movingAvg: The value of the moving average is "+ average);

        if(movingAvgResultItem[0] == null){
            average = movingAvgResultItemValue4[0];
            Log.i("Value 4", "movingAvg: "+average);
        }

        if(movingAvgResultItemValue4[0] == null){
            average = movingAvgResultItemValue3[0];
            Log.i("Value 3", "movingAvg: "+average);
        }

        if(movingAvgResultItemValue3[0] == null){
            average = movingAvgResultItemValue2[0];
            Log.i("Value 2", "movingAvg: "+average);
        }

        if(movingAvgResultItemValue2[0] == null){
            average = movingAvgResultItemValue1[0];
            Log.i("Value 1", "movingAvg: "+average);
        }

        if(movingAvgResultItemValue1[0] == null){
            return movingAvgResultItemValue1[0];
        }

        double avg = Double.parseDouble(average);
        avg = Math.ceil(avg);
        long intAvg = Math.round(avg);
        String result = String.valueOf(intAvg);
        Log.i("Moving Testing result", "The result of the average is: "+ average);
        return result;
    }

    public String numOfCasesResult (String numberOfCasesEnteredString, String simpleAvg, String movingAvg) {
        double simple_Avg = Double.parseDouble(simpleAvg);
        double moving_Avg;
        if(movingAvg == null ){
            moving_Avg = Double.parseDouble(simpleAvg);
            Log.i("No Result", "numOfCasesResult: The value of moving_Avg = simple_Avg which is "+simpleAvg);
        }
        else {
            moving_Avg = Double.parseDouble(movingAvg);
            Log.i("Result", "numOfCasesResult: The result of the moving avg from query is"+movingAvg);
        }
        double combined_Avg = (simple_Avg+moving_Avg)/2;
        Log.i("Combined Avg", "The combined average is "+combined_Avg);

        double enteredNumber = Double.parseDouble(numberOfCasesEnteredString);
        double numberResult = combined_Avg - enteredNumber;

        numberResult = Math.ceil(numberResult);
        if(numberResult <= 0){
            numberResult = 0; //Add function to search other customers from the same route for the same item (avg value)
        }
        long intNumberResult = Math.round(numberResult);
        String result = String.valueOf(intNumberResult);
        return result;
    }
}
