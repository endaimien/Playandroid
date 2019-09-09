package com.ktc.playandroid.mvpcomponent.present;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ktc.playandroid.baseview.PlayAndroidApp;
import com.ktc.playandroid.internet.bean.PlayHeader;
import com.ktc.playandroid.internet.bean.ResponeBaseDate;
import com.ktc.playandroid.internet.bean.mine.MineLoginData;
import com.ktc.playandroid.internet.httpinterface.HttpNetInterface;
import com.ktc.playandroid.mvpcomponent.contract.LoginContract;
import com.ktc.playandroid.util.RxjarUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginPresent implements LoginContract.Presenter {
    private LoginContract.View mView;
    private Disposable mDisposable;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    @Inject
    HttpNetInterface mHttpNeterface;
    
    @Inject
    LoginPresent() {
    }

    @Override
    public void attachView(LoginContract.View pContext) {
        mView = pContext;
    }

    @Override
    public void detachView() {
        mCompositeDisposable.clear();
    }

    @Override
    public void loginIn(String name, String password) {
       /* try {
            LoginAsyncTask loginAsyncTask = new LoginAsyncTask(name,password);
            loginAsyncTask.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
      /* mHttpNeterface.loginforuser(name,password).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<PlayHeader<MineLoginData>>() {
                   @Override
                   public void onSubscribe(Disposable d) {
                         mDisposable = d;
                         mCompositeDisposable.add(d);
                   }

                   @Override
                   public void onNext(PlayHeader<MineLoginData> mineLoginDataPlayHeader) {
                       if(mineLoginDataPlayHeader.getErrorCode()==0){
                           mView.loginSuccess();
                       }else {
                           mView.loginFail();
                       }
                   }

                   @Override
                   public void onError(Throwable e) {

                   }

                   @Override
                   public void onComplete() {

                   }
               });*/
       mHttpNeterface.loginforuser(name,password)
               .compose(RxjarUtils.rxScheduleTrans())
               .compose(RxjarUtils.handResuilt())
               .subscribe(new Observer<MineLoginData>() {
               @Override
               public void onSubscribe(Disposable d) {
                   mCompositeDisposable.add(d);
               }

               @Override
               public void onNext(MineLoginData mineLoginData) {
                   mView.loginSuccess();
                   SharedPreferences sharedPreferences = PlayAndroidApp.getInstance().getAppComponent().getSharePrefer();
                   SharedPreferences.Editor editor = sharedPreferences.edit();
                   editor.putString("username",name);
                   editor.commit();
           }

           @Override
           public void onError(Throwable e) {
                    mView.loginFail();
           }

           @Override
           public void onComplete() {

           }
       });
    }

    @Override
    public void register(String name, String password, String repassword) {

    }

    @Override
    public void loginOut() {

    }

    @Override
    public List<String> getShareAccount() {
        SharedPreferences sharedPreferences = PlayAndroidApp.getInstance().getSharedPreferences("accountnames", Context.MODE_PRIVATE);
        Map<String, ?> nameprefer = sharedPreferences.getAll();
        ArrayList nameList = new ArrayList();
        for (Map.Entry<String, ?> entry : nameprefer.entrySet()) {
            nameList.add(entry.getValue());
        }
        return nameList;
    }

    public boolean isAccountValid(String account) {
        return true;
    }

    public boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    class LoginAsyncTask extends AsyncTask<Void, Void, Boolean> {
        String mAccount;
        String mPassword;

        LoginAsyncTask(String account, String password) {
            mAccount = account;
            mPassword = password;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean){
                mView.loginSuccess();
            }else {
                mView.loginFail();
            }
        }

        @Override
        protected Boolean doInBackground(Void... voids){
            OkHttpClient okHttpClient = new OkHttpClient();
            FormBody formBody = new FormBody.Builder()
                    .add("username",mAccount).add("password",mPassword).build();
            final Request request = new Request.Builder().post(formBody).url("http://www.wanandroid.com/user/login").build();
            Call call = okHttpClient.newCall(request);
            Response response;
            try {
                response = call.execute();
            } catch (IOException e) {
                Log.d("wy","error respone exception");
                e.printStackTrace();
                response  = null;
            }
            try {
                Log.d("wy","getRespone "+mAccount+"  password"+mPassword+"  respone"+response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (response!=null&&response.isSuccessful()){
                Gson mgson = new Gson();
                try {

                    ResponeBaseDate<MineLoginData> logindate = mgson.fromJson(response.body().string(),new TypeToken<ResponeBaseDate<MineLoginData>>(){
                    }.getType());
                    if(logindate.getErrorCode()==0)
                        return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.d("wy","maccount"+mAccount+"password"+mPassword);
                return false;
            }else
                return false;
        }
    }
}
