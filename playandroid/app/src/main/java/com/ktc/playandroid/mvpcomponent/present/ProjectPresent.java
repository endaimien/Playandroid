package com.ktc.playandroid.mvpcomponent.present;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.ktc.playandroid.internet.bean.project.ProjectClassifyData;
import com.ktc.playandroid.internet.bean.project.ProjectEssayData;
import com.ktc.playandroid.internet.bean.project.ProjectHeader;
import com.ktc.playandroid.internet.httpinterface.HttpNetInterface;
import com.ktc.playandroid.mvpcomponent.basemvp.BasePresent;
import com.ktc.playandroid.mvpcomponent.contract.ProjectContract;
import com.ktc.playandroid.util.RxjarUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ProjectPresent implements ProjectContract.Present {

    public ProjectContract.View mView;
    @Inject
    HttpNetInterface mHttpNeterface;
    @Inject
    public ProjectPresent(){}
    @Override
    public void getClassify() {
        mHttpNeterface.getProjectClassifyList()
                .compose(RxjarUtils.rxScheduleTrans())
                .compose(RxjarUtils.handResuilt())
                .subscribe(new Observer<List<ProjectClassifyData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ProjectClassifyData> projectClassifyData) {
                        ArrayList<String> strings = new ArrayList<>();
                        ArrayList<Integer> cid = new ArrayList<>();
                        Iterator<ProjectClassifyData> listIterator = projectClassifyData.iterator();
                        while (listIterator.hasNext()){
                            ProjectClassifyData projectClassifyData1 = listIterator.next();
                            strings.add(projectClassifyData1.getName());
                            cid.add(projectClassifyData1.getId());
                        }
                        Log.d("wy","onNext()");
                        mView.showClassify(strings,cid);


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getProject(int page, int classify) {
        mHttpNeterface.getProjectEssayList(page,classify)
                .compose(RxjarUtils.rxScheduleTrans())
                .compose(RxjarUtils.handResuilt())
                .subscribe(new Observer<ProjectHeader>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProjectHeader projectHeader) {
                        List<ProjectEssayData> projectEssayData = projectHeader.getDatas();

                        ArrayList<String> name = new ArrayList<>();
                        ArrayList<String> projectcont = new ArrayList<>();
                        ArrayList<String> background = new ArrayList<>();
                        Log.d("wy","getProject"+" "+"onNext"+projectHeader.getDatas().get(0).getEnvelopePic()+projectHeader.getPageCount());
                      for (ProjectEssayData project:projectEssayData){
                             name.add(project.getTitle());
                             projectcont.add(project.getDesc());

                             background.add(project.getEnvelopePic());
                        }
                        Log.d("wy","before getBackf"+name);
                     // ArrayList<Bitmap> bitmaps = getBackgroundDraw();
                        mView.showProject(name,projectcont,background);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void attachView(ProjectContract.View pView) {
           mView = pView;
    }

    @Override
    public void detachView() {

    }

    /*public ArrayList<Bitmap> getBackgroundDraw(){
        ArrayList<Bitmap> bitmaps = new ArrayList<>();
        for(String url:background){
            mHttpNeterface.getDrawable(url)
                    .map(new Function<ResponseBody, Bitmap>() {
                        @Override
                        public Bitmap apply(ResponseBody responseBody) throws Exception {
                            Bitmap bitmap = null;
                            byte[] bys = new byte[0];
                            try {
                                Log.d("wy","getBitmap");
                                bys = responseBody.bytes(); //注意：把byte[]转换为bitmap时，也是耗时操作，也必须在子线程
                                bitmap = BitmapFactory.decodeByteArray(bys, 0, bys.length);
                                //return bitmap;

                            }catch (IOException e) {
                                Log.d("wy","getBitmap error");
                                e.printStackTrace();
                            }
                            return bitmap;
                        }})
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Bitmap>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Bitmap bitmap) {
                           bitmaps.add(bitmap);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
        return bitmaps;
    }*/
}
