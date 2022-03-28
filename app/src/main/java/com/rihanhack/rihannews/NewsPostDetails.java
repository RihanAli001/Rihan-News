package com.rihanhack.rihannews;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.bumptech.glide.Glide;
import com.rihanhack.rihannews.databinding.ActivityNewsPostDetailsBinding;

public class NewsPostDetails extends AppCompatActivity {
    ActivityNewsPostDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewsPostDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extras = getIntent().getExtras();
        String t = extras.getString("title");
        String d = extras.getString("desc");
        String s = extras.getString("source");
        String m = extras.getString("image");
        String dt = extras.getString("date");

        binding.newsTitle.setText(t);
        binding.newsDate.setText(dt);
        binding.newsDesc.setText(d);
        try {
            binding.newSource.setText(s);
        }catch (Exception e){e.printStackTrace();}
        Glide.with(this).load(m).into(binding.newsImage);
    }
}