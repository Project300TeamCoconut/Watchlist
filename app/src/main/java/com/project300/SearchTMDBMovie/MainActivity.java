package com.project300.SearchTMDBMovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //    private static String JSON_URL = "https://api.themoviedb.org/3/movie/popular?api_key=8099f5720bad1f61f020fdbc855f73db";
    private static String JSON_URL_TEMPLATE = "https://api.themoviedb.org/3/search/movie?api_key=8099f5720bad1f61f020fdbc855f73db&query=";
    private static String JSON_URL, userInput, test;

    //EditText etMovieName;
    TextView tvURLTest;
    List<MovieModelClass> movieList;
    RecyclerView recyclerView;
     SearchView etMovieName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // etMovieName = findViewById(R.id.etMovieName);
//        tvURLTest = findViewById(R.id.tvURLTest);

        etMovieName = findViewById(R.id.svMovieName);


       // perform set on query text listener event
        etMovieName.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
           // do something on text submit

                //Intent MoviePage = new Intent(view.getContext(), MovieActivity.class);
                // userInput = etMovieName.getText().toString();
              //  Intent MoviePage = new Intent(view.getContext(), MovieActivity.class);

                userInput = etMovieName.getQuery().toString();
                Intent intent = new Intent(getApplicationContext(), MovieActivity.class);
               // MoviePage.putExtra("userInput", userInput);
                intent.putExtra("userInput", userInput);
                startActivity(intent);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // do something when text changes
                return false;
            }
        });

    }

/*
    //searchView.getQuery()
    public void searchMovie(View view) {
        Intent MoviePage = new Intent(view.getContext(), MovieActivity.class);
       // userInput = etMovieName.getText().toString();
        userInput = etMovieName.getQuery().toString();
        MoviePage.putExtra("userInput", userInput);
//        tvURLTest.setText(JSON_URL_TEMPLATE + userInput);
        startActivity(MoviePage);
    }

 */
}