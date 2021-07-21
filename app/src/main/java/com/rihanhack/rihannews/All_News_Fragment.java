package com.rihanhack.rihannews;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rihanhack.rihannews.adapters.NewsAdapter;
import com.rihanhack.rihannews.models.NewsModel;
import com.rihanhack.rihannews.params.Params;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class All_News_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private List<NewsModel> allNewsList = new ArrayList<>();
    private LinearLayoutManager manager;
    private NewsAdapter allNewsAdapter;
    private int page=1;
    Context context;
    Boolean isScrolling=false;
    private int currentItem,totalItem,scrollOutItem;

    public All_News_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all__news_, container, false);
        context = container.getContext();

        recyclerView = view.findViewById(R.id.AllNews);
        manager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(manager);
        fetchData();
        allNewsAdapter = new NewsAdapter(allNewsList,context);
        recyclerView.setAdapter(allNewsAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItem = manager.getChildCount();
                totalItem = manager.getItemCount();
                scrollOutItem = manager.findFirstCompletelyVisibleItemPosition();

                if(isScrolling && (currentItem + scrollOutItem == totalItem)){
                    fetchData();
                }
            }
        });

        return view;
    }

    private void fetchData() {
        Toast.makeText(context, "Loading please wait...", Toast.LENGTH_SHORT).show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Params.TOP_HEADLINE + "&page="+page, null, response -> {
            try {
                JSONArray jsonArray = response.getJSONArray("results");
                int length = jsonArray.length();
                for(int i=0;i<length;i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    allNewsList.add(new NewsModel(jsonObject.getString("title"),
                            jsonObject.getString("description"),
                            jsonObject.getString("link"),jsonObject.getString("image_url")));
                }
                page++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            allNewsAdapter.notifyDataSetChanged();
        }, error -> Toast.makeText(context, "Error Occurred", Toast.LENGTH_SHORT).show());

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);
    }
}