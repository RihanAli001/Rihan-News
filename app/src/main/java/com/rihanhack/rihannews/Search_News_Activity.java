package com.rihanhack.rihannews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rihanhack.rihannews.adapters.RootNewsAdapter;
import com.rihanhack.rihannews.databinding.ActivitySearchNewsBinding;
import com.rihanhack.rihannews.models.Article;
import com.rihanhack.rihannews.params.Params;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Search_News_Activity extends AppCompatActivity {
    private List<Article> searchNewsList = new ArrayList<>();
    private LinearLayoutManager manager;
    private RootNewsAdapter searchNewsAdapter;
    ActivitySearchNewsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchNewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extra = getIntent().getExtras();
        String query = extra.getString("search");

        manager = new LinearLayoutManager(Search_News_Activity.this);
        binding.searchNewsRecycleView.setLayoutManager(manager);

        fetchData(query);
        searchNewsAdapter = new RootNewsAdapter(searchNewsList,this);
        binding.searchNewsRecycleView.setAdapter(searchNewsAdapter);
    }

    private void fetchData(String query) {
        Toast.makeText(this, "Loading please wait...", Toast.LENGTH_SHORT).show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Params.WEBSITE+query+Params.WEB_REST,null, this::onResponse, error -> Toast.makeText(this, "Error Occurred",Toast.LENGTH_SHORT).show());

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

    private void onResponse(JSONObject response) {
        try {
            JSONArray jsonArray = response.getJSONArray("articles");
            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                searchNewsList.add(new Article(jsonObject.getString("title"), jsonObject.getString("description"),
                        jsonObject.getString("content"), jsonObject.getString("url"), jsonObject.getString("image"),
                        jsonObject.getString("publishedAt")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        searchNewsAdapter.notifyDataSetChanged();
    }
}