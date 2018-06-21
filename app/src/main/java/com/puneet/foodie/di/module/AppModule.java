package com.puneet.foodie.di.module;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.puneet.foodie.R;
import com.puneet.foodie.data.AppDataManager;
import com.puneet.foodie.data.DataManager;
import com.puneet.foodie.data.remote.ApiHelper;
import com.puneet.foodie.data.remote.AppApiHelper;
import com.puneet.foodie.data.remote.ClientModule;
import com.puneet.foodie.data.remote.ClientModuleImplement;
import com.puneet.foodie.di.scope.ApplicationContext;
import com.puneet.foodie.utils.rx.AppSchedulerProvider;
import com.puneet.foodie.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@Module
public class AppModule {

    @Provides
    @Singleton
    @ApplicationContext
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder( )
                .setDefaultFontPath( "fonts/source-sans-pro/SourceSansPro-Regular.ttf" )
                .setFontAttrId( R.attr.fontPath )
                .build( );
    }
    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder( ).excludeFieldsWithoutExposeAnnotation( ).create( );
    }

    @Provides
    SchedulerProvider providesSchedulerProvider() {
        return new AppSchedulerProvider( );
    }

    @Provides
    @Singleton
    DataManager providesDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    ClientModuleImplement providesClientModuleImplement() {
        return new ClientModule( );
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }


}
