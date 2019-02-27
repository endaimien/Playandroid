package com.ktc.playandroid.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class NaviAdapter extends PagerAdapter {
    List<View> mListView;
    public NaviAdapter(List<View> pListView) {
         mListView = pListView;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public int getCount() {
        return mListView.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mListView.get(position));
        return mListView.get(position);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
