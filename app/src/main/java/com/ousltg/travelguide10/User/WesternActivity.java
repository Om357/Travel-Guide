package com.ousltg.travelguide10.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ousltg.travelguide10.R;

import java.util.ArrayList;
import java.util.List;

public class WesternActivity extends AppCompatActivity {

    //CREATE VARIABLE FOR LIST AND SEARCHVIEW
    ListView listview;
    SearchView searchView;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_western);
        getSupportActionBar().setTitle("Travel Guide");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //CREATE OBJECT FOR LISTVIEW AND SEARCHVIEW
        listview = findViewById(R.id.listview);
        searchView = findViewById(R.id.search_bar);

        //array of destination names is created (value) to populate the ListView.
        String[] value = new String[]{
                "Taj Mahal", "Jaipur", "Varanasi", "Himalayan", "Shimla", "Manali",
                "Amritsar", "Kashmir", "Delhi", "Ladakh"
        };

        //initialized with the array of destination names and set as the adapter for the ListView.
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, value);
        listview.setAdapter(arrayAdapter);

        //handle item clicks in the ListView
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(view.getContext(),AdawelaWaterfallActivity.class);
                    startActivity(intent);
                }

                if (position == 1) {
                    Intent intent = new Intent(view.getContext(),GanagaramayaActivity.class);
                    startActivity(intent);
                }

//                if (position == 2){
//                    Intent intent = new Intent(view.getContext(),DeyyangalaDolaActivity.class);
//                    startActivity(intent);
//                }
            }
        });

        // SEARCHVIEW SET FILTER

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            // user submits a query or changes the text, the arrayAdapter is filtered to display only the matching destinations in the ListView.
            public boolean onQueryTextSubmit(String query) {
                WesternActivity.this.arrayAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                WesternActivity.this.arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}
