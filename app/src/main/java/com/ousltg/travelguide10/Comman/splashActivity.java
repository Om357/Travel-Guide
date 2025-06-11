package com.ousltg.travelguide10.Comman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.VideoView;

import com.ousltg.travelguide10.R;
import com.ousltg.travelguide10.User.SignUpActivity;
import com.ousltg.travelguide10.User.StartActivity;

import java.util.Timer;
import java.util.TimerTask;

public class splashActivity extends AppCompatActivity {

    Timer timer;

    private long delay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        VideoView videoView = findViewById(R.id.videoView);
        videoView.setVideoPath(("android.resource://"+getPackageName()+"/"+R.raw.splash));
        videoView.start();
        getSupportActionBar().hide();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                goSignUP();
            }
        }, 5000); // delay 5 seconds
    }

    public void goSignUP() {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }
}