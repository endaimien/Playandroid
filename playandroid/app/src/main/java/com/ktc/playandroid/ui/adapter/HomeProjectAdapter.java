package com.ktc.playandroid.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ktc.playandroid.R;
import com.ktc.playandroid.internet.bean.homepage.HomeEssaypageDate;
import com.ktc.playandroid.internet.bean.project.ProjectEssayData;
import com.ktc.playandroid.util.TimeUtil;

import java.util.List;
import java.util.PrimitiveIterator;

public class HomeProjectAdapter extends BaseAdapter {
    private Context mContext;
    private List<HomeEssaypageDate> mHomeEssaypageDateList;
    private List<ProjectEssayData> mProjectEssayDataList;
    private boolean mShowProject;
    public HomeProjectAdapter(Context context, List<HomeEssaypageDate> homeEssaypageDateList,
                              List<ProjectEssayData> projectEssayData,boolean showproject) {
        mContext = context;
        mHomeEssaypageDateList = homeEssaypageDateList;
        mProjectEssayDataList = projectEssayData;
        mShowProject = showproject;
    }

    @Override
    public int getCount() {
        if(mShowProject){
            return mProjectEssayDataList.size();
        }else {
            return mHomeEssaypageDateList.size();
        }
    }

    @Override
    public Object getItem(int i) {
        if(mShowProject){
            return mProjectEssayDataList.get(i);
        }else {
            return mHomeEssaypageDateList.get(i);
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.home_project_item,null);
            viewHolder.mButton = view.findViewById(R.id.but_home_star);
            viewHolder.mTitle = view.findViewById(R.id.ttv_home_title);
            viewHolder.mContent = view.findViewById(R.id.ttv_home_content);
            viewHolder.mTime = view.findViewById(R.id.ttv_home_time);
            viewHolder.mWrite = view.findViewById(R.id.ttv_home_writer);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if(mShowProject){
            viewHolder.mButton.setPressed(mProjectEssayDataList.get(i).isCollect());
            viewHolder.mTitle.setText(mProjectEssayDataList.get(i).getTitle());
            viewHolder.mContent.setText(mProjectEssayDataList.get(i).getDesc());
            viewHolder.mWrite.setText(mProjectEssayDataList.get(i).getAuthor());
            viewHolder.mTime.setText(TimeUtil.getTimehour(mProjectEssayDataList.get(i).getPublishTime()));
        }else {
            viewHolder.mButton.setPressed(mHomeEssaypageDateList.get(i).isCollect());
            viewHolder.mTitle.setText(mHomeEssaypageDateList.get(i).getTitle());
            if(mHomeEssaypageDateList.get(i).getDesc()==null){
                viewHolder.mContent.setVisibility(View.GONE);
            }else {
                viewHolder.mContent.setText(mHomeEssaypageDateList.get(i).getDesc());
            }
            viewHolder.mWrite.setText(mHomeEssaypageDateList.get(i).getAuthor());
            viewHolder.mTime.setText(TimeUtil.getTimehour(mHomeEssaypageDateList.get(i).getPublishTime()));
        }
        return view;
    }
    static class ViewHolder{
        Button mButton;
        TextView mTitle;
        TextView mContent;
        TextView mWrite;
        TextView mTime;
    }
}
