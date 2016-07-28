package com.felink.guihubcode.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by Administrator on 2016/7/26.
 */
public class SnackbarUtil {
    public static  void showSnack(View view,Context context, String content){
        Snackbar.make(view, "data deleted",
                Snackbar.LENGTH_LONG)
                .setAction("Undo",
                        new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                            }
                        })
                .show();
    }
}
