package com.rihanhack.rihannews;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rihanhack.rihannews.adapters.RootNewsAdapter;
import com.rihanhack.rihannews.models.Article;
import com.rihanhack.rihannews.params.Params;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Health_News extends Fragment {
    private RecyclerView recyclerView;
    private List<Article> allNewsList = new ArrayList<>();
    private LinearLayoutManager manager;
    private RootNewsAdapter allNewsAdapter;
    Context context;

    public Health_News() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_health__news, container, false);
        context = container.getContext();

        recyclerView = view.findViewById(R.id.HealthNews);
        manager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(manager);
        fetchData();
        allNewsAdapter = new RootNewsAdapter(allNewsList,context);
        recyclerView.setAdapter(allNewsAdapter);

        return view;
    }

    private void fetchData() {
        Toast.makeText(context, "Loading please wait...", Toast.LENGTH_SHORT).show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Params.NEWS_HEALTH, null, this::onResponse, error -> Toast.makeText(context, "Error Occurred", Toast.LENGTH_SHORT).show());

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);
    }

    private void onResponse(JSONObject response) {
        try {
            JSONArray jsonArray = response.getJSONArray("articles");
            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                allNewsList.add(new Article(jsonObject.getString("title"), jsonObject.getString("description"),
                        jsonObject.getString("content"), jsonObject.getString("url"), jsonObject.getString("image"),
                        jsonObject.getString("publishedAt")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        allNewsAdapter.notifyDataSetChanged();
    }
}