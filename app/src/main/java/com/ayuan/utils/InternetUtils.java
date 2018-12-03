package com.ayuan.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetUtils {
    //判断网络是否连接
    public static boolean isNetWorkAvailable(Context context){
        boolean isAvailable = false ;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isAvailable()){
            isAvailable = true;
        }
        return isAvailable;
    }

}
