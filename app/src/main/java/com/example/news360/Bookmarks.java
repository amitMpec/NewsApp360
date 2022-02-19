package com.example.news360;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.news360.RecyclerBind.DataAdapterBigList;

public class Bookmarks extends AppCompatActivity {


    RecyclerView recyclerBookmarks;
    String[] biglist = {"Mark Zuckerberg apologized.\nNow he has to fix Facebook", "Mark Zuckerberg apologized.\nNow he has to fix Facebook",
            "Mark Zuckerberg apologized.\nNow he has to fix Facebook", "Mark Zuckerberg apologized.\nNow he has to fix Facebook",
            "Mark Zuckerberg apologized.\nNow he has to fix Facebook", "Mark Zuckerberg apologized.\nNow he has to fix Facebook"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bookmarks);
        recyclerBookmarks = findViewById(R.id.recyclerBookmarks);
        recyclerBookmarks.setLayoutManager(new LinearLayoutManager(this));
        // recyclerBookmarks.setAdapter(new DataAdapterBigList(this,biglist));
    }
}