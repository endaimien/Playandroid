package com.ktc.playandroid.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ktc.playandroid.R;

import java.util.ArrayList;

public class MineChoiceAdapter extends BaseAdapter {
    private ArrayList<String> mlist;
    private Context mContext;
    public MineChoiceAdapter(Context context) {
        super();
        mContext = context;
        mlist = new ArrayList<String>();
        mlist.add(context.getString(R.string.mine_choice_atic));
        mlist.add(context.getString(R.string.mine_choice_web));
        mlist.add(context.getString(R.string.mine_choice_todo));
        mlist.add(context.getString(R.string.mine_choice_userinfor));
        mlist.add(context.getString(R.string.mine_choice_logout));
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int i) {
        return mlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder mViewHolder;
        if(view==null){
            mViewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.mine_fragment_main_item,null);
            mViewHolder.imageView = view.findViewById(R.id.imv_main_item_icon);
            mViewHolder.textView = view.findViewById(R.id.imv_main_item_text);
            view.setTag(mViewHolder);
        }else {
            mViewHolder = (ViewHolder) view.getTag();
        }
        mViewHolder.imageView.setImageResource(R.drawable.mine_head_default);
        mViewHolder.textView.setText(mlist.get(i));
        return view;
    }
    static class ViewHolder{
        ImageView imageView;
        TextView  textView;
    }
}
