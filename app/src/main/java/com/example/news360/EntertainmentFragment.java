package com.example.news360;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.news360.RecyclerBind.DataAdapterBigList;
import com.example.news360.RecyclerBind.DataAdapterWorld;
import com.example.news360.data.News;
import com.example.news360.data.NewsService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EntertainmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EntertainmentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ConstraintLayout constraintLayout;
    ProgressBar progressBar;

    RecyclerView recyclerView1;
    RecyclerView recyclerViewSmallItem;
    String[] list = {"Cavani fires Uruguay into\n" +
            "last eight as Ronaldo dream", "Cavani fires Uruguay into\n" +
            "last eight as Ronaldo dream", "Cavani fires Uruguay into\n" +
            "last eight as Ronaldo dream", "Cavani fires Uruguay into\n" +
            "last eight as Ronaldo dream"};
    String[] biglist = {"Mark Zuckerberg apologized.\nNow he has to fix Facebook", "Mark Zuckerberg apologized.\nNow he has to fix Facebook",
            "Mark Zuckerberg apologized.\nNow he has to fix Facebook", "Mark Zuckerberg apologized.\nNow he has to fix Facebook",
            "Mark Zuckerberg apologized.\nNow he has to fix Facebook", "Mark Zuckerberg apologized.\nNow he has to fix Facebook"};


    public EntertainmentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EntertainmentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EntertainmentFragment newInstance(String param1, String param2) {
        EntertainmentFragment fragment = new EntertainmentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_world, container, false);
        recyclerView1 = view.findViewById(R.id.recyclerView1);
        constraintLayout = view.findViewById(R.id.constraint1);
        progressBar = view.findViewById(R.id.progressBar);

        Call<News> newsInterface1 = NewsService.getNews().getHeadlines("ca");
        // Call<News> newsInterface = NewsService.getNews().getBusinessHeadlines("us","business");

        newsInterface1.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {

                if (!response.isSuccessful()) {
                    //    textViewResponse.setText(response.code());
                    Log.i("debug", "smallList" + response.message());
                    return;
                }
                News articles = (News) response.body();
                recyclerView1.setAdapter(new DataAdapterWorld(view.getContext(), articles.getArticles()));

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.i("fail", t.getMessage());

            }
        });



        recyclerViewSmallItem = view.findViewById(R.id.recyclerSmallItem);
        recyclerViewSmallItem.setLayoutManager(new LinearLayoutManager(view.getContext()));

        Call<News> newsInterface = NewsService.getNews().getHeadlines("us");

        newsInterface.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {

                if (!response.isSuccessful()) {
                    //    textViewResponse.setText(response.code());
                    Log.i("debug", "biglist123" + response.message());
                    return;
                }
                progressBar.setVisibility(View.GONE);
                constraintLayout.setVisibility(View.VISIBLE);
                News articles = (News) response.body();
                recyclerViewSmallItem.setAdapter(new DataAdapterBigList(view.getContext(), articles.getArticles()));

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.i("fail", t.getMessage());

            }
        });


        return view;
    }
}