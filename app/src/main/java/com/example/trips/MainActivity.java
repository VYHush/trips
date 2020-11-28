package com.example.trips;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CollectionReference lugarReference = null;
    FirebaseUser fireUser = null;
    private List<Lugar> lugares;
    private LugarAdapter adapter;
    private RecyclerView lugaresRecyclerView;
    private EditText nomeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lugaresRecyclerView = findViewById(R.id.recycleview_lugares);
        lugares = new ArrayList<>();
        adapter = new LugarAdapter(lugares, this);
        lugaresRecyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lugaresRecyclerView.setLayoutManager(linearLayoutManager);
    }
    @Override
    protected void onStart() {
        super.onStart();
        setupFirebase();
        getLugares();
    }
    private void getLugares(){
        lugarReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                lugares.clear();
                for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                    Lugar lugar = doc.toObject(Lugar.class);
                    lugar.setId(doc.getId());
                    lugares.add(lugar);
                }
                Collections.sort(lugares);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void setupFirebase(){
        fireUser = FirebaseAuth.getInstance().getCurrentUser();
        lugarReference = FirebaseFirestore.getInstance().collection("lugares");
    }

    public void irParaLugar(View view) {
        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(this, LugarActivity.class);
            startActivity(intent);
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

    }
}