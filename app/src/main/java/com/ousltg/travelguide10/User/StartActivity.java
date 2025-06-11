package com.ousltg.travelguide10.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ousltg.travelguide10.R;

public class StartActivity extends AppCompatActivity{
    //declared variable for button
    ImageButton imageBtn,imageBtn2,imageBtn3,imageBtn4,imageBtn5,imageBtn6,imageBtn7,imageBtn8,imageBtn9,imageBtn10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getSupportActionBar().setTitle("Travel Guide");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //BOTTOM NAVIGATION
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation); //retrieves the BottomNavigationView from the layout file by using its id
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    return true;
                case R.id.bottom_map:
                    startActivity(new Intent(getApplicationContext(), My_Map.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_settings:
                    startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_menu:
                    startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });

        //NAVIGATE PROVINCE ACTIVITY

        imageBtn = (ImageButton) findViewById(R.id.imageBtn);
        imageBtn2 = (ImageButton) findViewById(R.id.imageBtn2);
        imageBtn3 = (ImageButton) findViewById(R.id.imageBtn3);
        imageBtn4 = (ImageButton) findViewById(R.id.imageBtn4);
        imageBtn5 = (ImageButton) findViewById(R.id.imageBtn5);
        imageBtn6 = (ImageButton) findViewById(R.id.imageBtn6);
        imageBtn7 = (ImageButton) findViewById(R.id.imageBtn7);
        imageBtn8 = (ImageButton) findViewById(R.id.imageBtn8);
        imageBtn9 = (ImageButton) findViewById(R.id.imageBtn9);
        imageBtn10 = (ImageButton) findViewById(R.id.imageBtn10);

        //set onclick
        imageBtn.setOnClickListener(new Clik());
        imageBtn2.setOnClickListener(new Clik());
        imageBtn3.setOnClickListener(new Clik());
        imageBtn4.setOnClickListener(new Clik());
        imageBtn5.setOnClickListener(new Clik());
        imageBtn6.setOnClickListener(new Clik());
        imageBtn7.setOnClickListener(new Clik());
        imageBtn8.setOnClickListener(new Clik());
        imageBtn9.setOnClickListener(new Clik());
        imageBtn10.setOnClickListener(new Clik());
    }

    //NAVIGATE PROVINCE

    public class Clik implements View.OnClickListener{
        @Override
        public void onClick(View v){

            switch (v.getId()){
                case R.id.imageBtn:
                    startActivity(new Intent(getApplicationContext(), SrilankaActivity.class));
//                    Toast.makeText(getApplicationContext(),"hi btn2",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.imageBtn2:
                    startActivity(new Intent(getApplicationContext(), WesternActivity.class));
                    break;
                case R.id.imageBtn3:
                    startActivity(new Intent(getApplicationContext(), varanasi_package.class));
                    break;
                case R.id.imageBtn4:
                    startActivity(new Intent(getApplicationContext(), jaipur_package.class));
                    break;
                case R.id.imageBtn5:
                    startActivity(new Intent(getApplicationContext(), ladakh_package.class));
                    break;
                case R.id.imageBtn6:
                    startActivity(new Intent(getApplicationContext(),kerela_package.class));
                    break;
                case R.id.imageBtn7:
                    startActivity(new Intent(getApplicationContext(),mysore_package.class));
                    break;
                case R.id.imageBtn8:
                    startActivity(new Intent(getApplicationContext(),ahmedabad_package.class));
                    break;
                case R.id.imageBtn9:
                    startActivity(new Intent(getApplicationContext(),mumbai_package.class));
                    break;
                case R.id.imageBtn10:
                    startActivity(new Intent(getApplicationContext(), goa_package.class));
                    break;
                default:
            }
        }
    }
}