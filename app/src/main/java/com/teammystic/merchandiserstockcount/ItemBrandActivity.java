package com.teammystic.merchandiserstockcount;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ItemBrandActivity extends AppCompatActivity {

    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brand_search_screen_1);
        this.linearLayout1 = findViewById(R.id.itemBrandLinearLayout);
        this.linearLayout2 = findViewById(R.id.selectBrandBtnLinearLayout);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<String> itemBrand = databaseAccess.getBrand();
        databaseAccess.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.default_text_style, itemBrand);

        int count = adapter.getCount();
        for(int i = 0; i < count; i++){
            Button button = new Button(this);
            button.setText("Select");
            button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectBtn();
                }
            });

            linearLayout1.addView(adapter.getView(i,null, linearLayout1));
            linearLayout2.addView(button);
        }
    }

    private void selectBtn() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
