package com.puneet.foodie;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;
import com.puneet.foodie.di.component.DaggerAppComponent;
import com.puneet.foodie.utils.AppLogger;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class TargetDealsApplication extends MultiDexApplication implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;
    @Inject
    CalligraphyConfig mCalligraphyConfig;
    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
        Stetho.initializeWithDefaults(this);
        AppLogger.init();
        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }
}
