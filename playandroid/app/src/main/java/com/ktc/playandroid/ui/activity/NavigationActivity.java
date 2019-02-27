package com.ktc.playandroid.ui.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ktc.playandroid.R;
import com.ktc.playandroid.mvpcomponent.contract.LoginContract;
import com.ktc.playandroid.ui.adapter.NaviAdapter;

import java.util.ArrayList;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationActivity extends AppCompatActivity {
    @BindView(R.id.vp_navi)
    ViewPager mViewPager;
    @BindView(R.id.ll_navi_dot)
    LinearLayout mNavidot;

    Button mComein;
    Button mLogin;


    private ArrayList<ImageView> mImageViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);
        initViewPage();
        initDots(0);
        initListener();
    }

    private void initViewPage(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.layout_navi_4,null);
        mComein = view.findViewById(R.id.bt_navi_come);
        mLogin =  view.findViewById(R.id.bt_navi_login);
        ArrayList<View> viewList = new ArrayList<>();
        viewList.add(inflater.inflate(R.layout.layout_navi_1,null));
        viewList.add(inflater.inflate(R.layout.layout_navi_2,null));
        viewList.add(inflater.inflate(R.layout.layout_navi_3,null));
        viewList.add(view);
        NaviAdapter naviAdapter = new NaviAdapter(viewList);
        mViewPager.setAdapter(naviAdapter);
        //mComein = inflater.inflate(R.layout.layout_navi_4,null).findViewById(R.id.bt_navi_come);
        //mLogin =  inflater.inflate(R.layout.layout_navi_4,null).findViewById(R.id.bt_navi_login);
    }

    private void initDots(int position){
        mNavidot.removeAllViews();
        mImageViews = new ArrayList<ImageView>();
       for(int i=0;i<4;i++){
           ImageView imageView = new ImageView(NavigationActivity.this);
           //imageView.setLayoutParams(new ViewGroup.LayoutParams(30,30));
           //imageView.setPadding(70,0,55,0);
           LinearLayout.LayoutParams  layoutParams = new LinearLayout.LayoutParams(30,30);
           layoutParams.setMargins(30,0,30,0);
           imageView.setLayoutParams(layoutParams);
           if(i==position){
               imageView.setBackground(getDrawable(R.drawable.navi_dot_focus));
           }else
               imageView.setBackground(getDrawable(R.drawable.navi_dot_nofocus));
           Log.d("wy","getdots"+i);
           mNavidot.addView(imageView,i);
           mImageViews.add(imageView);
       }
    }
    public void initListener(){
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                initDots(position);
                Log.d("wy","onChange"+position);
                /*mNavidot.getChildAt(position).setBackground(getDrawable(R.drawable.navi_dot_focus));
               // mImageViews.get(position).setBackground(getDrawable(R.drawable.navi_dot_focus));
                for(int i=0;i<4;i++){
                    if(i!=position){
                        mNavidot.getChildAt(position).setBackground(getDrawable(R.drawable.navi_dot_nofocus));
                       // mImageViews.get(position).setBackground(getDrawable(R.drawable.navi_dot_nofocus));
                    }
                }
*/
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mComein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("wy","navi comein");
                startActivity(new Intent(NavigationActivity.this,MainActivity.class));
            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("wy","navi Login");
                startActivity(new Intent(NavigationActivity.this,LoginActivity.class));
            }
        });
    }

}
