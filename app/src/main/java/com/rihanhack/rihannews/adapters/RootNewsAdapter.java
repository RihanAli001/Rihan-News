package com.rihanhack.rihannews.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rihanhack.rihannews.NewsPostDetails;
import com.rihanhack.rihannews.R;
import com.rihanhack.rihannews.models.Article;
import java.util.List;

public class RootNewsAdapter extends RecyclerView.Adapter<RootNewsAdapter.ViewHolder> {
    List<Article> newsList;
    Context context;

    public RootNewsAdapter(List<Article> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.top_headline_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(newsList.get(position).getTitle());
        holder.description.setText(newsList.get(position).getDescription());
        holder.source.setText(newsList.get(position).getUrl());
        Glide.with(context).load(newsList.get(position).getImage()).into(holder.imageView);


        String t = newsList.get(position).getTitle();
        String d = newsList.get(position).getDescription();
        String s = newsList.get(position).getUrl();
        String image = newsList.get(position).getImage();
        String dt = newsList.get(position).getPublishedAt();

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, NewsPostDetails.class);
            intent.putExtra("title",t+"");
            intent.putExtra("desc",d+"");
            intent.putExtra("date",dt+"");
            try {
                intent.putExtra("source",s+"");
            }catch (Exception e){e.printStackTrace();}
            if(image == null)
                intent.putExtra("image","");
            else
                intent.putExtra("image",image+"");

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() { return newsList.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title,description,source;
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.newsTitleDetail);
            description = itemView.findViewById(R.id.newsDescriptionDetail);
            source = itemView.findViewById(R.id.newsSourceDetail);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
