package com.ktc.playandroid.ui.activity;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.ktc.playandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestInternetActivity extends AppCompatActivity {
    @BindView(R.id.imageView)
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_internet);
        ButterKnife.bind(this);
        Glide.with(this)
                .load("https://www.wanandroid.com/blogimgs/10491b74-b534-48b1-a5fe-d2ac00e20d2d.png")
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        Log.d("wy",resource.getIntrinsicHeight()+" "+resource.getIntrinsicWidth());
                        imageView.setBackground(resource);
                    }
                });
    }
}
