package com.asd.uberclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.asd.uberclone.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;

public class RegisterActivity extends AppCompatActivity {

    SharedPreferences selectUser;
    DatabaseReference databaseUser;
    FirebaseAuth authRegister;
    TextInputEditText nameInput;
    TextInputEditText emailInput;
    TextInputEditText passwordInput;
    Button regis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regis = findViewById(R.id.registerUser);
        nameInput = findViewById(R.id.nameRegister);
        emailInput = findViewById(R.id.emailRegister);
        passwordInput = findViewById(R.id.passwordRegister);
        authRegister = FirebaseAuth.getInstance();
        databaseUser = FirebaseDatabase.getInstance("https://uberclone-bd1d5-default-rtdb.firebaseio.com/").getReference();
        selectUser = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    public void register(){
        String name = nameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        if(name.isEmpty() | email.isEmpty() | password.isEmpty()){
            Toast.makeText(RegisterActivity.this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            if(password.length() > 6){
                authRegister.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            saveUser(name, email);
                        }else{
                            Toast.makeText(RegisterActivity.this, "No se realizo el registro", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }else{
                Toast.makeText(RegisterActivity.this, "Su clave debe de tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void saveUser(String name, String email){
        String userInput = selectUser.getString("user", "NO");
        User user = new User(name, email);
        if(userInput.equalsIgnoreCase("driver")){
            databaseUser.child("users").child("drivers").push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NotNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Registro de conductor exitoso", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if(userInput.equalsIgnoreCase("client")) {
            databaseUser.child("users").child("client").push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NotNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Registro de cliente exitoso", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}