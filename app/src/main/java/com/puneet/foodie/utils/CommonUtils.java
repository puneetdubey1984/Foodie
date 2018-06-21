package com.puneet.foodie.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.util.Log;

import com.puneet.foodie.R;

public final class CommonUtils {

    private static DisplayMetrics mMetrics;

    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

  

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog( context );
        progressDialog.show( );
        if ( progressDialog.getWindow( ) != null ) {
            progressDialog.getWindow( ).setBackgroundDrawable( new ColorDrawable( Color
                    .TRANSPARENT ) );
        }
        progressDialog.setContentView( R.layout.progress_dialog );
        progressDialog.setIndeterminate( true );
        progressDialog.setCancelable( false );
        progressDialog.setCanceledOnTouchOutside( false );
        return progressDialog;
    }
    /**
     * This method converts dp unit to equivalent pixels, depending on device
     * density. NEEDS UTILS TO BE INITIALIZED BEFORE USAGE.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need
     *            to convert into pixels
     * @return A float value to represent px equivalent to dp depending on
     *         device density
     */
    public static float convertDpToPixel(float dp) {

        if (mMetrics == null) {

            Log.e("ValueBar-Utils",
                    "Utils NOT INITIALIZED. You need to call Utils.init(...) at least once before calling Utils.convertDpToPixel(...). Otherwise conversion does not take place.");
            return dp;
            // throw new IllegalStateException(
            // "Utils NOT INITIALIZED. You need to call Utils.init(...) at least once before calling Utils.convertDpToPixel(...).");
        }

        DisplayMetrics metrics = mMetrics;
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

}
