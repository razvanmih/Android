package com.example.razva.searchshow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView searchResult;
    TextView log;
    ApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //log = findViewById(R.id.log);
        //log.setMovementMethod(new ScrollingMovementMethod());
        searchResult = findViewById(R.id.searchResultList);


        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        client = retrofit.create(ApiClient.class);

        //Call<SearchResponse>  call =client.searchTV("Breaking",1);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.searchView);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                search(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    protected void search(String query){
        Call<SearchResponse>  call =client.searchTV(getString(R.string.api_key),getString(R.string.api_language),query,1);



        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                SearchResponse repos = response.body();


                ArrayList<TvShow> shows = repos.getShows();
                //log.setText(shows.get(0).getPoster_path());

                ArrayList<String> temp = new ArrayList<>();

                ListAdapter listAdapter = new TvShowAdapter(MainActivity.this, shows);

                searchResult.setAdapter(listAdapter);

            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }


}
