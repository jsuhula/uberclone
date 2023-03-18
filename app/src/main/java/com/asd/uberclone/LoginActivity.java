package com.asd.uberclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.annotations.NotNull;

import java.util.Objects;

import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText email;
    TextInputEditText password;
    Button btLogin;
    FirebaseAuth auth;
    DatabaseReference data;
    AlertDialog alDialog;
    Toolbar toolbarOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbarOne = findViewById(R.id.toolbarBack);
        setSupportActionBar(toolbarOne);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Inicio de Sesion");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);
        btLogin = findViewById(R.id.goLogin);
        alDialog = new SpotsDialog.Builder().setContext(LoginActivity.this).setMessage("Espere...").build();

        auth = FirebaseAuth.getInstance();
        data = FirebaseDatabase.getInstance().getReference();

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String mail = email.getText().toString();
        String pass = password.getText().toString();
        if(mail.isEmpty() || pass.isEmpty()){
            Toast.makeText(LoginActivity.this, "Ingrese sus datos", Toast.LENGTH_SHORT).show();
        }else{
            if(password.toString().length() >= 6){
                alDialog.show();
                auth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Sesion iniciada", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(LoginActivity.this,"Sus datos no son correctos", Toast.LENGTH_SHORT).show();
                        }
                        alDialog.dismiss();
                    }
                });
            }
        }
    }
}