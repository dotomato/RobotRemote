package com.chen.robotremote.MVPs.Main;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chen.robotremote.R;

/**
 * Created by chen on 17-6-24.
 * Copyright *
 */

public class MainFragment extends Fragment implements MainContract.View {


    private MainContract.Presenter mPresenter;
    private View mView;

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //初始控件填充
        mView = inflater.inflate(R.layout.main_frag, container, false);

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onDestroy() {
        mPresenter.destroy();
        super.onDestroy();
    }

    @Override
    public void set_video_url(String url) {

    }

    @Override
    public void show_setup_ip_window() {
        Snackbar.make(mView, "设置IP", Toast.LENGTH_SHORT).show();
    }
}
