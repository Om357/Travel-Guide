package com.ousltg.travelguide10.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ousltg.travelguide10.R;

public class AdawelaWaterfallActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adawela_waterfall);

        Button openActivityButton = findViewById(R.id.taj_button);

        openActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the SecondActivity
                Intent intent = new Intent(AdawelaWaterfallActivity.this, Payment_gpay.class);
                startActivity(intent);
            }
        });

    }
}