package com.example.news360.RecyclerBind;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.news360.NewsReader;
import com.example.news360.R;
import com.example.news360.data.Articles;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class DataAdapterWorld extends RecyclerView.Adapter<DataAdapterWorld.ViewHolder> {


    List<Articles> articleList;
    Context context;

    public DataAdapterWorld(Context context, List<Articles> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.newslist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String titleData = articleList.get(position).getTitle();
        Glide.with(context).load(articleList.get(position).getUrlToImage()).into(holder.imageView);
        holder.textView1.setText(titleData);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NewsReader.class);
                intent.putExtra("title", titleData);
                intent.putExtra("image", articleList.get(position).getUrlToImage());
                intent.putExtra("content", articleList.get(position).getContent());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        CardView cardView;
        ShapeableImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.bigTitle);
            cardView = itemView.findViewById(R.id.cardView);
            imageView = itemView.findViewById(R.id.imageBig);
        }
    }
}
