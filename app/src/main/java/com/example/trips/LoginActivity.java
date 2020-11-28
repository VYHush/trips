package com.example.trips;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEditText;
    private EditText senhaEditText;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginEditText = findViewById(R.id.emailEditText);
        senhaEditText = findViewById(R.id.senhaEditText);
        auth = FirebaseAuth.getInstance();
    }

    public void irParaCadastro (View view){
        startActivity(new Intent(this, RegistroUsuarioActivity.class));
    }

    
    public void fazerLogin (View v){
        String login = loginEditText.getEditableText().toString();
        String senha = senhaEditText.getEditableText().toString();
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        auth.signInWithEmailAndPassword(login, senha).
                addOnSuccessListener((result) -> {
                    startActivity(new Intent (this, MainActivity.class));
                }).
                addOnFailureListener((error) -> {
                    Toast.makeText(this, "Usuário ou senha inválidos", Toast.LENGTH_LONG).show();
                });
    }

    public void irParaOneBoarding(View view) {
        Intent intent = new Intent(this, OneBoardingActivity.class);
        startActivity(intent);
    }
}