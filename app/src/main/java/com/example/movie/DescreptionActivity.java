package com.example.movie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DescreptionActivity extends AppCompatActivity {
    TextView descreption,title,date,rate,overview;
    ImageView poster,main;
    ImageView back;
    String movieName,overviewData,dateDetails,rateDetails,posterDetails,backDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descreption);
        initComponent();
        featchSpesificMovie();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initComponent() {
        descreption=findViewById(R.id.desc_text);
        title=findViewById(R.id.desc_title);
        date=findViewById(R.id.desc_date);
        rate=findViewById(R.id.desc_rate);
        overview=findViewById(R.id.desc_overview);
        poster=findViewById(R.id.desc_poster);
        main=findViewById(R.id.desc_image);
        back=findViewById(R.id.desc_back);
    }

    public void featchSpesificMovie() {
        movieName=getIntent().getStringExtra("name");
        title.setText(movieName);
        descreption.setText(movieName);
        dateDetails=getIntent().getStringExtra("date");
        date.setText(dateDetails);
        posterDetails=getIntent().getStringExtra("poster");
        Picasso.get().load(posterDetails).into(poster);
        backDetails=getIntent().getStringExtra("back");
        Picasso.get().load(backDetails).into(main);
        overviewData=getIntent().getStringExtra("overView");
        overview.setText(overviewData);
        rateDetails=getIntent().getStringExtra("rate");
        rate.setText(rateDetails);

       // Toast.makeText(DescreptionActivity.this, movieName, Toast.LENGTH_SHORT).show();
    }

}