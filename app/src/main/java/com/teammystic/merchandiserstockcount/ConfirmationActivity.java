package com.teammystic.merchandiserstockcount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        setTitle("Enter Information");
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String routeNumberString = intent.getStringExtra("routeNumberIntent");
        String customerNameString = intent.getStringExtra("customerNameIntent");
        String customerAccountString = intent.getStringExtra("customerAccountIntent");
        String itemNameString = intent.getStringExtra("itemNameIntent");
        String itemBrandString = intent.getStringExtra("itemBrandIntent");

        //Toast toast = Toast.makeText(ConfirmationActivity.this,routeNumberString , Toast.LENGTH_SHORT);
        //toast.show();

        TextView routeNumTextView = findViewById(R.id.displayRouteNumberInputTextView);
        routeNumTextView.setText(routeNumberString);

        TextView custNameTextView = findViewById(R.id.displayCustomerNameInputTextView);
        custNameTextView.setText(customerNameString);

        TextView custAccTextView = findViewById(R.id.displayCustomerAccountInputTextView);
        custAccTextView.setText(customerAccountString);

        TextView itemNameTextView = findViewById(R.id.displayItemNameInputTextView);
        itemNameTextView.setText(itemNameString);

        TextView itemBrandTextView = findViewById(R.id.displayItemBrandInputTextView);
        itemBrandTextView.setText(itemBrandString);

    }
}
