package com.example.brama.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String mood = intent.getStringExtra("mood");
        String content = intent.getStringExtra("content");
        String date = intent.getStringExtra("date");

        TextView titleDetail = findViewById(R.id.titleDetail);
        TextView contentDetail = findViewById(R.id.contentDetail);
        TextView dateDetail = findViewById(R.id.dateDetail);
        ImageView moodDetail = findViewById(R.id.moodDetail);

        titleDetail.setText(title);
        contentDetail.setText(content);
        dateDetail.setText(date);

        int id = getResources().getIdentifier(mood,"drawable", getPackageName());
        moodDetail.setImageResource(id);
    }
}
