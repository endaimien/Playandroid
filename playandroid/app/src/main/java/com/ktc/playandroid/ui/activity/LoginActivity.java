package com.ktc.playandroid.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.ktc.playandroid.R;
import com.ktc.playandroid.baseview.PlayAndroidApp;
import com.ktc.playandroid.mvpcomponent.contract.LoginContract;
import com.ktc.playandroid.mvpcomponent.present.LoginPresent;
import com.ktc.playandroid.ui.baseui.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity<LoginPresent> implements LoginContract.View {

    @BindView(R.id.but_login_forgetpas)
    Button mForpsd;

    @BindView(R.id.but_login_register)
    Button mRegister;

    @BindView(R.id.login_account)
    AutoCompleteTextView mAccount;

    @BindView(R.id.login_password)
    EditText mPassword;

    @BindView(R.id.login_sign_in)
    Button mSignin;

    @BindView(R.id.login_progress)
    ProgressBar mProgressBar;

    @BindView(R.id.login_form)
    ScrollView mScrollView;

    @Inject
    LoginPresent mLoginPresent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        PlayAndroidApp.getInstance().getAppComponent().getLoginCompnent().inject(this);
        ButterKnife.bind(this);
        if (mLoginPresent != null)
            mLoginPresent.attachView(this);
        setListener();

    }

    private void attemptLogin() {
        // Reset errors.
        mAccount.setError(null);
        mPassword.setError(null);

        // Store values at the time of the login attempt.
        String email = mAccount.getText().toString();
        String password = mPassword.getText().toString();
        showProgress(true);
        mLoginPresent.loginIn(email, password);
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mScrollView.setVisibility(show ? View.GONE : View.VISIBLE);
            mScrollView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mScrollView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressBar.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            mScrollView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private void addAccountToAutocomplete() {
        List<String> accountAutocomplete = mLoginPresent.getShareAccount();
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this, android.R.layout.simple_dropdown_item_1line, accountAutocomplete);
        mAccount.setAdapter(adapter);
    }

    private void setListener() {
        addAccountToAutocomplete();
        mPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE || i == EditorInfo.IME_NULL) {
                    attemptLogin();
                }
                return true;
            }
        });

        mForpsd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mSignin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    @Override
    public void loginSuccess() {
        //showProgress(false);
        startActivity(new Intent(this,MainActivity.class));
        this.finish();
        //Toast.makeText(LoginActivity.this,"success",Toast.LENGTH_LONG).show();
        }


    @Override
    public void loginFail() {
        showProgress(false);
        Toast.makeText(LoginActivity.this,"fail",Toast.LENGTH_LONG).show();
    }

    @Override
    public void registerSuccess() {

    }

    @Override
    public void registFail() {

    }

    @Override
    public void loginOut() {

    }

    @Override
    public void setPresent(LoginPresent present) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLoginPresent != null)
            mLoginPresent.detachView();
    }
}

