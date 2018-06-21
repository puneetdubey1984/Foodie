package com.puneet.foodie.commom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.puneet.foodie.utils.IConnectivityReceiverListener;
import com.puneet.foodie.utils.NetworkUtil;

import javax.inject.Inject;

public class NetworkChangedReceiver extends BroadcastReceiver {

    public static IConnectivityReceiverListener connectivityReceiverListener;

    @Inject
    public NetworkChangedReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent arg1) {
        boolean isConnected = NetworkUtil.isConnected(context);
        if (connectivityReceiverListener != null) {
            connectivityReceiverListener.onNetworkConnectionChanged(isConnected);
        }
    }
}
