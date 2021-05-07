package com.coolcats.mvvmcoolcats.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.coolcats.mvvmcoolcats.R;
import com.coolcats.mvvmcoolcats.viewmodel.CustomVMFactorty;
import com.coolcats.mvvmcoolcats.viewmodel.MovieViewModel;

public class MainActivity extends AppCompatActivity {



    private int x = 0;


    private MovieViewModel viewModel;
    //Model View ViewModel
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = CustomVMFactorty.getInstance().create(MovieViewModel.class);
        //ViewModelProviders.of(this).get(MovieViewModel.class);
        Log.d("TAG_X", "ViewModelInstance 1 : "+viewModel.toString());
        viewModel.stateData.observe(this, movieState -> {

            switch (movieState){
                case LOADING:
                    toast(MovieViewModel.MovieState.LOADING.name());
                    break;
                case COMPLETE:
                    toast(MovieViewModel.MovieState.COMPLETE.name());
                    break;
                case ERROR:
                    toast(MovieViewModel.MovieState.ERROR.name());
                    break;

            }
        });


        new Handler(getMainLooper())
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent n = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(n);
                    }
                }, 3000);

        x = viewModel.x;
        Log.d("TAG_X", "Int -> "+x+"\n\n");

        viewModel.liveData.observe(this, movies -> {
            for(int i = 0; i < movies.size(); i++)
                Log.d("TAG_X", movies.get(i).getTitle());
        });

        viewModel.getMoviesForYear(2020);

        new Handler(getMainLooper()).postDelayed(()->{
            viewModel.getMoviesForYear(2021);
        }, 10000);
    }

    private void toast(String name) {
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }
}