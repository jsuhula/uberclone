package com.asd.uberclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnIamDriver;
    Button btnIamClient;
    SharedPreferences preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnIamDriver = findViewById(R.id.selectDriver);
        btnIamClient = findViewById(R.id.selectClient);
        preference = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
        SharedPreferences.Editor edit = preference.edit();

        this.btnIamDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.putString("user", "driver");
                edit.apply();
                goToSelectAuth();
            }
        });
        btnIamClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.putString("user", "client");
                edit.apply();
                goToSelectAuth();
            }
        });
    }
    private void goToSelectAuth() {
        Intent intent = new Intent(this, SelectOptionAuthActivity.class);
        startActivity(intent);
    }
}