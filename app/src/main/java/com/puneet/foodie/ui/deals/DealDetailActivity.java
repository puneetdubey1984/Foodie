package com.puneet.foodie.ui.deals;

import android.arch.lifecycle.R;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;
import com.puneet.foodie.ui.base.BaseActivity;
import com.puneet.foodie.databinding.*;
import com.puneet.foodie.data.model.Datum;

import javax.inject.Inject;

public class DealDetailActivity extends BaseActivity<DealDetailBinding,
        DealDetailViewModel > implements DealDetailNavigator {
    @Inject
    DealDetailViewModel mDealDetailViewModel;

    DealDetailBinding mDealDetailBinding;

    public static Intent newIntent(Context context, Datum datum) {
        Intent intent = new Intent( context, DealDetailActivity.class );
        Bundle mBundle = new Bundle();
        mBundle.putParcelable("Data", datum);
        intent.putExtras(mBundle);
        return intent;
    }

    @Override
    public void onAddToCart() {
        Toast.makeText( this, "Add to Cart", Toast.LENGTH_SHORT ).show( );
    }

    @Override
    public void onAddToList() {
        Toast.makeText( this, "Add to List", Toast.LENGTH_SHORT ).show( );
    }

    @Override
    public int getBindingVariable() {
        return BR.dealDetailViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.detail_activity;
    }

    @Override
    public DealDetailViewModel getViewModel() {
       return mDealDetailViewModel;
    }

    @Override
    public void intiBinding() {
        mDealDetailBinding = getViewDataBinding( );
               mDealDetailViewModel.setNavigator( this );
        if (getIntent().getExtras().getParcelable("Data")!=null) {
            Datum datum = getIntent().getExtras().getParcelable("Data");
            mDealDetailViewModel.updateProduct(datum);
            mDealDetailViewModel.getDealListLiveData();
        }
    }



    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        networkStatus( isConnected );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed( );
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
               intiBinding( );
    }

    @Override
    protected void onResume() {
        super.onResume( );

    }


}
