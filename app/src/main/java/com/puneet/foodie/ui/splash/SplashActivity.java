package com.puneet.foodie.ui.splash;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.puneet.foodie.BR;
import com.puneet.foodie.R;
import com.puneet.foodie.ui.base.BaseActivity;
import com.puneet.foodie.databinding.*;
import com.puneet.foodie.ui.landing.DealListActivity;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity<SplashActivityBinding, SplashViewModel >
        implements SplashNavigator {

    private static final long SPLASH_TIME_OUT = 5000;
    @Inject
    SplashViewModel mSplashViewModel;

    SplashActivityBinding mSplashScreenBinding;

    @Override
    public int getBindingVariable() {
        return BR.splashview;
    }

    @Override
    public int getLayoutId() {
        return R.layout.splash_screen;
    }

    @Override
    public SplashViewModel getViewModel() {
        return mSplashViewModel;
    }

    @Override
    public void intiBinding() {
        mSplashScreenBinding = getViewDataBinding( );
        mSplashViewModel.setNavigator( this );
        mSplashViewModel.openDealsListActivity( );
    }

    @Override
    public void openDealsListActivity() {
            new Handler( ).postDelayed( new Runnable( ) {
                @Override
                public void run() {
                    startActivity( DealListActivity.newIntent( SplashActivity.this ) );
                    finish( );
                }
            }, SPLASH_TIME_OUT );


    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme( R.style.AppTheme );
        super.onCreate( savedInstanceState );
        intiBinding();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        networkStatus( isConnected );
    }
}
