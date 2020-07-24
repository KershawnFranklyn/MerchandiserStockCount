package com.ecng3020project.merchandiserstockcount;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomePageActivity extends AppCompatActivity {

    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_ver_1_0);
        toolbar = findViewById(R.id.toolBar);
        setTitle("Please Start Stock Check Process");
        setSupportActionBar(toolbar);

        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(HomePageActivity.this);

        DatabaseReference rootReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference barcodeReference = rootReference.child("Barcode_Info");
        DatabaseReference customerReference = rootReference.child("Customer_Info");
        DatabaseReference itemReference = rootReference.child("Item_Info");
        DatabaseReference orderReference = rootReference.child("Order_Info");
        DatabaseReference orderLineReference = rootReference.child("Order_Line");

        final Button button = findViewById(R.id.newEntryButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        barcodeReference.addChildEventListener(new ChildEventListener() {
               @Override
               public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                   BarcodeData barcodeData = snapshot.getValue(BarcodeData.class);
                   if (databaseOpenHelper.BarcodeIDExist(barcodeData.getBarcode_ID()) == true) {
                       Log.i("Child Added", "Value does not exist in Barcode_Info");
                       databaseAccess.open();
                       Boolean readBarcodeBoolean = databaseAccess.insertReadBarcodeCloudData(barcodeData.getBarcode_ID(), barcodeData.getItem_ID(), barcodeData.getWarehouse(),
                               barcodeData.getExpiry_Date(), barcodeData.getUnit_Of_Measure(), barcodeData.getBatch_No());
                       databaseAccess.close();
                   }
               }

               @Override
               public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
               }

               @Override
               public void onChildRemoved(@NonNull DataSnapshot snapshot) {
               }

               @Override
               public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {
                   Toast.makeText(HomePageActivity.this, "Error Connecting to the Servers. Please Contact Support", Toast.LENGTH_SHORT).show();
               }
           }
        );

        customerReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                CustomerData customerData = snapshot.getValue(CustomerData.class);
                if(databaseOpenHelper.CustomerAccountNoExist(customerData.getCustomer_Account_No()) == true){
                    Log.i("Child Added", "Value does not exist in Customer_Info");
                    databaseAccess.open();
                    Boolean readCustomerBoolean = databaseAccess.insertReadCustomerCloudData(customerData.getCustomer_Account_No(), customerData.getRoute_No(), customerData.getCustomer_Name());
                    databaseAccess.close();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        itemReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ItemData itemData = snapshot.getValue(ItemData.class);
                if(databaseOpenHelper.ItemIDExist(itemData.getItem_ID()) == true){
                    Log.i("Child Added", "Value does not exist in Item_Info");
                    databaseAccess.open();
                    Boolean readItemBoolean = databaseAccess.insertReadItemCloudData(itemData.getItem_ID(), itemData.getItem_Brand(), itemData.getItem_Pack_Size(), itemData.getFlavor(), itemData.getItem_Name());
                    databaseAccess.close();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        orderReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                OrderData orderData = snapshot.getValue(OrderData.class);
                if(databaseOpenHelper.OrderIDExist(orderData.getOrder_ID()) == true){
                    Log.i("Child Added", "Value does not exist in Order_Info");
                    databaseAccess.open();
                    Boolean readOrderBoolean = databaseAccess.insertReadOrderCloudData(orderData.getOrder_ID(), orderData.getOrder_Date(), orderData.getCustomer_Account_No());
                    databaseAccess.close();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        orderLineReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                OrderLineData orderLineData = snapshot.getValue(OrderLineData.class);
                if(databaseOpenHelper.OrderLineExist(orderLineData.getOrder_ID(), orderLineData.getItem_ID()) == true){
                    Log.i("Child Added", "Value does not exist in Order_Line");
                    databaseAccess.open();
                    Boolean readOrderLineBoolean = databaseAccess.insertReadOrderLineCloudData(orderLineData.getOrder_ID(), orderLineData.getItem_ID(), orderLineData.getNo_Of_Cases());
                    databaseAccess.close();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



}
