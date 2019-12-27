package com.teammystic.merchandiserstockcount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.w3c.dom.Text;

public class ConfirmationActivity extends AppCompatActivity {

    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation_screen_ver_1_0);
        toolbar = findViewById(R.id.toolBar);
        setTitle("Confirm Information");
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String routeNumberString = intent.getStringExtra("routeNumberIntent");
        String customerNameString = intent.getStringExtra("customerNameIntent");
        String customerAccountString = intent.getStringExtra("customerAccountIntent");
        String itemNameString = intent.getStringExtra("itemNameIntent");
        String itemBrandString = intent.getStringExtra("itemBrandIntent");
        String itemPackSizeString = intent.getStringExtra("itemPackSizeIntent");
        String itemFlavorString = intent.getStringExtra("itemFlavorIntent");
        String numOfCasesString = intent.getStringExtra("numOfCasesIntent");

        Toast toast = Toast.makeText(ConfirmationActivity.this,"Please confirm information. Click 'Submit' to save.", Toast.LENGTH_LONG);
        toast.show();

        EditText routeNumTextView = findViewById(R.id.displayRouteNumberInputEditTextView);
        routeNumTextView.setText(routeNumberString);

        EditText custNameTextView = findViewById(R.id.displayCustomerNameInputEditTextView);
        custNameTextView.setText(customerNameString);

        EditText custAccTextView = findViewById(R.id.displayCustomerAccountInputEditTextView);
        custAccTextView.setText(customerAccountString);

        EditText itemNameTextView = findViewById(R.id.displayItemNameInputEditTextView);
        itemNameTextView.setText(itemNameString);

        EditText itemBrandTextView = findViewById(R.id.displayItemBrandInputEditTextView);
        itemBrandTextView.setText(itemBrandString);

        EditText itemPackSizeTextView = findViewById(R.id.displayItemPackSizeInputEditTextView);
        itemPackSizeTextView.setText(itemPackSizeString);

        EditText itemFlavorTextView = findViewById(R.id.displayItemFlavorInputEditTextView);
        itemFlavorTextView.setText(itemFlavorString);

        EditText numOfCasesTextView = findViewById(R.id.displayNoOfCasesInputEditTextView);
        numOfCasesTextView.setText(numOfCasesString);


    }
}
