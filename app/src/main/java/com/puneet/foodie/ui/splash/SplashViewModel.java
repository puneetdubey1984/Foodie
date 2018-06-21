package com.puneet.foodie.ui.splash;

import com.puneet.foodie.data.DataManager;
import com.puneet.foodie.ui.base.BaseViewModel;
import com.puneet.foodie.utils.rx.SchedulerProvider;

public class SplashViewModel extends BaseViewModel<SplashNavigator> {

    public SplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super( dataManager, schedulerProvider );
    }

    public void openDealsListActivity() {
        getNavigator().openDealsListActivity();
    }


}
