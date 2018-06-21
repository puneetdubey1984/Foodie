package com.puneet.foodie.data.remote;

import com.puneet.foodie.data.model.DealsResponse;
import com.puneet.foodie.utils.rx.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppApiHelper implements ApiHelper {

    private final ClientModuleImplement mClientModule;
    private SchedulerProvider mSchedulerProvider;

    @Inject
    public AppApiHelper(ClientModuleImplement clientModule,
                        SchedulerProvider schedulerProvider
    ) {
        this.mClientModule = clientModule;
        this.mSchedulerProvider = schedulerProvider;

    }

    @Override
    public Observable<DealsResponse> getDeals() {
        return mClientModule.createRetrofitBody( ).getDeals( );
    }

}
