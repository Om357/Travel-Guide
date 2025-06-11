package com.ousltg.travelguide10.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ousltg.travelguide10.R;

public class kerela_package extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kerela_package);

        Button openActivityButton = findViewById(R.id.kerela_pkg_btn);

        openActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the SecondActivity
                Intent intent = new Intent(kerela_package.this, Payment_gpay.class);
                startActivity(intent);
            }
        });
    }
}