package com.example.trips;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class RegistroUsuarioActivity extends AppCompatActivity {

    private EditText NomeNovoUsuarioEditText;
    private EditText loginNovoUsuarioEditText;
    private EditText senhaNovoUsuarioEditText;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        NomeNovoUsuarioEditText = findViewById(R.id.NomeNovoUsuarioEditText);
        loginNovoUsuarioEditText = findViewById(R.id.loginNovoUsuarioEditText);
        senhaNovoUsuarioEditText = findViewById(R.id.senhaNovoUsuarioEditText);
        auth = FirebaseAuth.getInstance();
    }

    public void criarNovoUsuario (View view){
        String Nome = NomeNovoUsuarioEditText.getEditableText().toString();
        String login = loginNovoUsuarioEditText.getEditableText().toString();
        String senha = senhaNovoUsuarioEditText.getEditableText().toString();
        auth.createUserWithEmailAndPassword(login, senha).addOnSuccessListener((result) -> {
            Toast.makeText(this, result.getUser().getEmail(), Toast.LENGTH_SHORT).show();
            finish();
        }).addOnFailureListener((error -> error.printStackTrace()));
    }
}