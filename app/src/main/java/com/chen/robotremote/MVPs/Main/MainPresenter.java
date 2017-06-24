package com.chen.robotremote.MVPs.Main;

import android.content.Intent;

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

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onResult(int requestCode, Intent data) {

    }

    @Override
    public void turnleft_button() {

    }

    @Override
    public void turnright_button() {

    }

    @Override
    public void up_button() {

    }

    @Override
    public void down_button() {

    }
}
