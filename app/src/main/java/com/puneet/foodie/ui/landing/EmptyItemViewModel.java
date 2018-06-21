package com.puneet.foodie.ui.landing;

public class EmptyItemViewModel {

    private final EmptyItemViewModelListener mListener;

    public EmptyItemViewModel(EmptyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public void onRetryClick() {
        mListener.onRetryClick();
    }

    public interface EmptyItemViewModelListener {

        void onRetryClick();
    }
}
