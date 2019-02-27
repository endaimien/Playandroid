package com.ktc.playandroid.util;

import com.ktc.playandroid.internet.bean.PlayHeader;
import com.ktc.playandroid.internet.bean.mine.MineLoginData;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxjarUtils {
    /*
    *compose for translate observable*/
    public static<T> ObservableTransformer<T,T> rxScheduleTrans(){
        /*return  new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };*/
        return observable->observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static<T> ObservableTransformer<PlayHeader<T>,T> handResuilt(){
        /*return new ObservableTransformer<PlayHeader<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<PlayHeader<T>> upstream) {

                return upstream.flatMap(new Function<PlayHeader<T>, Observable<T>>() {
                    @Override
                    public Observable<T> apply(PlayHeader<T> tPlayHeader) throws Exception {
                       if(tPlayHeader.getErrorCode()==0&&tPlayHeader.getData()!=null
                       &&NetUtil.isNetConnect()){
                           return Observable.create(new ObservableOnSubscribe<T>() {
                               @Override
                               public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                                   emitter.onNext(tPlayHeader.getData());
                               }
                           });
                       }else {
                           return Observable.error(new Exception());

                       }
                    }
                });
            }
        };*/
        return upstream -> upstream.flatMap((Function<PlayHeader<T>,Observable<T>>)tPlayHeader->{
            if(tPlayHeader.getErrorCode()==0&&tPlayHeader.getData()!=null
                    &&NetUtil.isNetConnect()){
                return Observable.create(new ObservableOnSubscribe<T>() {
                    @Override
                    public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                        emitter.onNext(tPlayHeader.getData());
                    }
                });
            }else {
                return Observable.error(new Exception());

            }
        });
    }
}
