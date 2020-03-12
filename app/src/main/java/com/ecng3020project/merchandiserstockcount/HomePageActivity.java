package com.ecng3020project.merchandiserstockcount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HomePageActivity extends AppCompatActivity {

    private Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_ver_1_0);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        final Button button = findViewById(R.id.newEntryButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final Button testBtn = findViewById(R.id.testResultsBtn);
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, ResultsActivity.class);
                startActivity(intent);
            }
        });

    }
}
