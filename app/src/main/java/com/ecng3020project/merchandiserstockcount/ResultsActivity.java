package com.ecng3020project.merchandiserstockcount;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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
        String customer_nameConfirmationString = intent.getStringExtra("CustomerNameIntent");
        CustomerNameResultTextView = (TextView) findViewById(R.id.customerNameResult);
        CustomerNameResultTextView.setText(customer_nameConfirmationString);

        databaseH = new DatabaseOpenHelper(ResultsActivity.this);

        LinearLayout verticalLinearLayout = (LinearLayout) findViewById(R.id.dynamicLinearLayout);
        LinearLayout.LayoutParams itemTxtParams = new LinearLayout.LayoutParams(
                407,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        LinearLayout.LayoutParams horizontalSpaceParams = new LinearLayout.LayoutParams(
                13,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        LinearLayout.LayoutParams verticalSpaceParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                26
        );
        LinearLayout.LayoutParams orderTxtParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        //Autofill Item Flavor Using Item Name
        List<MyObject> DisplayItemNameList = databaseH.listing_ItemNames();
        int ItemNameRowCount = DisplayItemNameList.size();
        String[] ItemNameStringArray = new String[ItemNameRowCount];
        int itemNameVar= 0;

        for (com.ecng3020project.merchandiserstockcount.MyObject record : DisplayItemNameList) {

            ItemNameStringArray[itemNameVar] = record.objectName;
            itemNameVar++;
        }

        for (int i =0; i < ItemNameRowCount; i++){
            TextView itemTextView = new TextView(this);
            TextView orderTextView = new TextView(this);
            LinearLayout horizontalLinearLayout = new LinearLayout(this);
            horizontalLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

            Space horizontalSpace = new Space(this);
            horizontalSpace.setLayoutParams(horizontalSpaceParams);
            Space verticalSpace = new Space(this);
            verticalSpace.setLayoutParams(verticalSpaceParams);

            itemTextView.setText(ItemNameStringArray[i]);
            itemTextView.setLayoutParams(itemTxtParams);
            itemTextView.setTextColor(ContextCompat.getColor(this, R.color.SMJTextBlue));          //Sets text color to: SMJTextBlue
            itemTextView.setTextSize(20);

            orderTextView.setText("Test");
            orderTextView.setLayoutParams(orderTxtParams);
            orderTextView.setTextColor(ContextCompat.getColor(this, R.color.SMJTextBlue));
            orderTextView.setTextSize(20);
            orderTextView.setBackgroundColor(ContextCompat.getColor(this, R.color.GhostWhite));

            horizontalLinearLayout.addView(itemTextView);
            horizontalLinearLayout.addView(horizontalSpace);
            horizontalLinearLayout.addView(orderTextView);
            verticalLinearLayout.addView(horizontalLinearLayout);
            verticalLinearLayout.addView(verticalSpace);
        }

        //params.setMarginStart(60);
        //params.setMarginEnd(60);


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
}
