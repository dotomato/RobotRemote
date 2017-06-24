package com.chen.robotremote;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by chen on 17-6-24.
 * Copyright *
 */

public class PrefrenceManager {

    public static String hostname = "http://10.252.252.139:5000";
    public static String videourlname = "https://cn.bing.com";
    public static boolean enableJavascript = false;

    public static void loadPrefrence(Context context){
        SharedPreferences pref = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        hostname = pref.getString("hostname",hostname);
        enableJavascript = pref.getBoolean("enableJavascript",enableJavascript);
        videourlname = pref.getString("videourlname", videourlname);
    }

    public static void storerefrence(Context context){
        SharedPreferences pref = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("hostname", hostname);
        editor.putBoolean("enableJavascript", enableJavascript);
        editor.putString("videourlname", videourlname);
        editor.apply();
    }
}
