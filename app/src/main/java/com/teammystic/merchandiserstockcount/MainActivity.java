package com.teammystic.merchandiserstockcount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import java.net.Inet4Address;

public class MainActivity extends AppCompatActivity {

    //Test commit

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolBar);
        setTitle("Enter Information");
        setSupportActionBar(toolbar);

        final Button custBtn = findViewById(R.id.chooseCustBtn);
        custBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              customerBtn();
            }
        });

        final Button nameBtn = findViewById(R.id.chooseItemNameBtn);
        nameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemNameBtn();
            }
        });

        final Button brandBtn = findViewById(R.id.chooseItemBtn);
        brandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemBrandBtn();
            }
        });

        final Button packSizeBtn = findViewById(R.id.choosePackSizeBtn);
        packSizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemPackSizeBtn();
            }
        });

        final Button flavorBtn = findViewById(R.id.itemFlavorBtn);
        flavorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemFlavorBtn();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.common_menu, menu);
        return true;
    }

    private void customerBtn() {
        Intent intent = new Intent(this, CustomerNameActivity.class);
        startActivity(intent);
    }

    private void itemBrandBtn() {
        Intent intent = new Intent(this, ItemBrandActivity.class);
        startActivity(intent);
    }

    private void itemPackSizeBtn(){
        Intent intent = new Intent(this, PackSizeActivity.class);
        startActivity(intent);
    }

    private void itemFlavorBtn() {
        Intent  intent = new Intent(this, ItemFlavorActivity.class);
        startActivity(intent);
    }

    private void itemNameBtn() {
        Intent intent = new Intent(this, ItemNameActivity.class);
        startActivity(intent);
    }
}


