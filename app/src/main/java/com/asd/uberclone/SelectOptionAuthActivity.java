package com.asd.uberclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class SelectOptionAuthActivity extends AppCompatActivity {

    Toolbar toolbarOne;
    Button btnRegister;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_option_auth);
        toolbarOne = findViewById(R.id.toolbarBack);
        setSupportActionBar(toolbarOne);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Selecciona una opcion");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnRegister = findViewById(R.id.optionRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSelectOption(RegisterActivity.class);
            }
        });
        btnLogin = findViewById(R.id.optionLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSelectOption(LoginActivity.class);
            }
        });


    }

    private void goToSelectOption(Class option) {
        Intent intent = new Intent(SelectOptionAuthActivity.this, option);
        startActivity(intent);
    }
}