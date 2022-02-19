package com.example.news360.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsService {

  static   NewsInterface newsInterface;

   private final static String BASE_URL = "https://newsapi.org/";
   private static Retrofit retrofit = null;
   public static NewsInterface getNews(){
       if (retrofit==null){
           retrofit = new Retrofit.Builder()
                   .baseUrl(BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
            newsInterface = retrofit.create(NewsInterface.class);

       }
       return newsInterface;
   }
}
