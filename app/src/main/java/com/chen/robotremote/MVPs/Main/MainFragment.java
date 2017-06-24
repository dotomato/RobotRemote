package com.chen.robotremote.MVPs.Main;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.chen.robotremote.PrefrenceManager;
import com.chen.robotremote.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chen on 17-6-24.
 * Copyright *
 */

public class MainFragment extends Fragment implements MainContract.View {


    private MainContract.Presenter mPresenter;
    private View mView;

    @BindView(R.id.webview)
    public WebView mWebView;

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //初始控件填充
        mView = inflater.inflate(R.layout.main_frag, container, false);
        ButterKnife.bind(this, mView);

        mWebView.getSettings().setJavaScriptEnabled(PrefrenceManager.enableJavascript);
        mWebView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

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
        mWebView.loadUrl(url);
    }

    @Override
    public void set_webview_js(boolean enable) {
        mWebView.getSettings().setJavaScriptEnabled(enable);
        Snackbar.make(mView, "JavaSript Enable: "+String.valueOf(enable), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void show_message(String text) {
        Snackbar.make(mView, text, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.turnleft)
    public void onTurnleft(){
        mPresenter.turnleft();
    }

    @OnClick(R.id.turnright)
    public void onTurnright(){
        mPresenter.turnright();
    }

    @OnClick(R.id.movefront)
    public void onMovefront(){
        mPresenter.movefront();
    }

    @OnClick(R.id.moveback)
    public void onDownClike(){
        mPresenter.moveback();
    }

    @OnClick(R.id.moveleft)
    public void onMoveleft(){
        mPresenter.moveleft();
    }

    @OnClick(R.id.moveright)
    public void onMoveright(){
        mPresenter.moveright();
    }
}
