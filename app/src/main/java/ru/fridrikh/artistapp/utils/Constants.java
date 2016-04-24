package ru.fridrikh.artistapp.utils;

/**
 * Created by fdh on 15.04.16.
 */
public class Constants {
    public static final String API_URL = "http://download.cdn.yandex.net/mobilization-2016/";
    public static final String GET_ARTISTS = "artists.json";

    public static final String NAME_CACHE = "cache_artists";
    public static final int SIZE_CACHE = 20 * 1024 * 1024; // 20mb size cache

    public static final int TIMEOUT = 60;
    public static final int CONNECT_TIMEOUT = 20;
}
