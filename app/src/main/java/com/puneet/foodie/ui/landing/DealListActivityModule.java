package com.puneet.foodie.ui.landing;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.puneet.foodie.ViewModelProviderFactory;
import com.puneet.foodie.data.DataManager;
import com.puneet.foodie.di.scope.ApplicationContext;
import com.puneet.foodie.utils.SimpleDividerItemDecoration;
import com.puneet.foodie.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class DealListActivityModule {
    @Provides
    LinearLayoutManager provideLinearLayoutManager(@ApplicationContext Context context) {
        return new LinearLayoutManager(context);
    }

    @Provides
    SimpleDividerItemDecoration providesSimpleDividerItemDecoration(@ApplicationContext Context context){
        return new SimpleDividerItemDecoration(context) ;
    }



    @Provides
    DealsItemAdapter provideOpenSourceAdapter(@ApplicationContext Context context) {
        return new DealsItemAdapter(context);
    }
    @Provides
    ViewModelProvider.Factory mainViewModelProvider(DealsItemViewModel appLandingModelView) {
        return new ViewModelProviderFactory<>(appLandingModelView);
    }

    @Provides
    static DealsItemViewModel providesLandingModelView(DataManager dataManager, SchedulerProvider schedulerProvider){

        return new DealsItemViewModel( dataManager,schedulerProvider ) ;
    }
}
