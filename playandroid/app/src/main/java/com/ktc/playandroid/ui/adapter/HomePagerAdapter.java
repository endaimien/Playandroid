package com.ktc.playandroid.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.ktc.playandroid.R;
import com.ktc.playandroid.mvpcomponent.contract.ProjectContract;

import java.util.ArrayList;
import java.util.List;

public class HomePagerAdapter extends PagerAdapter {
    private List<String> mTitle;
    private List<String> mBackground;
    private Context mContext;
    public HomePagerAdapter(Context context,ArrayList<String> title, ArrayList<String> background) {
        mTitle = title;
        mBackground = background;
        mContext = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Log.d("wy","instantiateItem");
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_pager_list,container,false);
       RelativeLayout linearLayout = view.findViewById(R.id.lrl_home_viewpager);
        TextView textView = view.findViewById(R.id.ttv_home_viewpager);
        TextView textViewNum = view.findViewById(R.id.ttv_home_pagernum);
        Glide.with(mContext).load(mBackground.get(position)).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                linearLayout.setBackground(resource);
            }
        });
        textView.setText(mTitle.get(position));
        textViewNum.setText(position+"/"+mTitle.size());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public int getCount() {
        return mTitle.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

}
