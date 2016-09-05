package com.felink.guihubcode.xutil;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2016/7/23.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(listenerType = View.OnClickListener.class,listenerSetter = "setOnClickListener",methodName = "onClick")
public @interface OnClick {
    int value();
}
