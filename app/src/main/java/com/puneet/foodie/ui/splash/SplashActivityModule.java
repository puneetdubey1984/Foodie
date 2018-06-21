package com.puneet.foodie.ui.splash;

import com.puneet.foodie.data.DataManager;
import com.puneet.foodie.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class SplashActivityModule {

    @Provides
    static SplashViewModel providesSplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider ){
        return new SplashViewModel(dataManager,schedulerProvider);
    }
}
