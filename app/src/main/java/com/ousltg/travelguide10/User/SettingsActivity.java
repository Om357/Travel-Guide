package com.ousltg.travelguide10.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ousltg.travelguide10.R;

public class SettingsActivity extends AppCompatActivity {
    Button btn2,btn3,btn4,btn5,btn6,btn7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setTitle("Travel Guide");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //create onclick
        btn2 =(Button)findViewById(R.id.btn2);
        btn3 =(Button)findViewById(R.id.btn3);
        btn4 =(Button)findViewById(R.id.btn4);
        btn5 =(Button)findViewById(R.id.btn5);
        btn6 =(Button)findViewById(R.id.btn6);
        btn7 =(Button)findViewById(R.id.btn7);

        //set onclick
        btn2.setOnClickListener(new Clik());
        btn3.setOnClickListener(new Clik());
        btn4.setOnClickListener(new Clik());
        btn5.setOnClickListener(new Clik());
        btn6.setOnClickListener(new Clik());
        btn7.setOnClickListener(new Clik());

        //BOTTOM NAVIGATION
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_settings);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.bottom_home:
                    startActivity(new Intent(getApplicationContext(), StartActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_map:
                    startActivity(new Intent(getApplicationContext(), search_map.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_settings:
                    return true;
                case R.id.bottom_menu:
                    startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });
    }
    public class Clik implements View.OnClickListener{
        @Override
        public void onClick(View v){

            switch (v.getId()){
                case R.id.btn2:
                    startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
//                    Toast.makeText(getApplicationContext(),"hi btn2",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn3:
                    startActivity(new Intent(getApplicationContext(), LanguageActivity.class));
                    break;
                case R.id.btn4:
//                    startActivity(new Intent(getApplicationContext(), AdawelaWaterfallActivity.class));
                    break;
                case R.id.btn5:
//                    startActivity(new Intent(getApplicationContext(),Activity_Privacy.class));
                    break;
                case R.id.btn6:
                    startActivity(new Intent(getApplicationContext(), TguideActivity.class));
                    break;
                case R.id.btn7:
                    startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
                    break;
                default:
            }
        }
    }
}