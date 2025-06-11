package com.ousltg.travelguide10.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.ousltg.travelguide10.R;

public class My_Map extends AppCompatActivity {
    WebView mapwebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_map);
        getSupportActionBar().setTitle("Travel Guide");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mapwebview=findViewById(R.id.webViewMap);
        mapWebView();
    }

    private void mapWebView() {
        mapwebview.getSettings().setJavaScriptEnabled(true);
        mapwebview.getSettings().setGeolocationEnabled(true);
        mapwebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mapwebview.loadUrl("https://www.google.com/maps/search/");
        mapwebview.setWebChromeClient(new WebChromeClient() {
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }
        });

    }
}