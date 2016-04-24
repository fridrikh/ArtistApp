package ru.fridrikh.artistapp.network;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import ru.fridrikh.artistapp.utils.NetworkUtils;

/**
 * Created by fdh on 22.04.16.
 */
public class CacheInterceptor implements Interceptor {

    private Context mContext;

    public CacheInterceptor(Context context) {
        this.mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        String cacheHeaderValue = NetworkUtils.isNetworkConnected(mContext)
                ? "public, max-age=3600"
                : "public, only-if-cached, max-stale=3600" ;
        Request request = originalRequest.newBuilder().build();
        Response response = chain.proceed(request);
        return response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", cacheHeaderValue)
                .build();
    }
}
