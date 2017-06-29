package com.chen.robotremote;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by chen on 17-6-24.
 * Copyright *
 */

public class PM {

    private final SharedPreferences pm;
    private static PM ins=null;

    public static PM getIns(){
        return ins;
    }

    public PM(Context var){
        pm = PreferenceManager.getDefaultSharedPreferences(var);
        ins = this;
    }

    public String getHostName (){
        return pm.getString("HostName_preference","http://10.252.252.139:5000");
    }

    public String getVideoUrl (){
        return pm.getString("VideoUrl_preference","https://cn.bing.com");
    }

    public Boolean getJsEnable (){
        return pm.getBoolean("JsEnable_preference",false);
    }

    public long getThInt (){
        return Long.parseLong(pm.getString("ThInt_preference","1000")) ;
    }
}
