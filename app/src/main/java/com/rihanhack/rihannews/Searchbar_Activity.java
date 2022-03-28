package com.rihanhack.rihannews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.rihanhack.rihannews.databinding.ActivitySearchbarBinding;

public class Searchbar_Activity extends AppCompatActivity {
    ActivitySearchbarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchbarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        CharSequence query = binding.searchView.getQuery();

        binding.searchView.setOnSearchClickListener(v -> {
            Intent intent = new Intent(Searchbar_Activity.this, Search_News_Activity.class);
            intent.putExtra("search",query);
            startActivity(intent);
        });
    }
}