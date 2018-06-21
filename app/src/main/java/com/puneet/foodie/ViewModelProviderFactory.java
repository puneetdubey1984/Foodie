package com.puneet.foodie;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class ViewModelProviderFactory<V> implements ViewModelProvider.Factory {

    private final V mViewModel;

    public ViewModelProviderFactory(V viewModel) {
        this.mViewModel = viewModel;

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(mViewModel.getClass())){
            return (T) mViewModel;
        }
        throw new IllegalArgumentException("Unknown Class Name");
    }
}
