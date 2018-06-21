package com.puneet.foodie.ui.landing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.puneet.foodie.data.model.Datum;
import com.puneet.foodie.di.scope.ApplicationContext;
import com.puneet.foodie.ui.base.BaseViewHolder;
import com.puneet.foodie.ui.deals.DealDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class DealsItemAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private final List<Datum> mResponseList;
    private final Context mContext;


    private DealsAdapterListener mListener;

    public DealsItemAdapter(@ApplicationContext Context context) {
        this.mContext=context;
        this.mResponseList = new ArrayList<>();
    }


    @Override
    public int getItemCount() {
        if (!mResponseList.isEmpty()) {
            return mResponseList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (!mResponseList.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                DealsItemViewModelBinding itemBinding =
                        DealsItemViewModelBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                parent.setTag(itemBinding);
                return new DealListViewHolder(itemBinding);
           case VIEW_TYPE_EMPTY:
           default:
               EmptyDealsItemViewModelBinding emptyViewBinding = EmptyDealsItemViewModelBinding
                        .inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new EmptyViewHolder(emptyViewBinding);
        }
    }

    public void addItems(List<Datum > repoList) {
        mResponseList.addAll(repoList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mResponseList.clear();
    }

    public void setListener(DealsAdapterListener listener) {
        this.mListener = listener;
    }

    public interface DealsAdapterListener {

        void onRetryClick();
    }

    public class EmptyViewHolder extends BaseViewHolder implements EmptyItemViewModel.EmptyItemViewModelListener {

        private final EmptyDealsItemViewModelBinding mBinding;

        public EmptyViewHolder(EmptyDealsItemViewModelBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            EmptyItemViewModel emptyItemViewModel = new EmptyItemViewModel(this);
            mBinding.setEmptyDealViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            mListener.onRetryClick();
        }
    }

    public class DealListViewHolder extends BaseViewHolder implements ItemViewModel.DealsItemViewModelListener {

        private final DealsItemViewModelBinding mBinding;
        private Datum mDatum;
        private ItemViewModel mItemViewModel;

        public DealListViewHolder(DealsItemViewModelBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            mDatum = mResponseList.get(position);
            mItemViewModel = new ItemViewModel(mDatum, this);
            mBinding.setDealsItemViewModel( mItemViewModel );
            mBinding.executePendingBindings();
        }
        @Override
        public void onItemClick(Datum datum) {
           mContext.startActivity( DealDetailActivity.newIntent( mContext,datum ) );
        }
    }
}
