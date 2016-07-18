package com.felink.guihubcode.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by Administrator on 2016/7/18.
 */
public class ScreenUtil {

    public static int dip2px(Context context, float dipValue) {

        final float scale = context.getResources().getDisplayMetrics().density;

        return (int) (dipValue * scale + 0.5f);

    }

    public static int dip2px(float dipValue) {
        return (int) (getRawSize(TypedValue.COMPLEX_UNIT_DIP, dipValue) + 0.5f);
    }

    public static float getRawSize(int unit, float size) {

        Resources r = Resources.getSystem();
        return TypedValue.applyDimension(unit, size, r.getDisplayMetrics());
    }
}
