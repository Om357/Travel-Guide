package com.ousltg.travelguide10.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ousltg.travelguide10.R;

public class MenuActivity extends AppCompatActivity {
    //DECLARED BUTTONS IN LAYOUT FILE
    Button btn1, btn2, btn3, btn4, btn5, btn6;

    //BOTTOM NAVIGATION
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().setTitle("Travel Guide");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //create onclick
       // btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);

        //set onclick
       // btn1.setOnClickListener(new Clik());
        btn2.setOnClickListener(new Clik());
        btn3.setOnClickListener(new Clik());
        btn4.setOnClickListener(new Clik());
        btn5.setOnClickListener(new Clik());
        btn6.setOnClickListener(new Clik());

        //the bottom navigation view declared in the layout file
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_settings);

        // Sets an item selected listener for the bottom navigation view to handle item selection
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    startActivity(new Intent(getApplicationContext(), StartActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_map:
                    startActivity(new Intent(getApplicationContext(), search_map.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_settings:
                    startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_menu:
                    return true;
            }
            return false;
        });
    }

    //difference cases hadaled on the clicked button
    public class Clik implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                //case R.id.btn1:
//                    startActivity(new Intent(getApplicationContext(), Activity_Accountsettings.class));
//                    Toast.makeText(getApplicationContext(),"hi btn2",Toast.LENGTH_SHORT).show();
                   // break;*
                case R.id.btn2:
                    startActivity(new Intent(getApplicationContext(),NoteTodoActivity.class));
                    break;
                case R.id.btn3:
                    startActivity(new Intent(getApplicationContext(), CompassActivity.class));
                    break;
                case R.id.btn4:
//                    startActivity(new Intent(getApplicationContext(), FavouriteActivity.class));
                    break;
                case R.id.btn5:
                    startActivity(new Intent(getApplicationContext(), TguideActivity.class));
                    break;
                default:
            }
        }
    }
}