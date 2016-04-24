package ru.fridrikh.artistapp.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ru.fridrikh.artistapp.R;

/**
 * Created by fdh on 24.04.16.
 */
public class InfoActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initToolbar();
        setImage();
        setTextViews();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        addBackArrow();
        setTitle(getIntent().getStringExtra("name"));
    }

    private void setTextViews() {
        ((TextView) findViewById(R.id.textView_genres)).setText(getIntent().getStringExtra("genres"));
        ((TextView) findViewById(R.id.textView_tracks)).setText(getIntent().getStringExtra("info_music"));
        ((TextView) findViewById(R.id.textView_biograhp)).setText("Биография");
        ((TextView) findViewById(R.id.textView_description)).setText(getIntent().getStringExtra("description"));
    }

    private void setImage() {
        Picasso.with(InfoActivity.this)
                .load(getIntent().getStringExtra("photo"))
                .into((ImageView) findViewById(R.id.imageView_photo));
    }

    protected void addBackArrow() {
        mToolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_mtrl_am_alpha));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
