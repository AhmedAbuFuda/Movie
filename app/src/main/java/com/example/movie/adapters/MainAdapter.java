package com.example.movie.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
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
import com.example.movie.MainActivity;
import com.example.movie.R;
import com.example.movie.datasource.local.db.DbConfig;
import com.example.movie.datasource.local.db.DbHelper;
import com.example.movie.model.Example;
import com.example.movie.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.movieHolder> {
    Context context;
    List<Example> examples;
    boolean flag = true;


    public MainAdapter(Context context, List<Example> examples) {
        this.context = context;
        this.examples = examples;
    }


    @NonNull
    @Override
    public movieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_main_row, parent, false);

        return new movieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull movieHolder holder, int position) {
        Example example = examples.get(position);
        holder.title.setText(example.getResults().get(position).getTitle());
        holder.rate.setText(example.getResults().get(position).getVoteAverage() + "");
        holder.date.setText(example.getResults().get(position).getReleaseDate());
        Picasso.get().load("http://image.tmdb.org/t/p/w500" + example.getResults().get(position).getPosterPath()).into(holder.main);
    }

    @Override
    public int getItemCount() {
        return examples.size();
    }

    class movieHolder extends RecyclerView.ViewHolder {
        ImageView main, love;
        RatingBar ratebar;
        TextView title, rate, date;


        public movieHolder(@NonNull View itemView) {
            super(itemView);
            main = itemView.findViewById(R.id.image_main);
            love = itemView.findViewById(R.id.main_love);
            ratebar = itemView.findViewById(R.id.main_rate_bar);
            title = itemView.findViewById(R.id.main_title);
            rate = itemView.findViewById(R.id.main_rate);
            date = itemView.findViewById(R.id.main_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DescreptionActivity.class);
                    intent.putExtra("name", examples.get(getAdapterPosition()).getResults().get(getAdapterPosition()).getTitle());
                    intent.putExtra("date", examples.get(getAdapterPosition()).getResults().get(getAdapterPosition()).getReleaseDate());
                    intent.putExtra("rate", examples.get(getAdapterPosition()).getResults().get(getAdapterPosition()).getVoteAverage() + "");
                    intent.putExtra("overView", examples.get(getAdapterPosition()).getResults().get(getAdapterPosition()).getOverview());
                    intent.putExtra("poster", "http://image.tmdb.org/t/p/w500" + examples.get(getAdapterPosition()).getResults().get(getAdapterPosition()).getPosterPath());
                    intent.putExtra("back", "http://image.tmdb.org/t/p/w500" + examples.get(getAdapterPosition()).getResults().get(getAdapterPosition()).getBackdropPath());

                    v.getContext().startActivity(intent);


                }
            });
            /*love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(flag==true)
                    {
                        ContentValues values = new ContentValues();

                            values.put(DbConfig.COL_TEXT,examples.get(getAdapterPosition()).getResults().get(getAdapterPosition()).getTitle());
                            values.put(DbConfig.COL_DATE,examples.get(getAdapterPosition()).getResults().get(getAdapterPosition()).getReleaseDate());
                            values.put(DbConfig.COL_POSTER,examples.get(getAdapterPosition()).getResults().get(getAdapterPosition()).getPosterPath());
                            long row = db.insert(DbConfig.TableName, null, values);

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("flag",false);
                        editor.putInt("image", R.drawable.true_love);
                        editor.apply();

                        int img=sharedPreferences.getInt("image",R.drawable.love);
                        love.setImageResource(img);

                        flag=sharedPreferences.getBoolean("flag",false);
                           // flag=false;
                        }


                    else {
                        Example e = examples.get(getAdapterPosition());
                        love.setImageResource(R.drawable.love);
                        flag=true;
                        int row = db.delete(DbConfig.TableName, DbConfig.COL_TEXT+ "=?", new String[]{e.getResults().get(getAdapterPosition()).getTitle()});

                    }
                }

            });
            }*/
        }
    }
}
