package com.ktc.playandroid.ui.baseui;

import android.arch.lifecycle.Lifecycle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.ktc.playandroid.R;

public abstract  class BaseResponeFragment extends BaseFragment {
    private final static String TAG = "BaseResponeFragment";
    public final static int NORM_STATE = 0;
    public final static int LOAD_STATE = 1;
    public final static int ERRO_STATE = 2;
    private int currentState = NORM_STATE;

    private LottieAnimationView mLoadingAnimation;
    public View mNormalView;
    public View mErrorView;
    public View mLoadView;

    public void initResponse(){
        Log.d(TAG ,getView()+" "+"initRespone");
        if(getView()==null){
            return;
        }
        mNormalView = getView().findViewById(R.id.lrl_project_totol);
        if (mNormalView == null) {
            throw new IllegalStateException(
                    "The subclass of RootActivity must contain a View named 'mNormalView'.");
        }
        if (!(mNormalView.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException(
                    "mNormalView's ParentView should be a ViewGroup.");
        }
        ViewGroup viewGroup = (ViewGroup) mNormalView.getParent();
        View.inflate(getContext(),R.layout.loading_view,viewGroup);
        View.inflate(getContext(),R.layout.error_view,viewGroup);

        mLoadView = viewGroup.findViewById(R.id.loading_group);
        mErrorView = viewGroup.findViewById(R.id.error_group);
        TextView reloadTv = mErrorView.findViewById(R.id.error_reload_tv);
        reloadTv.setOnClickListener(v -> reload());
        mLoadingAnimation = mLoadView.findViewById(R.id.loading_animation);
        mErrorView.setVisibility(View.GONE);
        mLoadView.setVisibility(View.GONE);
        mNormalView.setVisibility(View.VISIBLE);

    }
    public void reload(){}

    public void showLoading(){
        Log.d(TAG ,mLoadView+" "+currentState);
        if(mLoadView==null||currentState==LOAD_STATE){
            return;
        }
        hideCurrentView();
        currentState = LOAD_STATE;
        mLoadView.setVisibility(View.VISIBLE);
        mLoadingAnimation.setAnimation("loading_bus.json");
        mLoadingAnimation.loop(true);
        mLoadingAnimation.playAnimation();
    }

    public void showError(){
        if (currentState == ERRO_STATE) {
            return;
        }
        hideCurrentView();
        currentState = ERRO_STATE;
        mErrorView.setVisibility(View.VISIBLE);
    }

    public void showNormal() {
        if (currentState == NORM_STATE) {
            return;
        }
        hideCurrentView();
        currentState = NORM_STATE;
        mNormalView.setVisibility(View.VISIBLE);
    }
    public void hideCurrentView(){
        switch (currentState){
            case NORM_STATE:
                if (mNormalView == null) {
                    return;
                }
                mNormalView.setVisibility(View.INVISIBLE);
                break;
            case LOAD_STATE:
                mLoadingAnimation.cancelAnimation();
                mLoadView.setVisibility(View.GONE);
                break;
            case ERRO_STATE:
                mErrorView.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
}
