package ru.fridrikh.artistapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by fdh on 20.04.16.
 */
public final class NetworkUtils {
    /**
     * Returns true or false if the network is available.
     *
     * @param context used for get ConnectivityManager
     * @return boolean result
     */
    public static boolean isNetworkConnected(Context context){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null
                && activeNetworkInfo.isConnected();
    }
}
