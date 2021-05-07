package com.coolcats.mvvmcoolcats.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public class CustomVMFactorty implements ViewModelProvider.Factory {

    private MovieViewModel movieViewModel;

    private CustomVMFactorty() {
    }

    private static CustomVMFactorty instance = null;

    public static CustomVMFactorty getInstance() {
        if(instance == null)
            instance = new CustomVMFactorty();
        return instance;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(movieViewModel == null)
            movieViewModel = new MovieViewModel();

        return (T)movieViewModel;
    }
}
