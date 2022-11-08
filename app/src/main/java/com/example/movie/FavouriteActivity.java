package com.example.movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movie.adapters.FavAdapter;
import com.example.movie.adapters.MainAdapter;
import com.example.movie.datasource.local.db.DbConfig;
import com.example.movie.datasource.local.db.DbHelper;
import com.example.movie.model.Example;
import com.example.movie.model.Result;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class FavouriteActivity extends AppCompatActivity {
    TextView title,date;
    ImageView poster,main;
    private FavAdapter adapter;
    ArrayList<Result> examples;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        initComponent();

        DbHelper helper = new DbHelper(this);
         db =  helper.getReadableDatabase();
         getAllNotes(db);
    }

    private void initComponent() {
        title=findViewById(R.id.favTextView);
        date=findViewById(R.id.fav_date);
        poster=findViewById(R.id.favImageView);
        main=findViewById(R.id.hImageView);

    }

    private List<Result> getAllNotes(SQLiteDatabase db){
        examples=new ArrayList<Result>();
        String [] cols = {DbConfig.COL_ID, DbConfig.COL_TEXT , DbConfig.COL_DATE, DbConfig.COL_POSTER};
        Cursor cursor = db.query(DbConfig.TableName, cols, null, null, null, null, DbConfig.COL_DATE + " desc");
        if(cursor.moveToFirst()){
            do{
                int idPos = cursor.getColumnIndex(DbConfig.COL_ID);
                int noteId = cursor.getInt(idPos);

                String noteText = cursor.getString(cursor.getColumnIndex(DbConfig.COL_TEXT));
                String noteDate = cursor.getString(cursor.getColumnIndex(DbConfig.COL_DATE));
                String notePoster = cursor.getString(cursor.getColumnIndex(DbConfig.COL_POSTER));
                Log.i("notetext",noteText);

                examples.add(new Result(noteText,notePoster,noteDate));


            }
            while(cursor.moveToNext());
            Set<Result> set = new LinkedHashSet<>();
            set.addAll(examples);
            examples.clear();
            examples.addAll(set);
        }

        RecyclerView favRecyclerView = findViewById(R.id.favRV);
        adapter=new FavAdapter(FavouriteActivity.this, examples);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(FavouriteActivity.this,1);
        favRecyclerView.setLayoutManager(gridLayoutManager);
        favRecyclerView.setAdapter(adapter);

        return examples;
    }

}