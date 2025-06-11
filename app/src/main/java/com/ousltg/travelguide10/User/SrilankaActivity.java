package com.ousltg.travelguide10.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.ousltg.travelguide10.R;

public class SrilankaActivity extends AppCompatActivity {

    Button showMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sri_lanka);
        getSupportActionBar().setTitle("Travel Guide");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //VIDEOVIEW

        VideoView videoView = findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.srilanka);
        videoView.start();

        //NAVIGATE SRI LANKA ACTIVITY
        showMap =findViewById(R.id.showMap);

        showMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SrilankaActivity.this, activity_smaps.class);
                startActivity(intent);
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoView.start();
            }
        });
    }
}