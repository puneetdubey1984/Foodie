package com.puneet.foodie.di.component;

import android.app.Application;

import com.puneet.foodie.TargetDealsApplication;
import com.puneet.foodie.di.builder.ActivityBuilder;
import com.puneet.foodie.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,AppModule.class,ActivityBuilder.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {
    void inject(TargetDealsApplication app);
    @Override
    void inject(DaggerApplication instance);
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
