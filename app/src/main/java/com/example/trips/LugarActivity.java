package com.example.trips;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LugarActivity extends AppCompatActivity implements LocationListener {

    Criteria criteria;
    protected LocationManager locationManager;
    String bestProvider;
    FusedLocationProviderClient fusedLocationProviderClient;
    CollectionReference lugarReference = null;
    EditText nome = findViewById(R.id.nomeLugarEditText);
    EditText latitude = findViewById(R.id.latitudeEditText);
    EditText longitude = findViewById(R.id.longitudeEditText);
    EditText dataCadastro = findViewById(R.id.dataCadastroEditText);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation();
        Date dataHoraAtual = new Date();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        dataCadastro.setText(data);
    }


    @SuppressLint("MissingPermission")
    private void getLocation() {
        locationManager = (LocationManager)  getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true));
        locationManager.requestLocationUpdates(bestProvider, 1000, 0, this::onLocationChanged);
        Location location = locationManager.getLastKnownLocation(bestProvider);
        System.out.println(location.getLatitude());
        System.out.println(location.getLongitude());
        latitude.setText(String.valueOf(location.getLatitude()));
        longitude.setText(String.valueOf(location.getLongitude()));
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onLocationChanged(Location location) {


    }

    public void cadastrar(View view) {
        /*if (ActivityCompat.checkSelfPermission(LugarActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            ActivityCompat.requestPermissions(LugarActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }*/
        lugarReference = FirebaseFirestore.getInstance().collection("lugares");
        Lugar l = new Lugar(nome.getText().toString(), latitude.getText().toString(), longitude.getText().toString(), dataCadastro.getText().toString());
        lugarReference.add(l);
    }
}