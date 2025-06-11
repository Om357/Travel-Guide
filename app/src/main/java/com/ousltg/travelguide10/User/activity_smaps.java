package com.ousltg.travelguide10.User;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ousltg.travelguide10.R;
import com.ousltg.travelguide10.databinding.ActivitySmapsBinding;

public class activity_smaps extends FragmentActivity implements OnMapReadyCallback {

    // Reference to a GoogleMap object used to interact with Google Maps functionality.
    private GoogleMap mMap;
    // Auto-generated binding class for the Smaps activity, providing access to UI elements
    private ActivitySmapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySmapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //REQUEST RUNTIME PERMISSIONS
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        == PackageManager.PERMISSION_GRANTED){
            mMap.setMyLocationEnabled(true);
        }

        // Add a marker in sri lanka and move the camera
        LatLng srilanka = new LatLng(7.677561540853112, 80.47210514981471);
        mMap.addMarker(new MarkerOptions().position(srilanka).title("Marker in Sri Lanka"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(srilanka,18.0f));
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }
}