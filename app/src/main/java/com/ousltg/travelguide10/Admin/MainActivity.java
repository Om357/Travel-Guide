package com.ousltg.travelguide10.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ousltg.travelguide10.Comman.splashActivity;
import com.ousltg.travelguide10.R;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goSplash();
    }

    public void goSplash() {
        Intent i = new Intent(this, splashActivity.class);
        startActivity(i);
    }
}