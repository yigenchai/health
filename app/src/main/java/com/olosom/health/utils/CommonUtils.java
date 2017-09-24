package com.olosom.health.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.Toast;

import com.olosom.health.app.App;

public class CommonUtils {


    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * check if network avalable
     */
    public static boolean isNetWorkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable() && mNetworkInfo.isConnected();
            }
        }

        return false;
    }

    /**
     * check if sdcard exist
     */
    public static boolean isSdcardExist() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取缓存目录
     */
    public static File getDiskCacheDir(Context context) {
        File cache = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cache = context.getExternalCacheDir();
        } else {
            cache = context.getCacheDir();
        }
        return cache;
    }


    /**
     * make true current connect service is wifi
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    public static String transformStringArrayToString(String[] array) {
        StringBuilder sb = new StringBuilder();
        int length = array.length;
        String temp = ",";
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                sb.append(array[i]);
                sb.append(temp);
            }
        }
        return sb.toString();
    }

    public static String transformFloatArrayToString(float[] array) {
        StringBuilder sb = new StringBuilder();
        int length = array.length;
        String temp = ",";
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                sb.append(String.valueOf(array[i]));
                sb.append(temp);
            }
        }
        return sb.toString();
    }

    public static String[] transformStringToStringArray(String content) {
        if (!TextUtils.isEmpty(content)) {
            return content.split(",");
        }
        return null;
    }

    public static float[] transformStringToFloatArray(String content) {
        if (!TextUtils.isEmpty(content)) {
            String[] stringArr = content.split(",");
            int length = stringArr.length;
            float[] floatArr = new float[length];
            for (int i = 0; i < length; i++) {
                floatArr[i] = Float.valueOf(stringArr[i]);
            }
            return floatArr;
        }
        return null;
    }

    public static String transformTimeToString(long time){
        Date date = new Date();
        date.setTime(time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }


}
