package com.chen.robotremote.Server;

import android.util.Log;


import rx.Observer;

/**
 * Created by chen on 17-2-25.
 * Copyright *
 */
//一个封装好了服务器已知错误和意外错误的Action1
public abstract class MyAction1<T> implements Observer<T> {
    @Override
    public final void onCompleted() {

    }

    @Override
    public final void onError(Throwable e) {
        e.printStackTrace();
        error(-101, "服务器发生未知错误，或者是转换返回数据体时出错");
    }

    @Override
    public final void onNext(T var){
        mVar = var;
        call();
    }

    public T mVar;

    public void call(){   }

    public void error(int statue, String errorMessage){
        Log.e("MyAction1","statue="+statue+" errorMessage="+errorMessage);
    }


}