package com.ecng3020project.merchandiserstockcount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class LoginActivity extends AppCompatActivity {

    private Toolbar toolbar;
    EditText UsernameEditText;
    EditText PasswordEditText;
    Boolean UsernameEnteredCheck = false;
    Boolean PasswordEnteredCheck = false;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen_ver_1_0);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        UsernameEditText = findViewById(R.id.UserNameEditText);
        PasswordEditText = findViewById(R.id.PasswordEditText);

        final Button button = findViewById(R.id.LoginBtn);
        //button.setEnabled(false);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
    }


}
