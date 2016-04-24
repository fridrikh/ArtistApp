package ru.fridrikh.artistapp.network;

import android.content.Context;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.fridrikh.artistapp.BuildConfig;
import ru.fridrikh.artistapp.utils.Constants;

/**
 * Created by fdh on 15.04.16.
 */
public class RestClient {
    private static RestAPI restAPI;

    public static RestAPI getClient(Context context) {
        if (restAPI == null) {
            OkHttpClient okHttpClient = createCachedClient(context);

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(Constants.API_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            restAPI = client.create(RestAPI.class);
        }
        return restAPI;
    }

    private static OkHttpClient createCachedClient(final Context context) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        // OkHttp set timeout
        clientBuilder.connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS);
        clientBuilder.readTimeout(Constants.TIMEOUT, TimeUnit.SECONDS);
        // OkHttp implement offline cache
        clientBuilder.addInterceptor(new CacheInterceptor(context));
        clientBuilder.addNetworkInterceptor(new CacheInterceptor(context));
        // OkHttp logging
        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(loggingInterceptor);
        }
        clientBuilder.cache(getCache(context));
        return clientBuilder.build();
    }

    /**
     * Return file for cache.
     *
     * @return file
     */
    private static File getFileCache(Context context){
        return new File(context.getCacheDir(), Constants.NAME_CACHE);
    }

    /**
     * Return cache.
     *
     * @return cache
     */
    private static Cache getCache(Context context){
        return new Cache(getFileCache(context), Constants.SIZE_CACHE);
    }
}
