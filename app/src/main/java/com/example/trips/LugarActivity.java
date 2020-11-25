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
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class LugarActivity extends AppCompatActivity implements LocationListener {

    LocationRequest mLocationRequest;
    Criteria criteria;
    protected LocationManager locationManager;
    String bestProvider;
    String longitude = "";
    String latitude = "";
    FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

    }

    public void ativar(View view) {
        if (ActivityCompat.checkSelfPermission(LugarActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            ActivityCompat.requestPermissions(LugarActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
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
        Toast.makeText(this, String.valueOf(location.getLatitude()), Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onLocationChanged(Location location) {


    }
}