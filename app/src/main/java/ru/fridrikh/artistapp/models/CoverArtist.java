package ru.fridrikh.artistapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fdh on 15.04.16.
 */
public class CoverArtist {
    @SerializedName("small")
    @Expose
    private String small;
    @SerializedName("big")
    @Expose
    private String big;

    /**
     *
     * @return
     * The small image
     */
    public String getSmall() {
        return small;
    }

    /**
     *
     * @return
     * The big image
     */
    public String getBig() {
        return big;
    }
}
