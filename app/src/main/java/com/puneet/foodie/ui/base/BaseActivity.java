package com.puneet.foodie.ui.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.puneet.foodie.R;
import com.puneet.foodie.commom.NetworkChangedReceiver;
import com.puneet.foodie.utils.CommonUtils;
import com.puneet.foodie.utils.IConnectivityReceiverListener;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends DaggerAppCompatActivity implements IConnectivityReceiverListener, BaseFragment.Callback {

    @Inject
    NetworkChangedReceiver myBroadcastReceiver;
    private T mViewDataBinding;
    private V mViewModel;

    String NETWORK_STATUS = String.valueOf( R.string.network_status );
    public boolean isConnected;
    private ProgressDialog mProgressDialog;

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

    /**
     * @return layout resource Id
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * Override for set View model
     *
     * @return view model instance
     */
    public abstract V getViewModel();

    /**
     *
     * @return view data binding
     */
    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    /**
     * Override for initial binding variable
     *
     */

    public abstract void intiBinding();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        performDataBinding();
    }
    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    public void networkStatus(boolean isConnected){
        if(!isConnected){
            Toast.makeText( this, NETWORK_STATUS, Toast.LENGTH_SHORT ).show( );
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(myBroadcastReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcastReceiver);
    }

    public void hideKeyboard(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = ( InputMethodManager ) getSystemService( Context.INPUT_METHOD_SERVICE );
            if(imm != null){
                imm.hideSoftInputFromWindow( view.getWindowToken(),0 );
            }
        }
    }
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }
    /**
     * To update the Toolbar in each level, we can use this updateToolbar
     *  @param idToolbar
     * @param toolBarTxt
     * @param title
     * @param enableBackArrow
     */
    @RequiresApi( api = Build.VERSION_CODES.LOLLIPOP )
    protected void updateToolBar(Toolbar idToolbar, TextView toolBarTxt, String title,
                                 boolean enableBackArrow) {
        setSupportActionBar(idToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolBarTxt.setText(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(enableBackArrow);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        idToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }



    protected void appCloseAlertDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.app_close_title));
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //TODO
                dialog.dismiss();
                finish();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //TODO
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
