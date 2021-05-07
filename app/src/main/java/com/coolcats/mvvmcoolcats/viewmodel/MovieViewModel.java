package com.coolcats.mvvmcoolcats.viewmodel;

//ViewModel - lifecycleAware + no context
//AndroidViewModel - lifecycleAware + context

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.coolcats.mvvmcoolcats.model.MovieResponse;
import com.coolcats.mvvmcoolcats.model.Result;
import com.coolcats.mvvmcoolcats.model.network.MovieRetrofit;

import java.util.List;
import java.util.logging.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {

    public int x = 0;
    private MovieRetrofit movieRetrofit = new MovieRetrofit();

    public MutableLiveData<List<Result>> liveData = new MutableLiveData<>();
    public MutableLiveData<MovieState> stateData = new MutableLiveData<>();

    public void getMoviesForYear(int year){

        stateData.setValue(MovieState.LOADING);

        movieRetrofit.getMovies(year)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                        if(response.body() != null && response.body().getResults() != null) {
                            x = response.body().getResults().size();
                            liveData.setValue(response.body().getResults());
                            stateData.setValue(MovieState.COMPLETE);
                        }
                        else {
                            stateData.setValue(MovieState.ERROR);
                            Log.d("TAG_X", "Null....");
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {
                        stateData.setValue(MovieState.ERROR);
                        Log.d("TAG_X",   "Error: "+t.toString()+ ", URL: "+call.request().url());
                    }
                });


    }

    @Override
    protected void onCleared() {
        super.onCleared();

        Log.d("TAG_X", "onCleared!!!");
    }

    public enum MovieState {
        LOADING,
        COMPLETE,
        ERROR
    }

}

