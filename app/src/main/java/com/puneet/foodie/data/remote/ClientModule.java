package com.puneet.foodie.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.puneet.foodie.BuildConfig;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class ClientModule implements ClientModuleImplement {

    private Retrofit retrofit;

    @Inject
    public ClientModule() {

    }

    public Retrofit provideRetrofit() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor( );
        loggingInterceptor.setLevel( HttpLoggingInterceptor.Level.BODY );

        OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder( )
                        .addInterceptor( loggingInterceptor )
                        .addInterceptor( chain -> {
                            Request original = chain.request( );
                            HttpUrl originalHttpUrl = original.url( );

                            HttpUrl url = originalHttpUrl.newBuilder( )
                                    .build( );
                            // Request customization: add request headers
                            Request.Builder requestBuilder = original.newBuilder( )
                                    .url( url );

                            Request request = requestBuilder.build( );
                            return chain.proceed( request );
                        } );
        Gson gson = new GsonBuilder( )
                .excludeFieldsWithoutExposeAnnotation( )
                .create( );

        retrofit = new Retrofit.Builder( )
                .client( httpClient.build( ) )
                .baseUrl( BuildConfig.BASE_URL )
                .addConverterFactory( GsonConverterFactory.create( gson ) )
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create( ) )
                .build( );

        return retrofit;
    }

    @Override
    public ApiHelper createRetrofitBody() {
        return provideRetrofit( ).create( ApiHelper.class );
    }


}
