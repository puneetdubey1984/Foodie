package com.puneet.foodie.utils.rx;

import io.reactivex.*;
import io.reactivex.android.schedulers.*;
import io.reactivex.schedulers.*;

public class AppSchedulerProvider implements SchedulerProvider {
    @Override
    public Scheduler computation() {
        return Schedulers.computation( );
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}
