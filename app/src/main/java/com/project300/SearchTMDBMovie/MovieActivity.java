package com.project300.SearchTMDBMovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends AppCompatActivity {

    //    private static String JSON_URL = "https://api.themoviedb.org/3/movie/popular?api_key=8099f5720bad1f61f020fdbc855f73db";
    private static String JSON_URL_TEMPLATE = "https://api.themoviedb.org/3/search/movie?api_key=8099f5720bad1f61f020fdbc855f73db&query=";
    private static String JSON_URL;

    private static String userInput;
    List<MovieModelClass> movieList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        userInput = getIntent().getStringExtra("userInput");

        JSON_URL = JSON_URL_TEMPLATE + userInput;

        movieList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        MovieActivity.GetData getData = new MovieActivity.GetData();
        getData.execute();
    }

    public class GetData extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            String current = "";

            try{
                URL url;
                HttpURLConnection urlConnection = null;

                try{

                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    int data = isr.read();
                    while(data != -1){
                        current +=(char) data;
                        data = isr.read();
                    }
                    return current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }
        @Override
        protected void onPostExecute(String s){
            try{
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for(int i = 0; i< jsonArray.length(); i++){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);


                    MovieModelClass model = new MovieModelClass();
                    model.setId(jsonObject1.getString("vote_average"));
                    model.setName(jsonObject1.getString("title"));
                    model.setImg(jsonObject1.getString("poster_path"));

                    movieList.add(model);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            PutDataIntoRecyclerView(movieList);
        }

        private void PutDataIntoRecyclerView(List<MovieModelClass> movieList){

            Adapter adapter = new Adapter(MovieActivity.this, movieList);
            recyclerView.setLayoutManager(new LinearLayoutManager(MovieActivity.this));

            recyclerView.setAdapter(adapter);
        }
    }


}