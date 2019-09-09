package com.ktc.playandroid.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ktc.playandroid.R;

import java.util.ArrayList;
import java.util.List;

public class NavigationTitleAdapter extends BaseAdapter {
    public Context context;
    public ArrayList<String> mTitle;
    public NavigationTitleAdapter(Context context, ArrayList<String> title) {
        super();
        this.context = context;
        mTitle = title;
    }

    @Override
    public int getCount() {
        return mTitle.size();
    }

    @Override
    public Object getItem(int i) {
        return mTitle.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.navi_title_item,null);
            viewHolder.textView = view.findViewById(R.id.ttv_navi_title);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.textView.setText(mTitle.get(i));
        return view;
    }
    static class ViewHolder{
        TextView textView;
    }
}
