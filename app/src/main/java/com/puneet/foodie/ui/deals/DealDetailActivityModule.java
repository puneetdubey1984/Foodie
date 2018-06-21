package com.puneet.foodie.ui.deals;

import com.puneet.foodie.data.DataManager;
import com.puneet.foodie.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class DealDetailActivityModule {
    @Provides
    static DealDetailViewModel providesDealDetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider){

        return new DealDetailViewModel( dataManager,schedulerProvider ) ;
    }
}
