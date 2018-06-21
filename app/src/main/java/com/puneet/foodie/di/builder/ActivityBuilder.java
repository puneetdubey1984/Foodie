package com.puneet.foodie.di.builder;

import com.puneet.foodie.ui.deals.DealDetailActivity;
import com.puneet.foodie.ui.deals.DealDetailActivityModule;
import com.puneet.foodie.ui.landing.DealListActivity;
import com.puneet.foodie.ui.landing.DealListActivityModule;
import com.puneet.foodie.ui.splash.SplashActivity;
import com.puneet.foodie.ui.splash.SplashActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = {DealListActivityModule.class})
    abstract DealListActivity bindDetailActivity();

    @ContributesAndroidInjector(modules = {DealDetailActivityModule.class})
    abstract DealDetailActivity bindDealDetailActivity();

}

