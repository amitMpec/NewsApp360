package com.example.news360;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.news360.databinding.ActivityNewsReaderBinding;

public class NewsReader extends AppCompatActivity {

    ActivityNewsReaderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_reader);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = extras.getString("title");
            String image = extras.getString("image");
            String content = extras.getString("content");
            //The key argument here must match that used in the other activity
            binding.titleTextView.setText(title);
            Glide.with(this).load(image).into(binding.newsReaderImage);
            binding.descriptionTextView.setText(content);
        }

        binding.shareNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareNews();
            }
        });

    }

    private void shareNews() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This news will be send");
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);

    }

}