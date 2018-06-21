package com.puneet.foodie.data;

import android.content.Context;

import com.google.gson.Gson;
import com.puneet.foodie.data.model.DealsResponse;
import com.puneet.foodie.data.remote.ApiHelper;
import com.puneet.foodie.data.remote.ClientModuleImplement;
import com.puneet.foodie.di.scope.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;

    private final Gson mGson;

    private final ClientModuleImplement mClientModuleImplement;

    @Inject
    public AppDataManager(@ApplicationContext Context context, ClientModuleImplement
            clientModule, Gson gson) {
        mContext = context;
        mGson = gson;
        mClientModuleImplement = clientModule;
    }



      public ApiHelper createRetrofitBody() {
        return mClientModuleImplement.createRetrofitBody( );
    }



    @Override
    public Observable<DealsResponse> getDeals() {
        return createRetrofitBody( ).getDeals( );
    }

   
}
