package com.puneet.foodie.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.puneet.foodie.data.model.Datum;
import com.puneet.foodie.ui.landing.DealsItemAdapter;

import java.util.List;

public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }
    @BindingAdapter({"adapter"})
    public static void addBlogItems(RecyclerView recyclerView, List<Datum> blogs) {
        DealsItemAdapter adapter = (DealsItemAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(blogs);
        }
    }
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }


}
