package ru.fridrikh.artistapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Response;
import ru.fridrikh.artistapp.R;
import ru.fridrikh.artistapp.models.Artists;
import ru.fridrikh.artistapp.network.RestAPI;
import ru.fridrikh.artistapp.network.RestClient;
import ru.fridrikh.artistapp.ui.adapter.ArtistsAdapter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArtistsAdapter mArtistAdapter;
    private ArrayList<Artists> mList;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initListView();
        getInfo();
    }

    private void getInfo() {
        RestAPI restAPI = RestClient.getClient(MainActivity.this);
        restAPI.getArtists()
        .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .cache()
                .subscribe(new Subscriber<Response<ArrayList<Artists>>>() {
                    @Override
                    public final void onCompleted() {
                        mArtistAdapter = new ArtistsAdapter(MainActivity.this, mList);
                        mListView.setAdapter(mArtistAdapter);
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Toast.makeText(MainActivity.this,
                                "Произошла ошибка!",
                                Toast.LENGTH_SHORT).show();
                        Log.e("ArtistsDemo", e.getMessage());
                    }

                    @Override
                    public final void onNext(Response<ArrayList<Artists>> response) {
                        mList = response.body();
                    }
                });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent info = new Intent(MainActivity.this, InfoActivity.class);
        info.putExtra("name", mList.get(position).getName());
        info.putExtra("photo", mList.get(position).getCover().getBig());
        info.putExtra("info_music", getInfoMusic(position));
        info.putExtra("genres", getGenres(position));
        info.putExtra("description", mList.get(position).getDescription());
        startActivity(info);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setTitle("Исполнители");
    }

    private void initListView() {
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setOnItemClickListener(this);
    }

    private String getGenres(int position) {
        String genres = "";
        for(String genre: mList.get(position).getGenres()){
            genres += genre + " ";
        }
        return genres;
    }

    private String getInfoMusic(int position) {
        return mList.get(position).getAlbums() + " альбомов, "
                + mList.get(position).getTracks() + " песен";
    }
}
