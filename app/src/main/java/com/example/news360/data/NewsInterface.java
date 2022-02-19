package com.example.news360.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsInterface {

    //https://newsapi.org/v2/top-headlines?country=in&apiKey=API_KEY
    //https://newsapi.org/v2/everything?q=apple&from=2021-09-23&to=2021-09-23&sortBy=popularity&apiKey=API_KEY

     String API_KEY = "ade554c08fdb47e7bd7938178f198648";
    //String API_KEY = "acbf05cb82074db292a102a8dcf3816b";

    @GET(value = "v2/top-headlines?apiKey=" + API_KEY)
    Call<News> getHeadlines(@Query(value = "country") String country);

    // https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=API_KEY
    @GET(value = "v2/top-headlines?apiKey=" + API_KEY)
    Call<News> getBusinessHeadlines(@Query(value = "country") String country, @Query(value = "category") String category);

    @GET(value = "v2/top-headlines?apiKey=" + API_KEY)
    Call<News> getTechHeadlines(@Query(value = "sources") String sources);

    @GET(value = "v2/everything?apiKey=" + API_KEY)
    Call<News> getSportsHeadlines(@Query(value = "domains") String domains);

    // https://newsapi.org/v2/everything?q=apple&from=2021-09-27&to=2021-09-27&sortBy=popularity&apiKey=API_KEY
    // https://newsapi.org/v2/everything?q=apple&from=2021-09-27&to=2021-09-27&sortBy=popularity&apiKey=ade554c08fdb47e7bd7938178f198648
    @GET(value = "v2/everything?apiKey=" + API_KEY)
    Call<News> getEntertainmentHeadlines(@Query(value = "apple") String apple, @Query(value = "from") String from, @Query(value = "to") String to, @Query(value = "sortBy") String sortBy);


    //https://newsapi.org/v2/everything?q=tesla&from=2021-08-28&sortBy=publishedAt&apiKey=API_KEY

    @GET(value = "v2/everything?apiKey=" + API_KEY)
    Call<News> getTeslaHeadlines(@Query(value = "tesla") String apple, @Query(value = "from") String from, @Query(value = "sortBy") String sortBy);

}
