package com.coolcats.mvvmcoolcats.model.network;

import com.coolcats.mvvmcoolcats.model.MovieResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.coolcats.mvvmcoolcats.util.Constants.*;

public class MovieRetrofit {

    private MovieService movieService = createRetrofit().create(MovieService.class);

    public Call<MovieResponse> getMovies(int releaseYear){
        return movieService.getMovies(
                API_KEY,
                SORT_BY_VALUE,
                releaseYear
        );
    }

    private Retrofit createRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    interface MovieService {

        @GET(ENDPOINT)
        Call<MovieResponse> getMovies(
                @Query(API_KEY_QUERY) String apiKey,
                @Query(SORT_BY_QUERY) String sortBy,
                @Query(RELEASE_YEAR_QUERY) int releaseYear
                );

    }

}
