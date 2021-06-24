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
import com.rihanhack.rihannews.models.NewsModel;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    List<NewsModel> headLineList;
    Context context;

    public NewsAdapter(List<NewsModel> headLineList, Context context) {
        this.headLineList = headLineList;
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
        holder.title.setText(headLineList.get(position).getTitle());
        holder.description.setText(headLineList.get(position).getDescription());
        holder.source.setText(headLineList.get(position).getSource());
        Glide.with(context).load(headLineList.get(position).getImage()).into(holder.imageView);


        String t = headLineList.get(position).getTitle();
        String d = headLineList.get(position).getDescription();
        String s = headLineList.get(position).getSource();
        String image = headLineList.get(position).getImage();

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, NewsPostDetails.class);
            intent.putExtra("title",t+"");
            intent.putExtra("desc",d+"");
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
    public int getItemCount() {
        return headLineList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
