package com.ktc.playandroid.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ktc.playandroid.R;

import java.util.ArrayList;
import java.util.List;

public class NaviContentRecyAdapter extends RecyclerView.Adapter<NaviContentRecyAdapter.ViewHolder> {
    public ArrayList<String> naviName;
    public NaviContentRecyAdapter(ArrayList<String> naviName) {
        super();
        this.naviName = naviName;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          holder.textView.setText(naviName.get(position));
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.navi_content_item,null));
    }

    @Override
    public int getItemCount() {
        return naviName.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.ttv_navi_content_title);
        }
    }
}
