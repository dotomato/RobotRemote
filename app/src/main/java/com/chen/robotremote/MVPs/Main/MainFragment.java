package com.chen.robotremote.MVPs.Main;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.speech.VoiceRecognitionService;
import com.chen.robotremote.PrefrenceManager;
import com.chen.robotremote.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chen on 17-6-24.
 * Copyright *
 */

public class MainFragment extends Fragment implements MainContract.View, RecognitionListener {

    private static final String TAG = "MainFragment";

    private MainContract.Presenter mPresenter;
    private View mView;
    private SpeechRecognizer speechRecognizer;

    @BindView(R.id.webview)
    public WebView mWebView;

    @BindView(R.id.speechtext)
    public TextView speechtext;

    @BindView(R.id.speech)
    public Button speechbutton;

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

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(
                getContext(), new ComponentName(getContext(), VoiceRecognitionService.class));
        speechRecognizer.setRecognitionListener(this);

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onDestroy() {
        speechRecognizer.destroy();
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
        show_message("JavaSript Enable: "+String.valueOf(enable));
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

    @OnClick(R.id.speech)
    public void onSpeech(){
        Intent intent = new Intent();

        intent.putExtra("sound_start", R.raw.bdspeech_recognition_start);
        intent.putExtra("sound_end", R.raw.bdspeech_speech_end);
        intent.putExtra("sound_success", R.raw.bdspeech_recognition_success);
        intent.putExtra("sound_error", R.raw.bdspeech_recognition_error);
        intent.putExtra("sound_cancel", R.raw.bdspeech_recognition_cancel);

        intent.putExtra("sample", 16000); // 离线仅支持16000采样率
        intent.putExtra("language", "cmn-Hans-CN"); // 离线仅支持中文普通话
//        intent.putExtra("prop", 20000); // 输入

        speechRecognizer.startListening(intent);
        speechbutton.setText("准备中");
    }

    @Override
    public void onReadyForSpeech(Bundle params) {
        speechbutton.setText("请说");

    }

    @Override
    public void onBeginningOfSpeech() {
        speechbutton.setText("输入中");
    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {
        speechbutton.setText("输入停止");
    }

    @Override
    public void onError(int error) {
        speechbutton.setText("语音命令");

        switch (error) {
            case SpeechRecognizer.ERROR_AUDIO:
                show_message("音频问题");
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                show_message("没有语音输入");
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                show_message("其它客户端错误");
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                show_message("权限不足");
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                show_message("网络问题");
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                show_message("没有匹配的识别结果");
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                show_message("引擎忙");
                break;
            case SpeechRecognizer.ERROR_SERVER:
                show_message("服务端错误");
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                show_message("连接超时");
                break;
        }
        Log.d(TAG,"onError " + error);
    }

    @Override
    public void onResults(Bundle results) {
        speechbutton.setText("语音命令");
        ArrayList<String> nbest = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        if (nbest == null || nbest.size() == 0)
            return;
        String result = nbest.get(0);
        speechtext.setText(result);
        mPresenter.speech(result);
    }

    @Override
    public void onPartialResults(Bundle partialResults) {
        ArrayList<String> nbest = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        if (nbest == null || nbest.size() == 0)
            return;
        String result = nbest.get(0);
        speechtext.setText(result);
    }

    @Override
    public void onEvent(int eventType, Bundle params) {
    }
}
