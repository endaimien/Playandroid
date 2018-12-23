package com.ktc.playandroid.dagger.module;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ktc.playandroid.internet.api.Api;
import com.ktc.playandroid.util.NetUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HttpModule {
    @Provides
    @Singleton
    public OkHttpClient.Builder providerHttpclientBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    @Named("cach")
    public OkHttpClient providerOkhttpClient(OkHttpClient.Builder pOkhttpBuilder) {
        //添加LoggingIntercepter记录打印 引用logging-interceptor:3.12.0 jar
        HttpLoggingInterceptor nLogInterceptor = new HttpLoggingInterceptor();
        nLogInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor cachInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetUtil.isNetConnect()) {
                    request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                }
                Response response = chain.proceed(request);
                if (NetUtil.isNetConnect()) {
                    response = response.newBuilder()
                            .header("Catch-control", "public,max-age=" + 0)
                            .removeHeader("Program").build();
                } else {
                    int value = 60 * 60 * 24 * 28;
                    response = response.newBuilder()
                            .header("Catch-control", "public, only-if-cached, max-stale=" + value)
                            .removeHeader("Program")
                            .build();
                }

                return response;
            }
        };
        //设置缓存地址
        File cachFile = new File(NetUtil.CACHFILE);
        Cache cache = new Cache(cachFile, 1024 * 1024 * 40);
        //设置超时
        pOkhttpBuilder.readTimeout(10, TimeUnit.SECONDS);
        pOkhttpBuilder.writeTimeout(10, TimeUnit.SECONDS);
        pOkhttpBuilder.connectTimeout(10, TimeUnit.SECONDS);
        pOkhttpBuilder.addNetworkInterceptor(nLogInterceptor);
        //设置缓存
        pOkhttpBuilder.addInterceptor(cachInterceptor);
        pOkhttpBuilder.addNetworkInterceptor(cachInterceptor);
        pOkhttpBuilder.cache(cache);
        //错误后重连
        pOkhttpBuilder.retryOnConnectionFailure(true);

        return pOkhttpBuilder.build();
    }

    @Provides
    @Singleton
    @Named("default")
    public OkHttpClient provideDefaultOkhttpClient(OkHttpClient.Builder pBuilder) {
        HttpLoggingInterceptor nLogInterceptor = new HttpLoggingInterceptor();
        nLogInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //设置超时
        pBuilder.readTimeout(10, TimeUnit.SECONDS);
        pBuilder.writeTimeout(10, TimeUnit.SECONDS);
        pBuilder.connectTimeout(10, TimeUnit.SECONDS);
        return pBuilder.addNetworkInterceptor(nLogInterceptor)
                .retryOnConnectionFailure(true)
                .build();
    }

    @Provides
    @Singleton
    @Named("default")
    public Retrofit provideDefaultRetrofit(@Named("default") OkHttpClient pOkHttpClient){
        return new Retrofit.Builder().baseUrl(Api.HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(pOkHttpClient)
                .build();
    }
    @Provides
    @Singleton
    @Named("cach")
    public Retrofit provideCachRetrofit(@Named("cach") OkHttpClient pOkHttpClient){
        return new Retrofit.Builder().baseUrl(Api.HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(pOkHttpClient)
                .build();
    }

    @Provides
    @Singleton
    @Named("cach")
    public Api provideApi(@Named("cach")Retrofit pRetrofit){
        return pRetrofit.create(Api.class);
    }
    @Provides
    @Singleton
    @Named("default")
    public Api provideDefaultApi(@Named("default")Retrofit pRetrofit){
        return pRetrofit.create(Api.class);
    }
}
