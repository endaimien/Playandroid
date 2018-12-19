package com.ktc.playandroid.dagger.scope;

import android.util.AndroidException;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PreActivity {
}
