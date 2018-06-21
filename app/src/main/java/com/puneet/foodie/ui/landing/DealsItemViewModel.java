
package com.puneet.foodie.ui.landing;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.puneet.foodie.data.DataManager;
import com.puneet.foodie.data.model.Datum;
import com.puneet.foodie.ui.base.BaseViewModel;
import com.puneet.foodie.utils.rx.SchedulerProvider;

import java.util.List;

public class DealsItemViewModel extends BaseViewModel<DealsNavigator > {

    public final ObservableList<Datum> datumObservableArrayList = new ObservableArrayList<>();

    private final MutableLiveData<List<Datum>> mutableLiveData;

    public DealsItemViewModel(DataManager dataManager,
                         SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        mutableLiveData = new MutableLiveData<>();
        getDeals();
    }

    public void addDealItemsToList(List<Datum> dealsItem) {
        datumObservableArrayList.clear();
        datumObservableArrayList.addAll(dealsItem);
    }

    public MutableLiveData<List<Datum>> getDealListLiveData() {
        return mutableLiveData;
    }

    public ObservableList<Datum> getBlogObservableList() {
        return datumObservableArrayList;
    }
    public void getDeals() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getDeals()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(openSourceResponse -> {
                    if (openSourceResponse != null && openSourceResponse.getData() != null) {
                        mutableLiveData.setValue(openSourceResponse.getData());
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }
}
