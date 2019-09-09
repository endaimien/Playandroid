package com.ktc.playandroid.mvpcomponent.contract;

import android.graphics.Bitmap;

import com.ktc.playandroid.mvpcomponent.basemvp.BaseMview;
import com.ktc.playandroid.mvpcomponent.basemvp.BasePresent;
import com.ktc.playandroid.mvpcomponent.present.ProjectPresent;

import java.util.ArrayList;

public interface ProjectContract {
     interface View extends BaseMview<ProjectPresent>{
         void showClassify(ArrayList<String> classsfyname,ArrayList<Integer> cid);
         void showProject(ArrayList<String> classsfyname,ArrayList<String> projectContent,ArrayList<String> projectBackground);
     }
     interface Present extends BasePresent<ProjectContract.View>{
         void getClassify();
         void getProject(int page,int classify);
     }
}
