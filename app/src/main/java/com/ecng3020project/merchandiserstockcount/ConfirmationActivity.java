package com.ecng3020project.merchandiserstockcount;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ConfirmationActivity extends AppCompatActivity {

    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation_screen_ver_1_0);
        toolbar = findViewById(R.id.toolBar);
        setTitle("Confirm Information");
        setSupportActionBar(toolbar);
        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        databaseAccess.createTable();
        databaseAccess.close();

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
        toast.setGravity(Gravity.BOTTOM, 0, 250);
        toast.show();

        final EditText routeNumTextView = findViewById(R.id.displayRouteNumberInputEditTextView);
        routeNumTextView.setText(routeNumberString);

        final EditText custNameTextView = findViewById(R.id.displayCustomerNameInputEditTextView);
        custNameTextView.setText(customerNameString);

        final EditText custAccTextView = findViewById(R.id.displayCustomerAccountInputEditTextView);
        custAccTextView.setText(customerAccountString);

        final EditText itemNameTextView = findViewById(R.id.displayItemNameInputEditTextView);
        itemNameTextView.setText(itemNameString);

        final EditText itemBrandTextView = findViewById(R.id.displayItemBrandInputEditTextView);
        itemBrandTextView.setText(itemBrandString);

        final EditText itemPackSizeTextView = findViewById(R.id.displayItemPackSizeInputEditTextView);
        itemPackSizeTextView.setText(itemPackSizeString);

        final EditText itemFlavorTextView = findViewById(R.id.displayItemFlavorInputEditTextView);
        itemFlavorTextView.setText(itemFlavorString);

        final EditText numOfCasesTextView = findViewById(R.id.displayNoOfCasesInputEditTextView);
        numOfCasesTextView.setText(numOfCasesString);


        final Button button = findViewById(R.id.submitBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseAccess.open();
                Boolean test = databaseAccess.insertData(routeNumTextView.getText().toString(),
                        custAccTextView.getText().toString(), custNameTextView.getText().toString(),
                        itemNameTextView.getText().toString(), itemBrandTextView.getText().toString(),
                        itemPackSizeTextView.getText().toString(), itemFlavorTextView.getText().toString(),
                        numOfCasesTextView.getText().toString());
                databaseAccess.close();


                if(test == true){
                    Toast toast = Toast.makeText(    ConfirmationActivity.this, "Saved Successfully", Toast.LENGTH_SHORT);
                    Intent intent = new Intent(ConfirmationActivity.this, HomePageActivity.class);
                    startActivity(intent);
                    toast.show();
                }

                if(test == false){
                    Toast toast = Toast.makeText(    ConfirmationActivity.this, "Saved Unsuccessfully", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });


    }


}
