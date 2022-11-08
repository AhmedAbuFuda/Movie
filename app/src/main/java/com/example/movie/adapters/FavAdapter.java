package com.example.movie.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.movie.DescreptionActivity;
import com.example.movie.FavouriteActivity;
import com.example.movie.R;
import com.example.movie.datasource.local.db.DbConfig;
import com.example.movie.model.Example;
import com.example.movie.model.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.movieHolder> {
    Context context;
    ArrayList<Result> examples;

    public FavAdapter(Context context, ArrayList<Result> examples) {
        this.context = context;
        this.examples = examples;
    }

    @NonNull
    @Override
    public movieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorite_custom_row, parent, false);

        return new movieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull movieHolder holder, int position) {
        Result example = examples.get(position);
        holder.title.setText(example.getTitle());
        holder.date.setText(example.getReleaseDate());
        Picasso.get().load("http://image.tmdb.org/t/p/w500" + example.getPosterPath()).into(holder.fav);
    }

    @Override
    public int getItemCount() {
        return (examples != null) ? examples.size() : 0;
    }

    public void setDataSource(ArrayList<Result> myList) {
        this.examples = myList;
        notifyDataSetChanged();
    }

    class movieHolder extends RecyclerView.ViewHolder {
        ImageView fav, love;
        TextView title, date;

        public movieHolder(@NonNull View itemView) {
            super(itemView);
            fav = itemView.findViewById(R.id.favImageView);
            love = itemView.findViewById(R.id.hImageView);
            title = itemView.findViewById(R.id.favTextView);
            date = itemView.findViewById(R.id.fav_date);

           /* love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Result e = examples.get(getAdapterPosition()); int row = db.delete(DbConfig.TableName, DbConfig.COL_TEXT+ "=?", new String[]{e.getTitle()});

                    examples.remove(e);
                    notifyDataSetChanged();

                }
            });
        }*/
        }
    }
}

