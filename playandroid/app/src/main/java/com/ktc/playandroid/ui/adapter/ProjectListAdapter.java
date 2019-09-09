package com.ktc.playandroid.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.ktc.playandroid.R;

import java.util.ArrayList;
import java.util.List;

public class ProjectListAdapter extends BaseAdapter {
    public ArrayList<String> mTtitle;
    public ArrayList<String> mContent;
    public ArrayList<String> mBackground;
    private Context mContext;
    public ProjectListAdapter(Context context,ArrayList<String> title, ArrayList<String> content, ArrayList<String> background) {
        mTtitle = title;
        mContent = content;
        mBackground = background;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mTtitle.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view==null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.project_list_item,null);
            viewHolder.title = view.findViewById(R.id.tv_project_title);
            viewHolder.content = view.findViewById(R.id.tv_project_content);
            viewHolder.imageView = view.findViewById(R.id.imv_project_icon);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.title.setText(mTtitle.get(i));
        viewHolder.content.setText(mContent.get(i));
        RequestOptions options = new RequestOptions().override(70,120);
        Glide.with(mContext).load(mBackground.get(i)).apply(options).into(viewHolder.imageView);
        return view;
    }
    static class ViewHolder{
        TextView title;
        TextView content;
        ImageView imageView;
    }
}
