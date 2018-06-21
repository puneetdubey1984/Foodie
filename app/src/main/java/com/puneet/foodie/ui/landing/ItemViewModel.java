
package com.puneet.foodie.ui.landing;

public class ItemViewModel {

    public final ObservableField<String> mTitle ;

    public final ObservableField<String> mImageUrl ;

    public final ObservableField<String> mPrice ;

    public final ObservableField<String> mAisle;

    public final DealsItemViewModelListener mListener;

    private Datum mData;

    public ItemViewModel(Datum data,DealsItemViewModelListener listener) {
        this.mData = data;
        this.mListener = listener;
        mImageUrl = new ObservableField<>(data.getImage());
        mTitle = new ObservableField<>(data.getTitle());
        mPrice = new ObservableField<>(mData.getPrice());
        mAisle = new ObservableField<>(mData.getAisle());
       
    }
    public void onItemClick() {
        mListener.onItemClick(mData);
    }

    public interface DealsItemViewModelListener {

        void onItemClick(Datum datum);
    }
}
