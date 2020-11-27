package com.example.trips;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class RegistroUsuarioActivity extends AppCompatActivity {

    private EditText nomeEditText;
    private EditText emailEditText;
    private EditText senhaEditText;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        nomeEditText = findViewById(R.id.emailEditText);
        emailEditText = findViewById(R.id.emailEditText);
        senhaEditText = findViewById(R.id.senhaEditText);
        auth = FirebaseAuth.getInstance();
    }

    public void criarNovoUsuario (View view){
        String Nome = nomeEditText.getEditableText().toString();
        String login = emailEditText.getEditableText().toString();
        String senha = senhaEditText.getEditableText().toString();
        auth.createUserWithEmailAndPassword(login, senha).addOnSuccessListener((result) -> {
            Toast.makeText(this, result.getUser().getEmail(), Toast.LENGTH_SHORT).show();
            finish();
        }).addOnFailureListener((error -> error.printStackTrace()));
    }
}