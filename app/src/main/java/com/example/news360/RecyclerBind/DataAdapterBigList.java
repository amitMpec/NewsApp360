package com.example.news360.RecyclerBind;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.shapes.Shape;
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

public class DataAdapterBigList extends RecyclerView.Adapter<DataAdapterBigList.ViewHolder> {


    List<Articles> articleList;
    Context context;

    public DataAdapterBigList(Context context, List<Articles> articleList) {
        this.context = context;
        this.articleList = articleList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newslistbig, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String titleData = articleList.get(position).getTitle();
        String dateData = articleList.get(position).getPublishedAt();
        Glide.with(context).load(articleList.get(position).getUrlToImage()).into(holder.smallImage);
        holder.title.setText(titleData);
        holder.dateText.setText(dateData);


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
        return articleList.size();
    }

    public static class ViewHolder extends DataAdapterWorld.ViewHolder {

        TextView title, dateText;
        CardView cardView;
        ShapeableImageView smallImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            cardView = itemView.findViewById(R.id.cardViewSmall);
            dateText = itemView.findViewById(R.id.dateView);
            smallImage = itemView.findViewById(R.id.imageSmall);

        }
    }
}
