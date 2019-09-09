package com.ktc.playandroid.drawview;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ktc.playandroid.R;

public class LoadingCircleDialog extends Dialog {
    public LoadingCircleDialog(@NonNull Context context) {
        super(context);
    }

    public LoadingCircleDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }
    public static class Builder{
        private String message;
        private Context context;
        private boolean isShowMessage=true;
        private boolean isCancelable=false;
        private boolean isCancelOutside=false;
        public  Builder(Context context){
            this.context = context;
        }
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setShowMessage(boolean showMessage) {
            isShowMessage = showMessage;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            isCancelable = cancelable;
            return this;
        }

        public Builder setCancelOutside(boolean cancelOutside) {
            isCancelOutside = cancelOutside;
            return this;
        }
        public LoadingCircleDialog create(){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.loadcicle_dialog,null);
            LoadingCircleDialog loadingCircleDialog = new LoadingCircleDialog(context,R.style.MyDialogStyle);
            TextView msgText= (TextView) view.findViewById(R.id.tipTextView);
            if(isShowMessage){
                msgText.setText(message);
            }else{
                msgText.setVisibility(View.GONE);
            }
            loadingCircleDialog.setContentView(view);
            loadingCircleDialog.setCancelable(isCancelable);
            loadingCircleDialog.setCanceledOnTouchOutside(isCancelOutside);
            return loadingCircleDialog;
        }
    }
}
