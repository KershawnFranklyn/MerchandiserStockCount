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
    float simpleAvgerageValue = 0;

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

        for (int i =0; i < ItemNameRowCount; i++){
            TextView itemTextView = new TextView(this);
            TextView orderResult1TextView = new TextView(this);
            TextView orderResult2TextView = new TextView(this);
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

            orderResult1TextView.setText(simpleAvg(ItemNameStringArray[i], customer_AccountNoString));
            orderResult1TextView.setLayoutParams(orderTxtParams);
            orderResult1TextView.setTextColor(ContextCompat.getColor(this, R.color.SMJTextBlue));
            orderResult1TextView.setTextSize(20);
            orderResult1TextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            orderResult1TextView.setBackgroundColor(ContextCompat.getColor(this, R.color.GhostWhite));

            orderResult2TextView.setText(movingAvg(ItemNameStringArray[i], customer_AccountNoString));
            orderResult2TextView.setLayoutParams(orderTxtParams);
            orderResult2TextView.setTextColor(ContextCompat.getColor(this, R.color.SMJTextBlue));
            orderResult2TextView.setTextSize(20);
            orderResult2TextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            orderResult2TextView.setBackgroundColor(ContextCompat.getColor(this, R.color.GhostWhite));

            horizontalLinearLayout.addView(itemTextView);
            horizontalLinearLayout.addView(horizontalSpace1);
            horizontalLinearLayout.addView(orderResult1TextView);
            horizontalLinearLayout.addView(horizontalSpace2);
            horizontalLinearLayout.addView(orderResult2TextView);
            verticalLinearLayout.addView(horizontalLinearLayout);
            verticalLinearLayout.addView(verticalSpace);
        }

        final Button finishBtn = findViewById(R.id.finishBtn);
        finishBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //databaseAccess.open();
                //databaseAccess.dropTempTable();
                //databaseAccess.close();
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
        Log.i("Testing result", "The result of te avgerage is: "+ simpleAvgResultItem[0]);
        return simpleAvgResultItem[0];
    }

    public String movingAvg(String itemNameSearchString, String customerAccountSearchTerm){
        List<com.ecng3020project.merchandiserstockcount.MyObject> movingAvgResultValue = databaseH.movingAverage(itemNameSearchString, customer_AccountNoString);
        int rowCount = movingAvgResultValue.size();

        String[] movingAvgResultItem = new String[rowCount];
        int x = 0;

        for (com.ecng3020project.merchandiserstockcount.MyObject record : movingAvgResultValue) {

            movingAvgResultItem[x] = record.objectName;
            x++;
        }

        double avg = Double.parseDouble(movingAvgResultItem[0]);
        avg = Math.ceil(avg);
        long intAvg = Math.round(avg);
        String result = String.valueOf(intAvg);
        Log.i("Testing result", "The result of te avgerage is: "+ movingAvgResultItem[0]);
        return result;
    }
}
