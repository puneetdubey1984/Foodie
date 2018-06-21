package com.puneet.foodie.data.remote;


import com.puneet.foodie.data.model.DealsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

import static com.puneet.foodie.data.remote.ApiEndPoint.DEALS;

public interface ApiHelper {

    @GET( DEALS )
    Observable<DealsResponse> getDeals();




}
