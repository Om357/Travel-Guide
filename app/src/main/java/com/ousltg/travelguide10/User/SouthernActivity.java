package com.ousltg.travelguide10.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ousltg.travelguide10.R;

public class SouthernActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_southern);
        getSupportActionBar().setTitle("Travel Guide");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}