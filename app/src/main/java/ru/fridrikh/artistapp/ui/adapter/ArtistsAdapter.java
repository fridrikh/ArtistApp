package ru.fridrikh.artistapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ru.fridrikh.artistapp.models.Artists;
import ru.fridrikh.artistapp.R;

/**
 * Created by fdh on 24.04.16.
 */
public class ArtistsAdapter extends BaseAdapter{

    private ArrayList<Artists> mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private View view;

    public ArtistsAdapter(Context context, ArrayList<Artists> list){
        this.mContext = context;
        this.mList = list;

        mLayoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = convertView;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.list_item, parent, false);
        }

        init(position);

        return view;
    }

    private void init(int position){
        Picasso.with(mContext)
                .load(mList.get(position).getCover().getSmall())
                .into((ImageView) view.findViewById(R.id.imageView_photo));

        ((TextView) view.findViewById(R.id.textView_name)).setText(mList.get(position).getName());
        ((TextView) view.findViewById(R.id.textView_genres)).setText(getGenres(position));
        ((TextView) view.findViewById(R.id.textView_tracks)).setText(getInfoMusic(position));
    }

    private String getGenres(int position){
        String genres = "";
        for(String genre: mList.get(position).getGenres()){
            genres += genre + " ";
        }
        return genres;
    }

    private String getInfoMusic(int position){
        return mList.get(position).getAlbums() + " альбомов, "
                + mList.get(position).getTracks() + " песен";
    }
}
