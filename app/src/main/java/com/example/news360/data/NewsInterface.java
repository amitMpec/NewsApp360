package com.example.news360.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsInterface {

    //https://newsapi.org/v2/top-headlines?country=in&apiKey=API_KEY
    //https://newsapi.org/v2/everything?q=apple&from=2021-09-23&to=2021-09-23&sortBy=popularity&apiKey=API_KEY

    // Add your own api key
     String API_KEY = "5c206f15-d484-xxxxx-xxxx-xxxxxxxx";
    @GET(value = "v2/top-headlines?apiKey=" + API_KEY)
    Call<News> getHeadlines(@Query(value = "country") String country);

    @GET(value = "v2/top-headlines?apiKey=" + API_KEY)
    Call<News> getBusinessHeadlines(@Query(value = "country") String country, @Query(value = "category") String category);

    @GET(value = "v2/top-headlines?apiKey=" + API_KEY)
    Call<News> getTechHeadlines(@Query(value = "sources") String sources);

    @GET(value = "v2/everything?apiKey=" + API_KEY)
    Call<News> getSportsHeadlines(@Query(value = "domains") String domains);
    
}
