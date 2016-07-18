package com.felink.guihubcode.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.UUID;

public class SysHelpFun {
    private static final String HOURS_12 = "12";
    private static final String HOURS_24 = "24";

    private static int VERSION_CODE;

    private static String VERSION_NAME;

    private static int VERSION_VALUE;

    /**
     * @brief【获取手机系统版本号】
     * 
     * 
     * @n<b>函数名称</b> :GetandroidSDk
     * @see
     * 
     * @param @return
     * @return String
     * @<b>作者</b> : 邱堃
     * @<b>创建时间</b> : 2011-11-21上午11:57:53
     */
    public static String GetandroidSDk() {
        return android.os.Build.VERSION.RELEASE;
        // int nvis = android.os.Build.VERSION.SDK_INT;
        // String strRes = "";
        //
        // switch (nvis) {
        // case 3:
        // strRes = "v1.5";
        // break;
        // case 4:
        // strRes = "v1.6";
        // break;
        // case 5:
        // strRes = "v2.0";
        // break;
        // case 6:
        // strRes = "v2.0.1";
        // break;
        // case 7:
        // strRes = "v2.1";
        // break;
        // case 8:
        // strRes = "v2.2";
        // break;
        // case 9:
        // strRes = "v2.3";
        // break;
        // case 10:
        // strRes = "v2.3.3";
        // break;
        // case 11:
        // strRes = "v3.0";
        // break;
        // case 12:
        // strRes = "v3.1";
        // break;
        // case 13:
        // strRes = "v3.2";
        // break;
        // case 14:
        // strRes = "v4.0";
        // break;
        // case 15:
        // strRes = "v4.0.3";
        // break;
        // case 16:
        // strRes = "v4.0.4";
        // break;
        // default:
        // strRes = String.format("%d", nvis);
        // break;
        // }
        // return strRes;
    }

    /**
     * @Title: getDeviceId
     * @Description: TODO(获取设备号)
     * @author yanyy
     * @date 2012-8-31 上午09:40:35
     * 
     * @return
     * @return String
     * @throws
     */
    public static String getDeviceId(Context context) {
        // 直接从配置文件读取
        SharedPreferences settings = context.getSharedPreferences("deviceId", 0);
        String deviceId = settings.getString("deviceId", null);
        if (TextUtils.isEmpty(deviceId)) {
            try {
                TelephonyManager tm = (TelephonyManager) context
                        .getSystemService(Context.TELEPHONY_SERVICE);
                deviceId = tm.getDeviceId();
            } catch (Exception e) {

            }
            if (TextUtils.isEmpty(deviceId)) {
                // 有些机器有可能取不到设备号用GUID表示
                deviceId = UUID.randomUUID().toString().replace("-", "");
            }
            // 保存到配置文件(这样可以保证每次都一样)
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("deviceId", deviceId);
            editor.commit();
        }

        return deviceId;
    }

    /**
     * @brief 【获取GUID唯一标识】
     * 
     * 
     * @n<b>函数名称</b> :getNewIdentifyID
     * @see
     * 
     * @param @return
     * @return String
     * @<b>作者</b> : 邱堃
     * @<b>创建时间</b> : 2011-11-21上午11:58:04
     */
    public static String getNewIdentifyID() {
        String strGuid = UUID.randomUUID().toString().replaceAll("-", "");
        return strGuid;
    }

    /**
     * @Title: getVersionCode
     * @Description: TODO(获取软件版本号)
     * @author yanyy
     * @date 2012-5-24 下午08:07:46
     * 
     * @param context
     * @return
     * @return int
     * @throws
     */
    public static int getVersionCode(Context context) {
        if (VERSION_CODE <= 0) {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = null;
            try {
                pi = pm.getPackageInfo(context.getPackageName(), 0);
                VERSION_CODE = pi.versionCode;
            } catch (Exception e) {
            }
        }

        return VERSION_CODE;
    }

    /**
     * 获取版本
     * 
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        if (TextUtils.isEmpty(VERSION_NAME)) {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi;
            try {
                pi = pm.getPackageInfo(context.getPackageName(), 0);
                VERSION_NAME = pi.versionName;
            } catch (NameNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return VERSION_NAME;
    }

    public static int getAppVersionValue(Context context) {
        if (VERSION_VALUE <= 0) {
            getAppVersionName(context);

            try {
                String sVer = VERSION_NAME.replaceAll("\\D", "0");
                VERSION_VALUE = Integer.valueOf(sVer);
            } catch (Exception e) {
            }
        }

        return VERSION_VALUE;
    }

    /**
     * @brief 【是否为24小时制】
     * @n<b>函数名称</b> :Is24Hour
     * @param context
     * @return
     * @return boolean
     * @<b>作者</b> : 陈希
     * @<b>修改</b> :
     * @<b>创建时间</b> : 2012-6-11上午10:53:51
     * @<b>修改时间</b> :
     */
    public static boolean Is24Hour(Context context) {
        ContentResolver cv = context.getContentResolver();
        String strTimeFormat = Settings.System.getString(cv, Settings.System.TIME_12_24);

        if (strTimeFormat != null && strTimeFormat.equals(HOURS_24)) // 某些rom12小时制时会返回null
        {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @brief 【设置24小时制】
     * @n<b>函数名称</b> :set24Hour
     * @param context
     * @param b24Hour
     * @return void
     * @<b>作者</b> : 陈希
     * @<b>修改</b> :
     * @<b>创建时间</b> : 2012-6-11上午10:54:20
     * @<b>修改时间</b> :
     */
    public static void set24Hour(Context context, boolean b24Hour) {
        ContentResolver cv = context.getContentResolver();
        Settings.System.putString(cv, Settings.System.TIME_12_24, b24Hour ? HOURS_24 : HOURS_12);
    }

}
