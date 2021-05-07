package com.coolcats.mvvmcoolcats.view;

import android.os.Bundle;

import com.coolcats.mvvmcoolcats.viewmodel.CustomVMFactorty;
import com.coolcats.mvvmcoolcats.viewmodel.MovieViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.View;

import com.coolcats.mvvmcoolcats.R;

public class MainActivity2 extends AppCompatActivity {



    private MovieViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        viewModel = CustomVMFactorty.getInstance().create(MovieViewModel.class);//ViewModelProviders.of(this).get(MovieViewModel.class);

        Log.d("TAG_X", "ViewModelInstance: "+viewModel.toString());

    }
}