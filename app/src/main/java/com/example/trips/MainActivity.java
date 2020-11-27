package com.example.trips;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    CollectionReference lugarReference = null;
    FirebaseUser fireUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onStart() {
        super.onStart();
        setupFirebase();
    }

    private void setupFirebase(){
        fireUser = FirebaseAuth.getInstance().getCurrentUser();
        lugarReference = FirebaseFirestore.getInstance().collection("lugares");
    }

    public void irParaLugar(View view) {
        Intent intent = new Intent(this, LugarActivity.class);
        startActivity(intent);
    }
}