package com.rihanhack.rihannews;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class NewsPostDetails extends AppCompatActivity {
    private TextView title,desc,source;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_post_details);

        Bundle extras = getIntent().getExtras();

        title = findViewById(R.id.newsTitle);
        desc = findViewById(R.id.newsDesc);
        source = findViewById(R.id.newSource);
        image = findViewById(R.id.newsImage);

        String t = extras.getString("title");
        String d = extras.getString("desc");
        String s = extras.getString("source");
        String m = extras.getString("image");

        title.setText(t);
        desc.setText(d);
        try {
            source.setText(s);
        }catch (Exception e){e.printStackTrace();}
        Glide.with(this).load(m).into(image);
    }
}