package ru.fridrikh.artistapp.network;

import java.util.ArrayList;
import retrofit2.Response;
import retrofit2.http.GET;
import ru.fridrikh.artistapp.models.Artists;
import ru.fridrikh.artistapp.utils.Constants;
import rx.Observable;

/**
 * Created by fdh on 15.04.16.
 */
public interface RestAPI {
    @GET(Constants.GET_ARTISTS)
    Observable<Response<ArrayList<Artists>>> getArtists();
}
