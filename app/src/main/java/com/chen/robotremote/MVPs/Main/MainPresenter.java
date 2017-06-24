package com.chen.robotremote.MVPs.Main;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.chen.robotremote.PrefrenceManager;
import com.chen.robotremote.Server.MyAction1;
import com.chen.robotremote.Server.Server;
import com.chen.robotremote.Server.ServerDataType.BaseResult;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by chen on 17-6-24.
 * Copyright *
 */

public class MainPresenter implements MainContract.Presenter {


    private final MainContract.View mMainView;

    MainPresenter(MainContract.View mainView) {
        mMainView = mainView;
        mMainView.setPresenter(this);
    }

    @Override
    public void start() {
        mMainView.set_video_url(PrefrenceManager.videourlname);
    }

    @Override
    public void destroy() {

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
                        mMainView.show_message(String.valueOf(mVar.code));
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
                        mMainView.show_message(String.valueOf(mVar.code));
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
                        mMainView.show_message(String.valueOf(mVar.code));
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
                        mMainView.show_message(String.valueOf(mVar.code));
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
                        mMainView.show_message(String.valueOf(mVar.code));
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
                        mMainView.show_message(String.valueOf(mVar.code));
                    }
                });
    }

    @Override
    public void speech(String content) {
        Server.getApi().speech(content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyAction1<BaseResult>() {
                    @Override
                    public void call() {
                        mMainView.show_message(String.valueOf(mVar.code));
                    }
                });
    }
}
