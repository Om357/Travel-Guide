package com.ousltg.travelguide10.User;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.ousltg.travelguide10.R;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {

    Spinner spinner;
    public static final String[] languages = {"Select Language","English","Greek","Sinhala","Spanish","Tamil"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        getSupportActionBar().setTitle("Travel Guide");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLang = parent.getItemAtPosition(position).toString();
                if (selectedLang.equals("English")) {
                    setLocale(LanguageActivity.this, "en");
                    recreateActivity();
                } else if (selectedLang.equals("greek")) {
                    setLocale(LanguageActivity.this, "el");
                    recreateActivity();
                } else if (selectedLang.equals("Sinhala")) {
                    setLocale(LanguageActivity.this, "si");
                    recreateActivity();
                } else if (selectedLang.equals("Spanish")) {
                    setLocale(LanguageActivity.this, "es");
                    recreateActivity();
                } else if (selectedLang.equals("Tamil")) {
                    setLocale(LanguageActivity.this, "ta");
                    recreateActivity();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setLocale(Activity activity, String langCode) {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    private void recreateActivity() {
        finish();
        startActivity(getIntent());
    }
}


//package com.ousltg.travelguide10.User;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.Activity;
//import android.content.res.Configuration;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Spinner;
//
//import com.ousltg.travelguide10.R;
//
//import java.util.Locale;
//
//public class LanguageActivity extends AppCompatActivity {
//
//    Spinner spinner;
//    public static final String[] languages = {"Select Language","English","Sinhala","Hindi"}
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_language);
//        getSupportActionBar().setTitle("Travel Guide");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        spinner = findViewById(R.id.spinner);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,languages);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setSelection(0);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String selectedLang = parent.getItemAtPosition(position).toString();
//                if ((selectedLang.equals("English"))){
//                    setLocal(LanguageActivity.this,"hi");
//                    finish();
//                    startActivity(getIntent());
//                }else if(selectedLang.equals("sinhala")){
//                    setLocal(LanguageActivity.this,"hi");
//                    finish();
//                    startActivity(getIntent());
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//
//        public void setLocal(Activity activity,String langCode){
//            Locale locale = new Locale(langcode);
//            locale.setDefault(locale);
//            Resourses resourses = activity.getResources();
//            Configuration config = resourses.getConfiguration();
//            config.setLocale(locale);
//            resourses.updateConfiguration(config.resources.getDisplayMetrics());
//
//        }
//    }
//}