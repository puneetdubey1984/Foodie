package com.puneet.foodie.ui.landing;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.puneet.foodie.R;
import com.puneet.foodie.ui.base.BaseActivity;
import com.puneet.foodie.databinding.*;
import com.puneet.foodie.BR;
import com.puneet.foodie.utils.SimpleDividerItemDecoration;

import javax.inject.Inject;

public class DealListActivity extends BaseActivity<ActivityDealListBinding,DealsItemViewModel > implements DealsNavigator,  DealsItemAdapter.DealsAdapterListener {

    @Inject
    LinearLayoutManager mLayoutManager;

    @Inject
    SimpleDividerItemDecoration mSimpleDividerItemDecoration;
    @Inject
    DealsItemAdapter mDealsItemAdapter;
    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    private DealsItemViewModel mDealsItemViewModel;
    private ActivityDealListBinding mActivityDealListBinding;
    private Toolbar mToolbar;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent( context, DealListActivity.class );
        intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_NEW_TASK );
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.dealsViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.deals_list;
    }

    @Override
    public DealsItemViewModel getViewModel() {
        mDealsItemViewModel = ViewModelProviders.of( this, mViewModelFactory ).get(
                DealsItemViewModel.class );
        return mDealsItemViewModel;
    }

    @Override
    public void intiBinding() {
        setUp();
        subscribeToLiveData();
        setSupportActionBar(mToolbar);
    }

    private void setUp() {
        mToolbar = mActivityDealListBinding.toolbar;
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mActivityDealListBinding.dealsRecyclerView.setLayoutManager(mLayoutManager);
        mActivityDealListBinding.dealsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mActivityDealListBinding.dealsRecyclerView.addItemDecoration(mSimpleDividerItemDecoration);
        mActivityDealListBinding.dealsRecyclerView.setAdapter( mDealsItemAdapter );
    }

    private void subscribeToLiveData() {
        mDealsItemViewModel.getDealListLiveData().observe(this,  datumList-> mDealsItemViewModel.addDealItemsToList(datumList));
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
        mDealsItemViewModel.setNavigator( this );
        mDealsItemAdapter.setListener(this);
        mActivityDealListBinding = getViewDataBinding( );
        intiBinding( );
    }

    @Override
    protected void onResume() {
        super.onResume( );

    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void onRetryClick() {
        mDealsItemViewModel.getDeals();
    }

   
}
