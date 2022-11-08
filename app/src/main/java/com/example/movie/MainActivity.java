package com.example.movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movie.datasource.local.db.DbHelper;
import com.example.movie.datasource.local.db.DbConfig;
import com.example.movie.adapters.MainAdapter;
import com.example.movie.datasource.remote.IMovieAPI;
import com.example.movie.datasource.remote.RetrofitClient;
import com.example.movie.model.Example;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;
    MainAdapter adapter;
    ArrayList<Example> examples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
        featchMovie();

    }

    private void initComponent() {
        recyclerView=findViewById(R.id.recycler);
        examples=new ArrayList<>();

    }

    public void featchMovie(){

        IMovieAPI iMovieAPI= RetrofitClient.cteareAPI();
        Call<Example> call = iMovieAPI.fetchMovies("d032214048c9ca94d788dcf68434f385");
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.d(TAG, "onResponse: " + response.message() + " " + response.code());
                Log.d(TAG, "onResponse: "+response.raw().request().url().toString());
                if(response.isSuccessful()) {
                  for(int i =0;i<20;i++){
                      examples.add(response.body());
                  }
                    adapter=new MainAdapter(MainActivity.this,examples);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,2);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setAdapter(adapter);

                    Log.d(TAG, "onResponse: "+response.body().getResults().toString());

                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage() );

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mnu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent=new Intent(MainActivity.this,FavouriteActivity.class);
                startActivity(intent);
        }
            return true;
    }


}