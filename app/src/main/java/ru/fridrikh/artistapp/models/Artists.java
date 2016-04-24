package ru.fridrikh.artistapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fdh on 15.04.16.
 */
public class Artists {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("genres")
    @Expose
    private ArrayList<String> genres = new ArrayList<String>();
    @SerializedName("tracks")
    @Expose
    private Integer tracks;
    @SerializedName("albums")
    @Expose
    private Integer albums;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("cover")
    @Expose
    private CoverArtist cover;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @return
     * The name artist
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     * The genres
     */
    public List<String> getGenres() {
        return genres;
    }

    /**
     *
     * @return
     * The tracks
     */
    public Integer getTracks() {
        return tracks;
    }

    /**
     *
     * @return
     * The albums
     */
    public Integer getAlbums() {
        return albums;
    }

    /**
     *
     * @return
     * The link
     */
    public String getLink() {
        return link;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return
     * The cover
     */
    public CoverArtist getCover() {
        return cover;
    }
}
