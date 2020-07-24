package com.ecng3020project.merchandiserstockcount;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Toolbar toolbar;
    EditText EmailEditText;
    EditText PasswordEditText;
    TextView ForgotPassword;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen_ver_1_0);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        EmailEditText = findViewById(R.id.EmailEditText);
        PasswordEditText = findViewById(R.id.PasswordEditText);
        EmailEditText.setText("admin@admin.com");
        PasswordEditText.setText("123456");
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        ForgotPassword = findViewById(R.id.ForgotPasswordTextView);

        mAuth = FirebaseAuth.getInstance();



        final Button button = findViewById(R.id.LoginBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInExistingUser();
            }
        });

        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:1234567"));
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void SignInExistingUser() {
        String email = EmailEditText.getText().toString();
        String password = PasswordEditText.getText().toString();

        //Error Handling of Email and Password
        if(email.isEmpty()){
            EmailEditText.setError("Please enter an email");
            EmailEditText.requestFocus();
            return;
        }
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches() == false){
            EmailEditText.setError("Please enter a valid email address");
            EmailEditText.requestFocus();
            return;
        }
        if(password.isEmpty()){
            PasswordEditText.setError("Please enter a password");
            PasswordEditText.requestFocus();
            return;
        }
        if(password.length() < 6){
            PasswordEditText.setError("Passwords must be at least 6 characters long");
            PasswordEditText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    Toast toast = Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    progressBar.setVisibility(View.GONE);
                    Toast toast = Toast.makeText(LoginActivity.this, "Login Unsuccessful", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

    }


}
