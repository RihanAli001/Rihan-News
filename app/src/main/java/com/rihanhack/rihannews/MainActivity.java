package com.rihanhack.rihannews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Button;
import com.rihanhack.rihannews.layouts.AllNews;
import com.rihanhack.rihannews.layouts.Education;
import com.rihanhack.rihannews.layouts.Entertainment;
import com.rihanhack.rihannews.layouts.Health;
import com.rihanhack.rihannews.layouts.Sports;
import com.rihanhack.rihannews.layouts.Technology;

public class MainActivity extends AppCompatActivity {
    Button allNews,education,entertainment,health,sports,technology;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allNews = findViewById(R.id.allNews);
        education = findViewById(R.id.education);
        entertainment = findViewById(R.id.entertainment);
        health = findViewById(R.id.health);
        sports = findViewById(R.id.sports);
        technology = findViewById(R.id.technology);

        AllNews allNewsFrag = new AllNews();
        Education educationFrag = new Education();
        Entertainment entertainmentFrag = new Entertainment();
        Health healthFrag = new Health();
        Sports sportsFrag = new Sports();
        Technology technologyFrag = new Technology();

        allNews.setOnClickListener(v -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.newsFragement, allNewsFrag);
            transaction.commit();
        });

        education.setOnClickListener(v -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.newsFragement, educationFrag);
            transaction.commit();
        });

        entertainment.setOnClickListener(v -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.newsFragement, entertainmentFrag);
            transaction.commit();
        });

        health.setOnClickListener(v -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.newsFragement, healthFrag);
            transaction.commit();
        });

        sports.setOnClickListener(v -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.newsFragement, sportsFrag);
            transaction.commit();
        });

        technology.setOnClickListener(v -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.newsFragement, technologyFrag);
            transaction.commit();
        });
    }
}