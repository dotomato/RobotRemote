package com.chen.robotremote.MVPs.Main;

import android.content.Intent;
import android.preference.PreferenceManager;

import com.chen.robotremote.PM;
import com.chen.robotremote.Server.MyAction1;
import com.chen.robotremote.Server.Server;
import com.chen.robotremote.Server.ServerDataType.BaseResult;
import com.chen.robotremote.Server.ServerDataType.THResult;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chen on 17-6-24.
 * Copyright *
 */

public class MainPresenter implements MainContract.Presenter {


    private final MainContract.View mMainView;
    private final String TAG = "MainPresenter";
    private Mytimer th_thread;

    MainPresenter(MainContract.View mainView) {
        mMainView = mainView;
        mMainView.setPresenter(this);
    }

    private class Mytimer implements Runnable{

        private boolean flag = true;
        @Override
        public void run() {
            while (flag) {
//                Log.d(TAG,"getth");
                Server.getApi().getth()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new MyAction1<THResult>() {
                            public void call() {
                                mMainView.show_th(mVar.t, mVar.h);
                            }
                        });
                try {
                    long i = Math.max(PM.getIns().getThInt(),100L);
                    Thread.sleep(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void stop(){
            flag = false;
        }
    }

    @Override
    public void start() {
        if (th_thread!=null){
            th_thread.stop();
        }
        th_thread = new Mytimer();
        new Thread(th_thread).start();
        mMainView.set_video_url(PM.getIns().getVideoUrl());
        mMainView.set_webview_js(PM.getIns().getJsEnable());
        Server.setSeverHost(PM.getIns().getHostName());
    }

    @Override
    public void destroy() {
        th_thread.stop();
        th_thread=null;
    }

    @Override
    public void onResult(int requestCode, Intent data) {

    }

    @Override
    public void turnleft() {
        Server.getApi().turnleft()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyAction1<BaseResult>() {
                    @Override
                    public void call() {
                    }
                });
    }

    @Override
    public void turnright() {
        Server.getApi().turnright()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyAction1<BaseResult>() {
                    @Override
                    public void call() {
                    }
                });
    }

    @Override
    public void movefront() {
        Server.getApi().movefront()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyAction1<BaseResult>() {
                    @Override
                    public void call() {
                    }
                });
    }

    @Override
    public void moveback() {
        Server.getApi().moveback()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyAction1<BaseResult>() {
                    @Override
                    public void call() {
                    }
                });
    }

    @Override
    public void moveleft() {
        Server.getApi().moveleft()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyAction1<BaseResult>() {
                    @Override
                    public void call() {
                    }
                });
    }

    @Override
    public void moveright() {
        Server.getApi().moveright()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyAction1<BaseResult>() {
                    @Override
                    public void call() {
                    }
                });
    }

    @Override
    public void speech(String content) {
        if (content.equals("前进")) {
            movefront();
            return;
        }

        if (content.equals("后退")) {
            moveback();
            return;
        }

        if (content.equals("左转")) {
            turnleft();
            return;
        }

        if (content.equals("右转")) {
            turnright();
            return;
        }

        if (content.equals("左移")) {
            moveleft();
            return;
        }

        if (content.equals("右移")) {
            moveright();
            return;
        }

        if (content.equals("唱歌")) {
            sing();
            return;
        }

        if (content.equals("跳舞")) {
            dance();
            return;
        }

        if (content.equals("开灯")) {
            lighton();
            return;
        }

        if (content.equals("关灯")) {
            lightoff();
            return;
        }

        niconiconi();
//
//        Server.getApi().speech(content)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new MyAction1<BaseResult>() {
//                    @Override
//                    public void call() {
//                        mMainView.show_message(String.valueOf(mVar.code));
//                    }
//                });
    }



    public void sing(){
        Server.getApi().sing()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyAction1<BaseResult>() {
                    @Override
                    public void call() {
                    }
                });

    }

    public void niconiconi(){
        Server.getApi().niconiconi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyAction1<BaseResult>() {
                    @Override
                    public void call() {
                    }
                });

    }

    public void dance(){
        Server.getApi().dance()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyAction1<BaseResult>() {
                    @Override
                    public void call() {
                    }
                });

    }

    public void lighton(){
        Server.getApi().lighton()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyAction1<BaseResult>() {
                    @Override
                    public void call() {
                    }
                });

    }

    public void lightoff(){
        Server.getApi().lightoff()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyAction1<BaseResult>() {
                    @Override
                    public void call() {
                    }
                });

    }


}
