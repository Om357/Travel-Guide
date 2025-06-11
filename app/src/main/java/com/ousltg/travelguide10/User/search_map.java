package com.ousltg.travelguide10.User;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ousltg.travelguide10.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class search_map extends AppCompatActivity implements OnMapReadyCallback {

    // permission to access the device's location.
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private GoogleMap mMap; //declared variable type google map
    private SearchView mSearchView; //variable for searchview
    private Button mLocationButton; //variable for button retrive the current location search

    private LocationManager mLocationManager; //variable is of type LocationManager and will be used to manage location-related operations,
    private Location mLastKnownLocation; //variable is of type Location and will store the last known location of the device.
    private LatLng mCurrentLatLng; // variable is of type LatLng and represents the latitude and longitude coordinates

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_map);
        getSupportActionBar().setTitle("Travel Guide");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSearchView = findViewById(R.id.mapSearch);
        mLocationButton = findViewById(R.id.currentLocationButton);

        //initialize the Google Map and obtain a reference to it in the onMapReady callback method.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // user input for location search.
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = mSearchView.getQuery().toString();
                List<Address> addressList = null;

                //geocoder used the coordinates (latitude and longitude) of the searched location
                if (location != null && !location.equals("")) {
                    Geocoder geocoder = new Geocoder(search_map.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (addressList != null && !addressList.isEmpty()) {
                        Address address = addressList.get(0);
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                    } else {
                        Toast.makeText(search_map.this, "Location not found", Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //allows the user to get their current location
        mLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocationPermission();
            } // method checks if the ACCESS_FINE_LOCATION permission is granted
        });
    }

    // the map is ready, and it calls the getLocationPermission() method
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //getLocationPermission();
    }

    //LOCATION PERMISSION
    //checks if the ACCESS_FINE_LOCATION permission is granted
    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getDeviceLocation();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    //GET DEVICE LOCATION
    //to retrieve the device's last known location using the LocationManager
    private void getDeviceLocation() {
        try {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLastKnownLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (mLastKnownLocation != null) {
                    mCurrentLatLng = new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(mCurrentLatLng).title("Current Location"));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mCurrentLatLng, 15));
                } else {
                    Toast.makeText(this, "Cannot retrieve location. Please make sure location is enabled on the device.", Toast.LENGTH_SHORT).show();
                }
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
}