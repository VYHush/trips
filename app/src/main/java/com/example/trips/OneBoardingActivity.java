package com.example.trips;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OneBoardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_boarding_activity);
    }

    public void irParaCadastro(View view) {
        Intent intent = new Intent(this, RegistroUsuarioActivity.class);
        startActivity(intent);
    }
}